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
import Model.PatientFile.Gender;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.*;
import javax.xml.crypto.Data;

/**
 * This class handles ''WHAT'' and will provide information
 */
public class RamqSearchController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    DataAccessObject dataAccessObject;
    Doctor doctor;

    //*************************//
    // FXML TextField variables//
    //*************************//
    @FXML
    private TextField tfRamqCode;


    //*********************//
    //FXML Button Variables//
    //*********************//
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnSearch;

    @FXML
    Label doctorName;
    @FXML
    Label doctorLicense;


    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnLogout(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/login.fxml"));
        root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setResources(dataAccessObject);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        URL url = new File("src/main/resources/Application/login.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }
    @FXML
    public void handleBtnSearch(ActionEvent event) throws Exception {
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

    public void setResources(Doctor doctor, DataAccessObject dataAccessObject) {
        doctorName.setText(doctor.getLastName());
        doctorLicense.setText("" + doctor.getLicense());
        this.doctor = doctor;
        this.dataAccessObject = dataAccessObject;
    }
}
