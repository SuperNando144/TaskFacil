package br.taskfacil.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.taskfacil.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnchorPaneCadastroUsuarioDialogController {
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldSenha;
	@FXML
	private Button buttonConfirmar;
	@FXML
	private Button buttonCancelar;

	private Stage dialogStage;
	private boolean buttonConfirmarClicked = false;
	private User user;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

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
	public void handleButtonCancelar() {
		this.buttonConfirmarClicked = false;
		this.dialogStage.close();
	}

	@FXML
	public void verificateTextFieldEmail() {
		if (!validate(textFieldEmail.getText())) {

			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setContentText("Por favor insira um e-mail válido!");
			errorAlert.show();
		}
	}

	private boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
