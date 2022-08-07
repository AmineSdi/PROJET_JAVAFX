package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Database.DataAccessObject;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * This class handles ''WHAT'' and will provide information
 */
public class SearchResultsController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DataAccessObject dataAccessObject;
    private Doctor doctor;
    private PatientFile patientFile;
    private MedicalVisit medicalVisit = null;
    private MedicalHistory medicalHistory = null;
    private ObservableList<MedicalVisit> visitObservableList;
    private ObservableList<MedicalHistory> historyObservableList;

    //*************************//
    // FXML TextField variables//
    //*************************//
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfGender;
    @FXML
    private TextField tfRamqCode;
    @FXML
    private TextField tfBirthDate;
    @FXML
    private TextField tfKnownParents;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfPostalCode;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfEmail;

    //*****************************************************//
    //FXML TableView and columns variables (MedicalVisit)//
    //*****************************************************//
    @FXML
    private TableView<MedicalVisit> medicalVisitTableView = new TableView<>();
    @FXML
    private TableColumn<MedicalVisit, String> colDateOfVisit = new TableColumn<>("visitDate");
    @FXML
    private TableColumn<MedicalVisit, String> colDiagnosisVisit = new TableColumn<>("diagnosis");
    @FXML
    private TableColumn<MedicalVisit, String> colTreatmentVisit = new TableColumn<>("treatment");
    @FXML
    private TableColumn<MedicalVisit, String> colSummaryVisit = new TableColumn<>("summary");
    @FXML
    private TableColumn<MedicalVisit, String> colNotesVisit = new TableColumn<>("notes");

    //*****************************************************//
    //FXML TableView and columns variables (MedicalHistory)//
    //*****************************************************//
    @FXML
    private TableView<MedicalHistory> medicalHistoryTableView = new TableView<>();
    @FXML
    private TableColumn<MedicalHistory, String> colDiagnosisHistory =
        new TableColumn<>("diagnosis");
    @FXML
    private TableColumn<MedicalHistory, String> colTreatmentHistory =
        new TableColumn<>("treatment");
    @FXML
    private TableColumn<MedicalHistory, String> colStartHistory = new TableColumn<>("startDate");
    @FXML
    private TableColumn<MedicalHistory, String> colEndHistory = new TableColumn<>("endDate");

    //*********************//
    //FXML Button Variables//
    //*********************//
    @FXML
    private Button btnBackToSearch;
    @FXML
    private Button btnSaveToDB;
    @FXML
    private Button btnAddMedicalVisit;
    @FXML
    private Button btnAddMedicalHistory;

    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnBackToSearch(ActionEvent event) throws Exception {
        goToRamqSearchPage(event);
    }

    /**
     * This button handler sends the local MedicalVisit and/or MedicalHistory to the database,
     * resetting them to null.
     * The local PatientFile object is updated appropriately as well.
     * @param event The ActionEvent.
     * @throws Exception
     */
    @FXML
    public void handleBtnSaveToDB(ActionEvent event) throws Exception {
        if(medicalVisit != null) {
            dataAccessObject.addMedicalVisit(patientFile.getRamqCode(), medicalVisit);
            patientFile.addMedicalVisit(medicalVisit);
            showPatientVisits(dataAccessObject, patientFile.getRamqCode());
            alertConfirmSubmit();
        }
        if(medicalHistory != null) {
            dataAccessObject.addMedicalHistory(patientFile.getRamqCode(), medicalHistory);
            patientFile.addMedicalHistory(medicalHistory);
            showPatientHistory(dataAccessObject, patientFile.getRamqCode());
            alertConfirmSubmit();
        }
        if(medicalVisit == null && medicalHistory == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Medical Program");
            alert.setHeaderText(null);
            alert.setContentText("Patient notes incomplete.");
            alert.showAndWait();
        }
        medicalVisit = null;
        medicalHistory = null;

    }

    @FXML
    public void handleBtnAddMV(ActionEvent event) throws Exception {
        goToAddVisitPage(event);
    }

    @FXML
    public void handleBtnAddMH(ActionEvent event) throws Exception {
        goToAddHistoryPage(event);
    }

    @FXML
    public void handleBtnUpdateMH(ActionEvent event) throws Exception {
        goToUpdateHistoryPage(event);
    }

    //**************//
    //Public Methods//
    //**************//
    public void alertConfirmSubmit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Medical Program");
        alert.setHeaderText(null);
        alert.setContentText("Patient notes saved.");
        alert.showAndWait();
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
        setTextFields();
        showPatientVisits(dataAccessObject, patientFile.getRamqCode());
        showPatientHistory(dataAccessObject, patientFile.getRamqCode());
    }

    public void showPatientVisits(DataAccessObject dao, String ramqCode) {
        this.dataAccessObject = dao;
        visitObservableList = dataAccessObject.getObservableVisitsList(ramqCode);
        colDateOfVisit.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        colDiagnosisVisit.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        colTreatmentVisit.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        colSummaryVisit.setCellValueFactory(new PropertyValueFactory<>("summary"));
        colNotesVisit.setCellValueFactory(new PropertyValueFactory<>("notes"));
        medicalVisitTableView.setItems(visitObservableList);
    }


    public void showPatientHistory(DataAccessObject dao, String ramqCode) {
        this.dataAccessObject = dao;
        historyObservableList = dataAccessObject.getObservableHistoryList(ramqCode);
        colDiagnosisHistory.setCellValueFactory(
            new PropertyValueFactory<MedicalHistory, String>("diagnosis"));
        colTreatmentHistory.setCellValueFactory(
            new PropertyValueFactory<MedicalHistory, String>("treatment"));
        colStartHistory.setCellValueFactory(
            new PropertyValueFactory<MedicalHistory, String>("startDate"));
        colEndHistory.setCellValueFactory(
            new PropertyValueFactory<MedicalHistory, String>("endDate"));
        medicalHistoryTableView.setItems(historyObservableList);
    }

    //**************//
    //Private Methods//
    //**************//

    /**
     * Sets the appropriate values to the text fields.
     */
    private void setTextFields() {
        tfRamqCode.setText(patientFile.getRamqCode());
        tfFirstName.setText(patientFile.getFirstName());
        tfLastName.setText(patientFile.getLastName());
        tfGender.setText(patientFile.getGender().toString());
        tfCity.setText(patientFile.getBirthCity());
        tfBirthDate.setText(patientFile.getBirthDate().toString());
        tfKnownParents.setText(patientFile.getKnownParents());
        tfPhone.setText(patientFile.getContactInformation().getPhone());
        tfEmail.setText(patientFile.getContactInformation().getEmail());
        tfPostalCode.setText(patientFile.getContactInformation().getPostalCode());
        tfStreet.setText(patientFile.getContactInformation().getStreet());
        setEditableToFalse();
    }

    /**
     * Makes sure the User can't modify certain text fields.
     */
    private void setEditableToFalse() {
        tfRamqCode.setEditable(false);
        tfFirstName.setEditable(false);
        tfLastName.setEditable(false);
        tfGender.setEditable(false);
        tfCity.setEditable(false);
        tfBirthDate.setEditable(false);
        tfKnownParents.setEditable(false);
        tfPhone.setEditable(false);
        tfEmail.setEditable(false);
        tfPostalCode.setEditable(false);
        tfStreet.setEditable(false);
    }

    /**
     * Goes to the add visit page, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToAddVisitPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                                           .getResource("/Application/addVisit.fxml"));
        root = loader.load();
        AddVisitController addVisitController = loader.getController();
        addVisitController.setResources(doctor, patientFile, medicalVisit, medicalHistory,
                                        dataAccessObject);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Goes to the add history page, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToAddHistoryPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                                           .getResource("/Application/addHistory.fxml"));
        root = loader.load();
        AddHistoryController addHistoryController = loader.getController();
        addHistoryController.setResources(doctor, patientFile, medicalVisit, medicalHistory,
                                          dataAccessObject);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Goes to the update history page, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToUpdateHistoryPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                                           .getResource("/Application/updateHistory.fxml"));
        root = loader.load();
        UpdateHistoryController updateHistoryController = loader.getController();
        updateHistoryController.setResources(doctor, patientFile, medicalVisit, medicalHistory,
                                             dataAccessObject, historyObservableList);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Goes to the RAMQ search page, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToRamqSearchPage(ActionEvent event) throws IOException {
        // Pass data to the next controller
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
