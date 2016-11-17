package br.taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.taskfacil.dao.UserDAO;
import br.taskfacil.models.User;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnchorPaneCadastroUsuarioDialogController implements Initializable {
	@FXML
	private TextField textFieldEmail = new TextField();
	@FXML
	private PasswordField passwordFieldSenha;
	@FXML
	private Button buttonConfirmar;
	@FXML
	private Button buttonCancelar;

	private Stage dialogStage;
	private boolean buttonConfirmarClicked = false;
	private User user;
	private UserDAO dao = new UserDAO();
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isButtonConfirmarClicked() {
		return buttonConfirmarClicked;
	}

	public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
		this.buttonConfirmarClicked = buttonConfirmarClicked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
	public void handleButtonConfirmar() {
		if(verificateEmail(textFieldEmail.getText())){
			user = new User(textFieldEmail.getText(), passwordFieldSenha.getText());
			dao.insert(user);
		}
	}

	@FXML
	public void handleButtonCancelar() {
		this.buttonConfirmarClicked = false;
		this.dialogStage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Criação de Evento quando o TextField não está selecionado pelo
		// usuário
		textFieldEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					System.out.println("Textfield on focus");
				} else {
					verificateEmail(textFieldEmail.getText());
					System.out.println("Textfield out focus");
				}
			}
		});

	}

	// Métodos da expressão regular do E-mail
	public boolean verificateEmail(String email) {
		if (!validate(email)) {

			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setContentText("Por favor insira um e-mail válido!");
			errorAlert.show();
			return false;
		} else {
			return true;
		}
	}

	private boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
