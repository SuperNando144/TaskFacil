package br.taskfacil.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VBoxPrincipalController implements Initializable {
	@FXML
	Label labelNome;
	@FXML
	Menu menuLista;
	@FXML
	private AnchorPane anchorPane;

	private Stage dialogStage;

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void handleMenuTarefa() throws IOException {
		System.out.println("*****************************");
		AnchorPane ap = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/br/taskfacil/AnchorPaneCadastroTarefa.fxml"));
		this.anchorPane.getChildren().setAll(ap);
	}
}