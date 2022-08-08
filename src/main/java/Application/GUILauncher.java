package Application;
import Controller.LoginController;
import Model.Database.DataAccessObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class handles the lauching of the application as well as its closing
 */
public class GUILauncher extends Application {

    /**
     * This method starts the application by setting up the proper pagers
     * @param stage object used by JavaFX, it is somewhat analogous to a
     * HTML page.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setResources(new DataAccessObject("jdbc:sqlite:MedicalSystem.db"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            logoutMedicalProgram(stage);
        });
    }

    /**
     * This method ensures that a confirmation is displayed on any page when
     * attempting to close it. It also also closes the connection
     * @param stage any stage from which the
     * user might try to close the application by clicking X.
     */
    public void logoutMedicalProgram(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Medical program");
        alert.setHeaderText("Would you like to exit the Medical program?");
        alert.setContentText("Click OK to exit, click cancel to return");
        if(alert.showAndWait().get() == ButtonType.OK) {
            //Code à effectuer lorsque le programme se termine
            stage.close();
        }
    }
    public static void launch(String[] args) {
        launch();
    }
}