package Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ResourceBundle;
import Model.Database.DataAccessObject;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * This class handles ''WHAT'' and will provide information
 */
public class AddHistoryController implements Initializable {
    //Private variables
    private Stage stage;
    private Scene scene;
    private Parent root;
    DataAccessObject dataAccessObject;
    Doctor doctor;
    PatientFile patientFile;
    MedicalVisit medicalVisit;
    MedicalHistory medicalHistory;



    //*************************//
    // FXML TextField variables//
    //*************************//
    @FXML
    private TextField tfDiagnosis;
    @FXML
    private TextField tfTreatment;
    @FXML
    private DatePicker dpStartDate;
    @FXML
    private DatePicker dpEndDate;

    //*********************//
    //FXML Button Variables//
    //*********************//
    @FXML
    private Button btnSaveMH;
    @FXML
    private Button btnCancelAddMH;
    @FXML
    private Button btnBackMH;



    //*********************//
    //Handle Button Methods//
    //*********************//
    @FXML
    public void handleBtnSaveMH(ActionEvent event) throws Exception {
        medicalHistory = new MedicalHistory();
        doctor.setHistoryDiagnosis(tfDiagnosis.getText());
        doctor.setHistoryTreatment(tfTreatment.getText());
        doctor.setHistoryStartDate(dpStartDate.getValue());
        doctor.setHistoryEndDate(dpEndDate.getValue());//(LocalDate.now()); // For now.

        medicalHistory.accept(doctor);
        if (tfDiagnosis.getText().isEmpty() || tfTreatment.getText().isEmpty()
                || dpStartDate.getValue() == null) {
            // TODO : Print error message to user.
            System.out.println("Please complete medical history.");
        } else {
            goToSearchResultsPage(event);
        }
//        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }
    @FXML
    public void handleBtnCancelAddMH(ActionEvent event) throws Exception {
        goToSearchResultsPage(event);
//        URL url = new File("src/main/resources/Application/searchResults.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(url);
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root, 750, 600));
//        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDateFormat(dpEndDate);
        setDateFormat(dpStartDate);
    }

    /**
     * Ensures that the displayed format of the date is yyyy-MM-dd.
     */
    private void setDateFormat(DatePicker date){
        date.setConverter(new StringConverter<LocalDate>() {
            String format = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

            {
                date.setPromptText(format.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public void setResources(Doctor doctor, PatientFile patientFile, MedicalVisit medicalVisit,
                             MedicalHistory medicalHistory, DataAccessObject dataAccessObject) {
        this.doctor = doctor;
        this.patientFile = patientFile;
        this.medicalVisit = medicalVisit;
        this.medicalHistory = medicalHistory;
        this.dataAccessObject = dataAccessObject;
        if(medicalHistory != null) {
            tfDiagnosis.setText(medicalHistory.getDiagnosis());
            tfTreatment.setText(medicalHistory.getTreatment());
            dpStartDate.setValue(medicalHistory.getStartDate());
            dpEndDate.setValue(medicalHistory.getEndDate());
        } else {
            dpStartDate.setValue(LocalDate.now());
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
