package Controller;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Date;
import Model.Database.DataAccessObject;
import Model.PatientFile.Gender;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.ContactInformation.ContactInformation;
import Model.PatientFile.Gender;
import Model.PatientFile.Gender;
import Model.PatientFile.PatientFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.*;

/**
 * This class handles ''WHAT'' and will provide information
 */
public class MainController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    //*************************//
    // FXML TextField variables//
    //*************************//
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfPassword;
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
    @FXML
    private TextField tfDoctor;
    @FXML
    private TextField tfDiagnostic;
    @FXML
    private TextField tfTreatment;
    @FXML
    private TextArea tfSummary;
    @FXML
    private TextArea tfNote;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfStartDate;
    @FXML
    private TextField tfEndDate;
    @FXML
    private TextField tfDoctorLicense;

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
    private Button btnLogin;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnBackToSearch;
    @FXML
    private Button btnSaveToDB;
    @FXML
    private Button btnAddMedicalVisit;
    @FXML
    private Button btnSaveMV;
    @FXML
    private Button btnCancelAddMV;
    @FXML
    private Button btnAddMedicalHistory;
    @FXML
    private Button btnSaveMH;
    @FXML
    private Button btnCancelAddMH;
    //@FXML
    //private Button btnMedicalVisit;
    //@FXML
    //private Button btnMedicalHistory;

    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnLogin(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/ramqSearch.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnLogout(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnSearch(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnBackToSearch(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/ramqSearch.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    /**
     * THIS BUTTON NEEDS TO BE FIXED
     * @param event
     * @throws Exception
     */
    @FXML
    public void handleBtnSaveToDB(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/searhResults.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnAddMV(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/addVisit.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnSaveMV(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnCancelAddMV(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnAddMH(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/addHistory.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnSaveMH(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }
    @FXML
    public void handleBtnCancelAddMH(ActionEvent event) throws Exception {
        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 750, 600));
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // showPatientFiles();
    }
}
