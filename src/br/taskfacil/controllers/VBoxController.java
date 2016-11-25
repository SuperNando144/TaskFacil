package br.taskfacil.controllers;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxController implements Initializable {
	@FXML
	private Menu menuAjuda;
	@FXML
	private MenuItem menuItemSobre;
	@FXML
	private Button buttonCriarConta;
	@FXML
	private Button buttonEntrar;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private PasswordField passwordFieldSenha;
	@FXML
	ImageView imageViewLogo;

	
	private UserDAO dao = new UserDAO();
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@FXML
	public void handleMenuItemSobre() throws IOException {
		Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
		errorAlert.setHeaderText("Sobre!");
		errorAlert.setContentText("Programinha para a matéria de Arquitetura de Software dos professores Samuka e Joice :)");
		errorAlert.show();
	}

	@FXML
	public void handleButtonCriarConta() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AnchorPaneCadastroUsuarioDialogController.class
				.getResource("/br/taskfacil/views/AnchorPaneCadastroUsuarioDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de Usuário");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		AnchorPaneCadastroUsuarioDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();
	}

	@FXML
	public void handleButtonEntrar() throws IOException {
		VBoxPrincipalController controller;
		boolean entrou = false;

		Integer b = passwordFieldSenha.getText().hashCode();
		User user = new User(textFieldEmail.getText(), b.toString());

		if (dao.findPassword(user)) {

			user.setNome(dao.findName(user));
			user.setId(dao.findId(user));
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AnchorPaneCadastroUsuarioDialogController.class
					.getResource("/br/taskfacil/views/VBoxPrincipal.fxml"));
			
			
			VBox page = (VBox) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("TaskFácil - Menu Principal");
			Scene scene = new Scene(page);
			
			controller = loader.getController();
			controller.initData(user);
			dialogStage.setScene(scene);
			controller.setDialogStage(dialogStage);
			Stage stage = (Stage) buttonEntrar.getScene().getWindow();
			stage.close();
			dialogStage.showAndWait();
			entrou = true;
		}

		if (!entrou) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("Informação Incorreta!");
			errorAlert.setContentText("Email e/ou Senha incorreto(s)!");
			errorAlert.show();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imageViewLogo.setImage(new Image("file:images/taskFacil.png"));
		
	}

}
