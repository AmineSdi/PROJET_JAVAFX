package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Database.DataAccessObject;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class UpdateHistoryController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    DataAccessObject dataAccessObject;
    Doctor doctor;
    PatientFile patientFile;
    MedicalVisit medicalVisit = null;
    MedicalHistory medicalHistory = null;
    ObservableList<String> historyDiagnosis = FXCollections.observableArrayList();;

    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();

    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnCancel(ActionEvent event) throws Exception {
        goToSearchResultsPage(event);
    }
    /**
     * This button handler sends the local MedicalVisit and/or MedicalHistory to the database,
     * resetting them to null.
     * The local PatientFile object is updated appropriately as well.
     * @param event The ActionEvent.
     * @throws Exception
     */
    @FXML
    public void handleBtnUpdate(ActionEvent event) throws Exception {
        if(medicalVisit != null) {
            dataAccessObject.addMedicalVisit(patientFile.getRamqCode(), medicalVisit);
            patientFile.addMedicalVisit(medicalVisit);
            medicalVisit = null;
        }

        if(medicalHistory != null) {
            dataAccessObject.addMedicalHistory(patientFile.getRamqCode(), medicalHistory);
            patientFile.addMedicalHistory(medicalHistory);
            medicalHistory = null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setResources(Doctor doctor, PatientFile patientFile, MedicalVisit medicalVisit,
                             MedicalHistory medicalHistory, DataAccessObject dataAccessObject,
                             ObservableList<MedicalHistory> historyObservableList) {
        this.doctor = doctor;
        this.patientFile = patientFile;
        this.medicalVisit = medicalVisit;
        this.medicalHistory = medicalHistory;
        this.dataAccessObject = dataAccessObject;

        setDiagnosisList(historyObservableList);

        this.comboBox.setItems(historyDiagnosis);
    }

    private void setDiagnosisList(ObservableList<MedicalHistory> historyObservableList) {
        for (MedicalHistory history : historyObservableList) {
            if (history.getEndDate() == null)
                historyDiagnosis.add(history.getDiagnosis());
        }
    }

    /**
     * Goes to the search results page, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToSearchResultsPage(ActionEvent event) throws IOException {

        // Pass data to the next controller
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
