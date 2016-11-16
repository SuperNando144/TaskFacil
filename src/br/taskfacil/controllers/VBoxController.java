package br.taskfacil.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
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
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
