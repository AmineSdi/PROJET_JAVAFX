package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GUILauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL url = new File("src/main/resources/Application/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        //stage.setTitle("Consultation des historiques médicaux");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            logoutMedicalProgram(stage);
        });
    }

    public void logoutMedicalProgram(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Medical program");
        alert.setHeaderText("Would you like to exit the Medical program?");
        alert.setContentText("Click yes to exit, click cancel to return");

        if(alert.showAndWait().get() == ButtonType.OK) {
            //Code à effectuer lorsque le programme se termine
            stage.close();
        }
    }

    public static void launch(String[] args) {
        launch();
    }
}