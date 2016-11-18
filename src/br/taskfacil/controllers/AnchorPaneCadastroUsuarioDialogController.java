package br.taskfacil.controllers;

import java.net.URL;
import java.util.List;
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
	private TextField textFieldNome;
	@FXML
	private TextField textFieldEmail = new TextField();
	@FXML
	private PasswordField passwordFieldSenha;
	@FXML
	private Button buttonConfirmar;
	@FXML
	private Button buttonCancelar;

	private Stage dialogStage;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
	public void handleButtonConfirmar() {
		if (verificateEmail(textFieldEmail.getText())     ) {
			Integer b = passwordFieldSenha.getText().hashCode();
			System.out.println(b);
			user = new User(textFieldNome.getText(), textFieldEmail.getText(), b.toString());
			dao.insert(user);
		}
		this.dialogStage.close();
	}

	@FXML
	public void handleButtonCancelar() {
		this.dialogStage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Criação de Evento quando o TextField não está selecionado pelo
		// usuário
		
		textFieldNome.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					System.out.println("Textfield Email on focus");
				} else {
					verificateName(textFieldNome.getText());
					System.out.println("Textfield Email out focus");
				}
			}
		});
		
		textFieldEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					System.out.println("Textfield Email on focus");
				} else {
					verificateEmail(textFieldEmail.getText());
					System.out.println("Textfield Email out focus");
				}
			}
		});
		
		passwordFieldSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					System.out.println("Passwordfield Senha on focus");
				} else {
					verificatePassword(passwordFieldSenha.getText());
					System.out.println("Passwordfield Senha out focus");
				}
			}
		});
	}
	
	// Métodos de verificação de nome
	public boolean verificateName(String name) {
		if(name.length()==0){
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("Sem nome!");
			errorAlert.setContentText("Insira um nome!");
			errorAlert.show();
			return false;
		}else{
			return true;
		}
	}
	
	// Métodos de verificação da Senha
		public boolean verificatePassword(String password) {
			if(password.length()<=4){
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
				errorAlert.setHeaderText("Senha curta!");
				errorAlert.setContentText("Insira uma senha com mais de 4 caracteres!");
				errorAlert.show();
				return false;
			}else{
				return true;
			}
		}

	// Métodos da expressão regular do E-mail
	public boolean verificateEmail(String email) {
		List<User> listaUsuarios = dao.findAll();
		for (User u : listaUsuarios) {
			if (u.getEmail().equals(email)) {
				Alert errorAlert = new Alert(Alert.AlertType.ERROR);
				errorAlert.setHeaderText("E-mail já existente!");
				errorAlert.setContentText("E-mail já existe no sistema!");
				errorAlert.show();
				return false;
			}
		}
		if (!validate(email)) {

			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("E-mail Inválido!");
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
