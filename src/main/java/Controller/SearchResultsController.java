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
public class SearchResultsController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    DataAccessObject dataAccessObject = new DataAccessObject();
    Doctor doctor;
    PatientFile patientFile;

    //*************************//
    // FXML TextField variables//
    //*************************//
    // TODO : Add fields as needed
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
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
    //FXML TableView and columns variables (MedicalHistory)//
    //*****************************************************//
    //private TableView<MedicalHistory> tvMedicalHistory;
    // private TableColumn<MedicalHistory,String> tcDoctorName;
    // private TableColumn<MedicalHistory,String> tcDiagnosis;
    // private TableColumn<MedicalHistory,String> tcTreatment;
    // private TableColumn<MedicalHistory,String>  tcStartDate;
    // private TableColumn<MedicalHistory,String>  tcEndDate;
    // // FXML tableView and columns variables (MedicalVisit)
    // private TableView<MedicalVisit> tvMedicalVisit;
    // // private TableColumn<MedicalVisit,String> tcDoctorName2;
    // private TableColumn<MedicalVisit,String> tcDate;
    // // private TableColumn<MedicalVisit,String> tcDiagnosis2;
    // // private TableColumn<MedicalVisit,String> tcTreatment2;
    // private TableColumn<MedicalVisit,String> tcSummary;
    // private TableColumn<MedicalVisit,String> tcNote;

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
//        URL url = new File("src/main/resources/Application/ramqSearch.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }
    /**
     * THIS BUTTON NEEDS TO BE FIXED
     * @param event
     * @throws Exception
     */
    @FXML
    public void handleBtnSaveToDB(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnAddMV(ActionEvent event) throws Exception {
        goToAddVisitPage(event);
//        URL url = new File("src/main/resources/Application/addVisit.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }

    @FXML
    public void handleBtnAddMH(ActionEvent event) throws Exception {
        goToAddHistoryPage(event);
//        URL url = new File("src/main/resources/Application/addHistory.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // showPatientFiles();
    }

    public void setResources(Doctor doctor, PatientFile patientFile,
                             DataAccessObject dataAccessObject) {
        this.doctor = doctor;
        this.patientFile = patientFile;
        this.dataAccessObject = dataAccessObject;
        tfRamqCode.setText(patientFile.getRamqCode());
        tfFirstName.setText(patientFile.getFirstName());
        tfLastName.setText(patientFile.getLastName());
        tfCity.setText(patientFile.getBirthCity());
        tfBirthDate.setText(patientFile.getBirthDate().toString());
        tfKnownParents.setText(patientFile.getKnownParents());
        tfPhone.setText(patientFile.getContactInformation().getPhone());
        tfEmail.setText(patientFile.getContactInformation().getEmail());
        tfPostalCode.setText(patientFile.getContactInformation().getPostalCode());
        tfStreet.setText(patientFile.getContactInformation().getStreet());
    }

    /**
     * Goes to the add visit page, passing any required data.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToAddVisitPage(ActionEvent event) throws IOException {
        // Pass data to the next controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/addVisit.fxml"));
        root = loader.load();
        AddVisitController addVisitController = loader.getController();
        addVisitController.setResources(doctor, patientFile, dataAccessObject);

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
        // Pass data to the next controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/addHistory.fxml"));
        root = loader.load();
        AddHistoryController addHistoryController = loader.getController();
        addHistoryController.setResources(doctor, dataAccessObject);

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
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/ramqSearch.fxml"));
        root = loader.load();
        RamqSearchController ramqSearchController = loader.getController();
        ramqSearchController.setResources(doctor, dataAccessObject);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
