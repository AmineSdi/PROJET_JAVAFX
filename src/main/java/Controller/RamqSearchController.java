package Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Builder.Director;
import Model.Builder.PatientFileBuilder;
import Model.Database.DataAccessObject;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
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
public class RamqSearchController implements Initializable {
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
    @FXML
    private TextField tfRamqCode;

    //*************************//
    // FXML Label variables (error messages)//
    //*************************//
    @FXML
    private Label errorNoField;
    @FXML
    private Label errorNoPatient;

    //*********************//
    //FXML Button Variables//
    //*********************//
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnSearch;

    //*********************//
    //FXML Label Variables //
    //*********************//
    @FXML
    Label doctorName;
    @FXML
    Label doctorLicense;


    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnLogout(ActionEvent event) throws Exception {
        goToLoginPage(event, dataAccessObject);
    }

    @FXML
    public void handleBtnSearch(ActionEvent event) throws Exception {
        if (tfRamqCode.getText().isEmpty()) {

              errorNoPatient.setVisible(false);
              errorNoField.setVisible(true);

            System.out.println("Please fill all the fields.");
        }
        else {
            Director director = new Director();
            PatientFileBuilder builder = new PatientFileBuilder(tfRamqCode.getText(),
                    dataAccessObject);
            patientFile = director.buildPatientFile(builder, tfRamqCode.getText());

//            Doctor doctor = dataAccessObject.findUsernameAndPassword(tfUserName.getText(), tfPassword.getText());
            if (patientFile != null) {
                goToSearchResults(event);
            } else {

                errorNoField.setVisible(false);
                errorNoPatient.setVisible(true);

                System.out.println("Patient not found.");
            }
        }



//        patientFile = builder.assemble();



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
        doctorName.setText(doctor.getLastName());
        doctorLicense.setText("" + doctor.getLicense());
        this.doctor = doctor;
        this.dataAccessObject = dataAccessObject;
    }

    /**
     * Goes to the login page, passing any required data.
     * Since the doctor object is not passed, it will simply be reset to null.
     *
     * @param event The ActionEvent
     * @param dataAccessObject The DataAccessObject
     * @throws IOException
     */
    private void goToLoginPage(ActionEvent event,
                               DataAccessObject dataAccessObject) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/login.fxml"));
        root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setResources(dataAccessObject);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Goes to the login page, passing any required data.
     * Since the doctor object is not passed, it will simply be reset to null.
     *
     * @param event The ActionEvent
     * @throws IOException
     */
    private void goToSearchResults(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/searchResults.fxml"));
        root = loader.load();
        SearchResultsController searchResultsController = loader.getController();
        searchResultsController.setResources(doctor, patientFile, null, null, dataAccessObject);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
