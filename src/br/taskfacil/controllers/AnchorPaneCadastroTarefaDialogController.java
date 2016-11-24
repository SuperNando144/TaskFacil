package br.taskfacil.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.taskfacil.dao.TaskDAO;
import br.taskfacil.models.Task;
import br.taskfacil.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AnchorPaneCadastroTarefaDialogController implements Initializable {
	@FXML
	TextField textFieldTitulo;
	@FXML
	TextArea textAreaDescricao;
	@FXML
	TextField textFieldData;
	@FXML
	TextField textFieldLocal;
	@FXML
	Button buttonConfirmar2;
	@FXML
	Button buttonLimpar;
	@FXML
	Button buttonVoltar;

	private Stage dialogStage;
	private TaskDAO dao;
	private Task task;
	private User user;
	private int acao;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void initData(User user, Task task, int acao) {
		this.user = user;
		System.out.println(this.user);
		this.task = task;
		System.out.println(this.task);
		this.acao = acao;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new TaskDAO();
	}

	@FXML
	public void handleButtonConfirmar() {
		if (acao == 0) {
			this.task = new Task(textFieldTitulo.getText(), textAreaDescricao.getText(), textFieldLocal.getText(), textFieldData.getText());
			this.task.setIdOwner(this.user);
			
			System.out.println(this.task.toString());
			
			dao.insert(this.task);
		} else {

		}

		this.dialogStage.close();
	}

	@FXML
	public void handleButtonLimpar() {
		this.textAreaDescricao.clear();
		this.textFieldData.clear();
		this.textFieldLocal.clear();
		this.textFieldTitulo.clear();
	}

	@FXML
	public void handleButtonVoltar() {
		this.dialogStage.close();
	}
}
