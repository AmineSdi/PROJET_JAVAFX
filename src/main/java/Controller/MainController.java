package Controller;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import java.util.Date;
import Model.PatientFile.Gender;
import Model.PatientFile.MedicalHistory.MedicalVisit;
import Model.ContactInformation.ContactInformation;
import Model.PatientFile.PatientFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {


    // FXML textfield variables
    
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
    private TextField tfSummary;

    @FXML
    private TextField tfNote;

    @FXML
    private TextField tfDate;



    // FXML tableView and columns variables (MedicalHistory)

    
    private TableView<MedicalHistory> tvMedicalHistory;

    private TableColumn<MedicalHistory,String> tcDoctorName;
    
    private TableColumn<MedicalHistory,String> tcDiagnosis;
    
    private TableColumn<MedicalHistory,String> tcTreatment;

    private TableColumn<MedicalHistory,String>  tcStartDate;

    private TableColumn<MedicalHistory,String>  tcEndDate;


    @FXML 
    private TableColumn<MedicalHistory, String> tcFirstName;
    // FXML TableView variables (TODO)

    

    // FXML tableView and columns variables (MedicalVisit)

    private TableView<MedicalVisit> tvMedicalVisit;

    private TableColumn<MedicalVisit,String> tcDoctorName2;

    
    
    // FXML button variables

    @FXML
    private Button btnLogOut; 

    @FXML
    private Button btnMedicalVisit; 

    @FXML
    private Button btnMedicalHistory; 

    @FXML
    private Button btnAddMedicalVisit;

    @FXML
    private Button btnAddMedicalHistory;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSubmit;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        // if(event.getSource() == btnInsert){
        //     insertRecord();
        // }else if (event.getSource() == btnUpdate){
        //     updateRecord();
        // }else if(event.getSource() == btnDelete){
        //     deleteButton();
        // } else if(event.getSource() == btnSubmit) {
        //     submitButton();
        // }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        // showPatientFiles();
    }

    public Connection getConnection() {
        String jdbcUrl = "jdbc:sqlite:MedicalSystem.db";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public ObservableList<PatientFile> getPatientFiles(){
        ObservableList<PatientFile> patientFiles = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM PatientFiles";
        Statement statement;
        ResultSet resultSet;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            PatientFile file;
            while(resultSet.next()){
                file = new PatientFile(
                        resultSet.getString("ramqCode"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        Gender.FEMALE,
                        LocalDate.parse(resultSet.getString("birthDate")),
                        resultSet.getString("birthCity"),
                        resultSet.getString("parentsName"),
                        new ContactInformation(1, null, null, null, null, null)); // Todo: fix
                patientFiles.add(file);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return patientFiles;
    }

    // public void showPatientFiles(){
    //     ObservableList<PatientFile> list = getPatientFiles();

    //     // colRamqCode.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("ramqCode"));
    //     // colLastName.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("firstName"));
    //     // colFirstName.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("lastName"));

    //     tvPatientFile.setItems(list);
    // }
    // private void insertRecord(){
    //     String query = "INSERT INTO PatientFiles VALUES (" + tfRamqCode.getText() + ",'" + tfFirstName.getText() + "','" + tfLastName.getText() + "')";
    //     executeQuery(query);
    //     showPatientFiles();
    // }
    // private void updateRecord(){
    //     String query = "UPDATE PatientFiles SET firstName  = '" + tfFirstName.getText() + "', lastName = '" + tfLastName.getText() + "' WHERE ramqCode = " + tfRamqCode.getText() + "";
    //     executeQuery(query);
    //     showPatientFiles();
    // }
    // private void deleteButton(){
    //     String query = "DELETE FROM PatientFiles WHERE ramqCode =" + tfRamqCode.getText() + "";
    //     executeQuery(query);
    //     showPatientFiles();
    // }

    // private void submitButton(){
       
    //     String query = "SELECT FROM PatientFiles WHERE ramqCode =" + tfRamqCode.getText() + "";
    //     executeQuery(query);
    //     showPatientFiles();
    // }

    // private void executeQuery(String query) {
    //     Connection conn = getConnection();
    //     Statement st;
    //     try{
    //         st = conn.createStatement();
    //         st.executeUpdate(query);
    //     }catch(Exception ex){
    //         ex.printStackTrace();
    //     }
    // }


}