package Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Database.DataAccessObject;
import Model.PatientFile.PatientFile;
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

/**
 * This class handles ''WHAT'' and will provide information
 */
public class AddHistoryController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    DataAccessObject dataAccessObject;
    Doctor doctor;
    PatientFile patientFile;

    //*************************//
    // FXML TextField variables//
    //*************************//

    private TextField tfDiagnostic;
    @FXML
    private TextField tfTreatment;
    @FXML
    private TextField tfStartDate;
    @FXML
    private TextField tfEndDate;
    @FXML
    private TextField tfDoctorLicense;

    //*********************//
    //FXML Button Variables//
    //*********************//
    @FXML
    private Button btnSaveMH;
    @FXML
    private Button btnCancelAddMH;



    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnSaveMH(ActionEvent event) throws Exception {
        goToSearchResultsPage(event);
//        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }
    @FXML
    public void handleBtnCancelAddMH(ActionEvent event) throws Exception {
        goToSearchResultsPage(event);
//        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // showPatientFiles();
    }

    public void setResources(Doctor doctor, DataAccessObject dataAccessObject) {
        this.doctor = doctor;
        this.dataAccessObject = dataAccessObject;
    }

    /**
     * Goes to the search results page, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToSearchResultsPage(ActionEvent event) throws IOException {
        // Pass data to the next controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/searchResults.fxml"));
        root = loader.load();
        SearchResultsController searchResultsController = loader.getController();
        searchResultsController.setResources(doctor, patientFile, null, dataAccessObject);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
