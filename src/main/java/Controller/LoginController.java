package Controller;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Database.DataAccessObject;
import Model.User.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.crypto.Data;

/**
 * This class handles ''WHAT'' and will provide information
 */
public class LoginController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    DataAccessObject dataAccessObject;

    //*************************//
    // FXML TextField variables//
    //*************************//
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfPassword;


    //*********************//
    //FXML Button Variables//
    //*********************//
    @FXML
    private Button btnLogin;

    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnLogin(ActionEvent event) throws Exception {
        if (tfUserName.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            // TODO : Message d'erreur qui s'affiche pour l'utilisateur.
            System.out.println("Please fill all the fields.");
        }
        else {
            Doctor doctor = dataAccessObject.findUsernameAndPassword(tfUserName.getText(), tfPassword.getText());
            if (doctor != null) {

                // Pass data to the next controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/ramqSearch.fxml"));
                root = loader.load();
                RamqSearchController ramqSearchController = loader.getController();
                ramqSearchController.setResources(doctor, dataAccessObject);

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                System.out.println(doctor.getFirstName()
                        + " " + doctor.getLastName()
                        + " " + doctor.getLicense()
                        + " " + doctor.getUserName()
                        + " " + doctor.getPassword()
                        + " " + doctor.getEstablishmentName());
            } else {
                // TODO : Message d'erreur qui s'affiche pour l'utilisateur.
                System.out.println("Wrong username or password.");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // showPatientFiles();
    }

    public void setResources(DataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }
}
