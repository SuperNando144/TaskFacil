package br.taskfacil.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.taskfacil.dao.TaskDAO;
import br.taskfacil.models.Task;
import br.taskfacil.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxPrincipalController implements Initializable {
	@FXML
	Label labelNome;
	@FXML
	Menu menuLista;
	@FXML
	MenuItem menuItemEditar;
	@FXML
	Button buttonLogout;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView<Task> tableViewTarefas;
	@FXML
	private TableColumn<Task, String> tableColumnTitulo;
	@FXML
	private TableColumn<Task, String> tableColumnDescricao;
	@FXML
	private TableColumn<Task, String> tableColumnLocal;
	@FXML
	private TableColumn<Task, String> tableColumnData;

	private Stage dialogStage;
	private ArrayList<Task> taskList;
	private ObservableList<Task> taskObservableList;
	private User user;
	private TaskDAO dao;
	
	public void setUser(User user){
		this.user = user;
		this.labelNome.setText(user.getNome());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.dao = new TaskDAO();
		loadTableViewTask();

	
	}
	
	private void loadTableViewTask() {
		//this.taskList = (ArrayList<Task>) this.dao.findSpecific(task);

		this.tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("title"));
		this.tableColumnData.setCellValueFactory(new PropertyValueFactory<>("realizationDate"));
		this.tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("description"));
		this.tableColumnLocal.setCellValueFactory(new PropertyValueFactory<>("location"));
		// conversão de ArrayList para ObservableList
		this.taskObservableList = FXCollections.observableArrayList(this.taskList);
		this.tableViewTarefas.setItems(this.taskObservableList);

	}


	@FXML
	public void handleMenuItemEditar() throws IOException {
		AnchorPane ap = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/br/taskfacil/views/AnchorPaneCadastroTarefa.fxml"));
		this.anchorPane.getChildren().setAll(ap);
	}

	@FXML
	public void handleButtonLogout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				AnchorPaneCadastroUsuarioDialogController.class.getResource("/br/taskfacil/views/VBoxApp.fxml"));

		VBox page = (VBox) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("TaskFácil - Login");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		VBoxController controller = loader.getController();
		this.dialogStage.close();
		dialogStage.showAndWait();
	}
}
