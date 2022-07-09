package Application;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import java.util.Date;
import PatientFile.Gender;

import ContactInformation.ContactInformation;
import PatientFile.PatientFile;
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

    @FXML
    private TextField tfRamqCode;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TableView<PatientFile> tvPatientFile;
    @FXML
    private TableColumn<PatientFile, String> colRamqCode;
    @FXML
    private TableColumn<PatientFile, String> colFirstName;
    @FXML
    private TableColumn<PatientFile, String> colLastName;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPatientFiles();
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
                        new Date(),
                        resultSet.getString("birthCity"),
                        resultSet.getString("parentsName"),
                        new ContactInformation());
                patientFiles.add(file);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return patientFiles;
    }

    public void showPatientFiles(){
        ObservableList<PatientFile> list = getPatientFiles();

        colRamqCode.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("ramqCode"));
        colLastName.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("firstName"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("lastName"));

        tvPatientFile.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO PatientFiles VALUES (" + tfRamqCode.getText() + ",'" + tfFirstName.getText() + "','" + tfLastName.getText() + "')";
        executeQuery(query);
        showPatientFiles();
    }
    private void updateRecord(){
        String query = "UPDATE PatientFiles SET firstName  = '" + tfFirstName.getText() + "', lastName = '" + tfLastName.getText() + "' WHERE ramqCode = " + tfRamqCode.getText() + "";
        executeQuery(query);
        showPatientFiles();
    }
    private void deleteButton(){
        String query = "DELETE FROM PatientFiles WHERE ramqCode =" + tfRamqCode.getText() + "";
        executeQuery(query);
        showPatientFiles();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}