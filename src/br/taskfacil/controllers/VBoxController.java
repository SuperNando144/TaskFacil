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

	private UserDAO dao = new UserDAO();
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@FXML
	public void handleMenuItemSobre() throws IOException {
		System.out.println("*****************************");
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
		if (verificateEmail(textFieldEmail.getText())) {

			Integer b = passwordFieldSenha.getText().hashCode();
			System.out.println(b);

			List<User> listaUsuarios = dao.findAll();
			for (User u : listaUsuarios) {
				if ((u.getEmail().equals(textFieldEmail.getText()) && (Integer.parseInt(u.getPassword()) == b))) {

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(AnchorPaneCadastroUsuarioDialogController.class
							.getResource("/br/taskfacil/views/VBoxPrincipal.fxml"));

					VBox page = (VBox) loader.load();

					Stage dialogStage = new Stage();
					dialogStage.setTitle("TaskFácil - Menu Principal");
					Scene scene = new Scene(page);
					dialogStage.setScene(scene);

					VBoxPrincipalController controller = loader.getController();
					controller.setDialogStage(dialogStage);
					Stage stage = (Stage) buttonEntrar.getScene().getWindow();
					stage.close();
					dialogStage.showAndWait();

				}
			}

		} else {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("Informação Incorreta!");
			errorAlert.setContentText("Email e/ou Senha incorreto(s)!");
			errorAlert.show();
		}
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
		List<User> listaUsuarios = dao.findAll();
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
