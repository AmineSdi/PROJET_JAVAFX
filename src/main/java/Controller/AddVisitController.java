package Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Database.DataAccessObject;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class handles ''WHAT'' and will provide information
 */
public class AddVisitController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    DataAccessObject dataAccessObject = new DataAccessObject();
    Doctor doctor;
    PatientFile patientFile;
    MedicalVisit medicalVisit;

    //*************************//
    // FXML TextField variables//
    //*************************//
    @FXML
    private TextField tfDiagnosis;
    @FXML
    private TextField tfTreatment;
    @FXML
    private TextArea tfSummary;
    @FXML
    private TextArea tfNote;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfDoctorLicense;

    //*********************//
    //FXML Button Variables//
    //*********************//
    @FXML
    private Button btnSaveMV;
    @FXML
    private Button btnCancelAddMV;



    //*********************//
    //Handle Button Methods//
    //*********************//
    /**
     * THIS BUTTON NEEDS TO BE FIXED
     * @param event
     * @throws Exception
     */
    @FXML
    public void handleBtnSaveMV(ActionEvent event) throws Exception {
        medicalVisit = new MedicalVisit();
        doctor.setVisitDiagnosis(tfDiagnosis.getText());
        doctor.setVisitTreatment(tfTreatment.getText());
        doctor.setVisitNotes(tfNote.getText());
        doctor.setVisitSummary(tfSummary.getText());
        medicalVisit.accept(doctor);
        goToSearchResultsPage(event);

//        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }

    @FXML
    public void handleBtnCancelAddMV(ActionEvent event) throws Exception {
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

    public void setResources(Doctor doctor, PatientFile patientFile, MedicalVisit medicalVisit,DataAccessObject dataAccessObject) {
        this.doctor = doctor;
        this.patientFile = patientFile;
        this.medicalVisit = medicalVisit;
        this.dataAccessObject = dataAccessObject;
        if(medicalVisit != null){
            tfDiagnosis.setText(medicalVisit.getDiagnosis());
            tfTreatment.setText(medicalVisit.getTreatment());
            tfSummary.setText(medicalVisit.getVisitSummary());
            tfNote.setText(medicalVisit.getNotes());
        }
    }

    /**
     * Goes to the search results, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToSearchResultsPage(ActionEvent event) throws IOException {
        // Pass data to the next controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/searchResults.fxml"));
        root = loader.load();
        SearchResultsController searchResultsController = loader.getController();
        searchResultsController.setResources(doctor, patientFile, medicalVisit, dataAccessObject);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
