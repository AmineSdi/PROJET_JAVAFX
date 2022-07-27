package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
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
    }

    public static void launch(String[] args) {
        launch();
    }
}