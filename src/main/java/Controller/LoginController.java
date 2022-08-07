package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Database.DataAccessObject;
import Model.User.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class handles ''WHAT'' and will provide information
 */
public class LoginController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DataAccessObject dataAccessObject;

    //*************************//
    // FXML TextField variables//
    //*************************//
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfPassword;

    //*************************//
    // FXML Label variables (error messages)//
    //*************************//
    @FXML
    private Label errorNoFields;
    @FXML
    private Label errorNoUserPassword;

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
            errorNoUserPassword.setVisible(false);
            errorNoFields.setVisible(true);
        }
        else {
            Doctor doctor = dataAccessObject.findUsernameAndPassword(tfUserName.getText(),
                            tfPassword.getText());
            if (doctor != null) {
                goToRamqSearchPage(event, doctor);
            } else {
                errorNoFields.setVisible(false);
                errorNoUserPassword.setVisible(true);
            }
        }
    }

    //**************//
    //Public Methods//
    //**************//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Sets the private variables for this controller on page entry.
     * @param dataAccessObject
     */
    public void setResources(DataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    //***************//
    //Private Methods//
    //***************//
    /**
     * Goes to the RAMQ search page, passing any required data.
     *
     * @param event The ActionEvent
     * @param doctor The Doctor
     * @throws IOException
     */
    private void goToRamqSearchPage(ActionEvent event, Doctor doctor) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                                           .getResource("/Application/ramqSearch.fxml"));
        root = loader.load();
        RamqSearchController ramqSearchController = loader.getController();
        ramqSearchController.setResources(doctor, dataAccessObject);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
