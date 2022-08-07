package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Builder.Director;
import Model.Builder.PatientFileBuilder;
import Model.Database.DataAccessObject;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class handles ''WHAT'' and will provide information
 */
public class RamqSearchController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DataAccessObject dataAccessObject;
    private Doctor doctor;
    private PatientFile patientFile;

    //*************************//
    // FXML TextField variables//
    //*************************//
    @FXML
    private TextField tfRamqCode;

    //*************************//
    // FXML Label variables (error messages)//
    //*************************//
    @FXML
    private Label errorNoField;
    @FXML
    private Label errorNoPatient;

    //*********************//
    //FXML Button Variables//
    //*********************//
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnSearch;

    //*********************//
    //FXML Label Variables //
    //*********************//
    @FXML
    Label doctorName;
    @FXML
    Label doctorLicense;

    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnLogout(ActionEvent event) throws Exception {
        goToLoginPage(event, dataAccessObject);
    }

    @FXML
    public void handleBtnSearch(ActionEvent event) throws Exception {
        String ramqCode = tfRamqCode.getText();
        if (ramqCode.isEmpty()) {
            errorNoPatient.setVisible(false);
            errorNoField.setVisible(true);
        }
        else {
            Director director = new Director();
            PatientFileBuilder builder = new PatientFileBuilder(ramqCode, dataAccessObject);
            if(dataAccessObject.patientExistsInDB(ramqCode)) {
                patientFile = director.buildPatientFile(builder, ramqCode);
            } else {
                patientFile = null;
            }
            if (patientFile != null) {
                goToSearchResults(event);
            } else {
                errorNoField.setVisible(false);
                errorNoPatient.setVisible(true);
            }
        }
    }

    //**************//
    //Public Methods//
    //**************//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setResources(Doctor doctor, DataAccessObject dataAccessObject) {
        doctorName.setText("Dr. " + doctor.getLastName());
        doctorLicense.setText("License # " + doctor.getLicense());
        this.doctor = doctor;
        this.dataAccessObject = dataAccessObject;
    }

    //***************//
    //Private Methods//
    //***************//
    /**
     * Goes to the login page, passing any required data.
     * Since the doctor object is not passed, it will simply be reset to null.
     *
     * @param event The ActionEvent
     * @param dataAccessObject The DataAccessObject
     * @throws IOException
     */
    private void goToLoginPage(ActionEvent event,
                               DataAccessObject dataAccessObject) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/login.fxml"));
        root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setResources(dataAccessObject);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Goes to the login page, passing any required data.
     * Since the doctor object is not passed, it will simply be reset to null.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToSearchResults(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                                           .getResource("/Application/searchResults.fxml"));
        root = loader.load();
        SearchResultsController searchResultsController = loader.getController();
        searchResultsController.setResources(doctor, patientFile, null,
                                             null, dataAccessObject);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
