package Application;

import Controller.LoginController;
import Controller.RamqSearchController;
import Model.Database.DataAccessObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GUILauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setResources(new DataAccessObject());

//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        URL url = new File("src/main/resources/Application/login.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Scene scene = new Scene(root);
//        //stage.setTitle("Consultation des historiques médicaux");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void launch(String[] args) {
        launch();
    }
}