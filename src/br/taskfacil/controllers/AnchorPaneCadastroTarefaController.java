package br.taskfacil.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.taskfacil.dao.TaskDAO;
import br.taskfacil.models.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AnchorPaneCadastroTarefaController implements Initializable {
	@FXML
	private TableView<Task> tableViewTarefas;
	@FXML
	private TableColumn<Task, Long> tableColumnId;
	@FXML
	private TableColumn<Task, String> tableColumnTitulo;
	@FXML
	private Label labelId;
	@FXML
	private Label labelTitulo;
	@FXML
	private Label labelDescricao;
	@FXML
	private Label labelLocal;
	@FXML
	private Label labelData;
	@FXML
	private Button buttonInserir;
	@FXML
	private Button buttonEditar;
	@FXML
	private Button buttonApagar;

	private ArrayList<Task> taskList;
	private ObservableList<Task> taskObservableList;
	private TaskDAO dao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.dao = new TaskDAO();
		loadTableViewTask();

		this.tableViewTarefas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selectItemTableViewTarefas(newValue));

	}

	private void loadTableViewTask() {
		this.taskList = (ArrayList<Task>) this.dao.findAll();

		this.tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("title"));

		// conversão de ArrayList para ObservableList
		this.taskObservableList = FXCollections.observableArrayList(this.taskList);
		this.tableViewTarefas.setItems(this.taskObservableList);

	}

	private void selectItemTableViewTarefas(Task task) {
		System.out.println(task);

		if (task != null) {
			this.labelId.setText(String.valueOf(task.getId()));
			this.labelTitulo.setText(task.getTitle());

			this.labelDescricao.setText(task.getDescription());
			this.labelLocal.setText(task.getLocation());
			this.labelData.setText(task.getRealizationDate());
		}
	}
	
	@FXML
	private void handleButtonInserir(){
		
	}
	
	@FXML
	private void handleButtonEditar(){
		
	}
	
	@FXML
	private void handleButtonApagar(){
		
	}

}
