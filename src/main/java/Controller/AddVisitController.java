package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Database.DataAccessObject;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    MedicalHistory medicalHistory;

    Timeline automaticUpdate;

    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;

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
        updateMedicalVisit();


        if (tfDiagnosis.getText().isEmpty() || tfTreatment.getText().isEmpty()
                || tfSummary.getText().isEmpty() || tfNote.getText().isEmpty()) {
            // TODO : print error message to user.
            System.out.println("Please complete medical visit.");
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

    /**
     * This function creates a pop-up box asking if the user is certain of wanting to clear the
     * local changes to the medical history.
     * @return A boolean.
     */
    private boolean confirmClear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Medical program");
        alert.setHeaderText("Erase all local changes to this medical visit?");
        alert.setContentText("Click OK to erase, click cancel to return.");

        if(alert.showAndWait().get() == ButtonType.OK) {
            //Code à effectuer lorsque le programme se termine
            stage = (Stage) AnchorPane.getScene().getWindow();
            stage.close();
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // showPatientFiles();
    }

    /**
     * Updates the local MedicalVisit Object
     */
    private void updateMedicalVisit() {
        System.out.println("updated.");
        medicalVisit = new MedicalVisit();
        doctor.setVisitDiagnosis(tfDiagnosis.getText());
        doctor.setVisitTreatment(tfTreatment.getText());
        doctor.setVisitNotes(tfNote.getText());
        doctor.setVisitSummary(tfSummary.getText());
        medicalVisit.accept(doctor);
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

        automaticUpdate = new Timeline(
                new KeyFrame(Duration.seconds(3),
                        event -> updateMedicalVisit()));
        automaticUpdate.setCycleCount(Timeline.INDEFINITE);
        automaticUpdate.play();
    }

    /**
     * Goes to the search results, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToSearchResultsPage(ActionEvent event) throws IOException {
        automaticUpdate.stop();
        automaticUpdate = null;

        // Pass data to the next controller
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/Application/searchResults.fxml"));
        root = loader.load();
        SearchResultsController searchResultsController = loader.getController();
        searchResultsController.setResources(doctor, patientFile, medicalVisit, medicalHistory,
                dataAccessObject);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
