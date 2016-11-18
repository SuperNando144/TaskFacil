package br.taskfacil.app;

import br.taskfacil.controllers.VBoxController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/br/taskfacil/views/VBoxApp.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("TaskFacil - Login");

		VBoxController controller = new VBoxController();
		controller.setDialogStage(stage);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
