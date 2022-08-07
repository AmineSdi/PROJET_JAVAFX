package Controller;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.image.ImageView;
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
    private DataAccessObject dataAccessObject;
    private Doctor doctor;
    private PatientFile patientFile;
    private MedicalVisit medicalVisit = null;
    private MedicalHistory medicalHistory = null;
    private ObservableList<String> historyDiagnosis = FXCollections.observableArrayList();;
    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();
    @FXML
    private DatePicker dpEndDate;

    //*********************//
    //FXML Label variables (error messages)//
    //*********************//
    @FXML
    private Label lbErrorEmptyDiagnosis;
    @FXML
    private Label lbErrorEmptyEndDate;
    @FXML
    private Label lbErrorEndDateBfStart;

    //*********************//
    //FXML ImageView variable (error messages)//
    //*********************//
    @FXML
    private ImageView errorCaution;

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
        // End date must be same or more than Start date.
        if(comboBox.getValue() == null) {
            comboBox.setStyle("-fx-border-color: red");
            lbErrorEmptyDiagnosis.setVisible(true);
            errorCaution.setVisible(true);
        } else {
            comboBox.setStyle("-fx-border-color: none ; -fx-border-width: 0px ;");
            if (dpEndDate.getValue() == null) {
                dpEndDate.setStyle("-fx-border-color: red");
                lbErrorEmptyDiagnosis.setVisible(false);
                lbErrorEmptyEndDate.setVisible(true);
                errorCaution.setVisible(true);
            } else {
                dpEndDate.setStyle("-fx-border-color: none ; -fx-border-width: 0px ;");
                LocalDate startDate = dataAccessObject.getStartDate(patientFile.getRamqCode(),
                                      doctor.getLicense(), comboBox.getValue());
                if (!startDate.isBefore(dpEndDate.getValue())
                        && !startDate.isEqual(dpEndDate.getValue())) {
                    lbErrorEmptyDiagnosis.setVisible(false);
                    lbErrorEmptyEndDate.setVisible(false);
                    lbErrorEndDateBfStart.setVisible(true);
                    errorCaution.setVisible(true);
                } else {
                    patientFile.updateEndDate(doctor.getLicense(),
                                              comboBox.getValue(), startDate, dpEndDate.getValue());
                    dataAccessObject.updateEndDate(patientFile.getRamqCode(), doctor.getLicense(),
                                                   comboBox.getValue(), startDate, dpEndDate.getValue());
                    goToSearchResultsPage(event);
                }
            }
        }
    }

    //**************//
    //Public Methods//
    //**************//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //enable datepicker when combobox value is selected
        comboBox.setOnAction(e -> {
            if(comboBox.getValue() == null) {
                dpEndDate.setDisable(true);
            } else {
                dpEndDate.setDisable(false);
            }
        });
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

    //**************//
    //Private Methods//
    //**************//
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
