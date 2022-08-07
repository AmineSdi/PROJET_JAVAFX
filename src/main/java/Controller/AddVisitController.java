package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Database.DataAccessObject;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private DataAccessObject dataAccessObject = new DataAccessObject();
    private Doctor doctor;
    private PatientFile patientFile;
    private MedicalVisit medicalVisit;
    private MedicalHistory medicalHistory;
    @FXML
    private AnchorPane AnchorPane;
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

    //*************************//
    // FXML Label variables (error messages)//
    //*************************//
    @FXML
    private Label errorCompleteMV;

    //*************************//
    // FXML imageView variables (error messages)//
    //*************************//
    @FXML
    private ImageView errorCaution1;

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
        updateMedicalVisit();
        if(tfDiagnosis.getText().isEmpty()) {
            tfDiagnosis.setStyle("-fx-border-color: red");
        } else {
            tfDiagnosis.setStyle("-fx-border-color: #66adff");
        }
        if(tfTreatment.getText().isEmpty()) {
            tfTreatment.setStyle("-fx-border-color: red");
        } else {
            tfTreatment.setStyle("-fx-border-color: #66adff");
        }
        if(tfSummary.getText().isEmpty()) {
            tfSummary.setStyle("-fx-border-color: red");
        } else {
            tfSummary.setStyle("-fx-border-color: #66adff");
        }
        if(tfNote.getText().isEmpty()) {
            tfNote.setStyle("-fx-border-color: red");
        } else {
            tfNote.setStyle("-fx-border-color: #66adff");
        }
        if (tfDiagnosis.getText().isEmpty() || tfTreatment.getText().isEmpty()
                || tfSummary.getText().isEmpty() || tfNote.getText().isEmpty()) {
            errorMessageMV();
        } else {
            goToSearchResultsPage(event);
        }
    }

    @FXML
    public void handleBtnCancelAddMV(ActionEvent event) throws Exception {
        if (confirmClear()) {
            this.medicalVisit = null;
            goToSearchResultsPage(event);
        }
    }
    //**************//
    //Public Methods//
    //**************//
    @FXML
    public void errorMessageMV()  {
        errorCompleteMV.setVisible(true);
        errorCaution1.setVisible(true);
    }

    /**
    * Updates the local MedicalVisit Object
    */
    @FXML
    public void updateMedicalVisit() {
        medicalVisit = new MedicalVisit();
        doctor.setVisitDiagnosis(tfDiagnosis.getText());
        doctor.setVisitTreatment(tfTreatment.getText());
        doctor.setVisitNotes(tfNote.getText());
        doctor.setVisitSummary(tfSummary.getText());
        medicalVisit.accept(doctor);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setResources(Doctor doctor, PatientFile patientFile, MedicalVisit medicalVisit,
                             MedicalHistory medicalHistory, DataAccessObject dataAccessObject) {
        this.doctor = doctor;
        this.patientFile = patientFile;
        this.medicalVisit = medicalVisit;
        this.medicalHistory = medicalHistory;
        this.dataAccessObject = dataAccessObject;
        if(medicalVisit != null) {
            tfDiagnosis.setText(medicalVisit.getDiagnosis());
            tfTreatment.setText(medicalVisit.getTreatment());
            tfSummary.setText(medicalVisit.getSummary());
            tfNote.setText(medicalVisit.getNotes());
        }
    }

    //***************//
    //Private Methods//
    //***************//
    /**
     * This function creates a pop-up box asking if the user is certain of wanting to clear the
     * local changes to the medical visit.
     * @return A boolean.
     */
    private boolean confirmClear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Medical program");
        alert.setHeaderText("Erase all local changes to this medical visit?");
        alert.setContentText("Click OK to erase, click cancel to return.");
        if(alert.showAndWait().get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    /**
     * Goes to the search results, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToSearchResultsPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                                           .getResource("/Application/searchResults.fxml"));
        root = loader.load();
        SearchResultsController searchResultsController = loader.getController();
        searchResultsController.setResources(doctor, patientFile, medicalVisit,
                                             medicalHistory, dataAccessObject);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
