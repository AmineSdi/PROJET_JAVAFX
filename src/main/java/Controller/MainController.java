package Controller;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import java.util.Date;

import Model.Database.DataAccessObject;
import Model.PatientFile.Gender;

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

/**
 * This class handles ''WHAT'' and will provide information
 */
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

    public void showPatientFiles(){
        DataAccessObject dbAccess = new DataAccessObject();
        ObservableList<PatientFile> list = dbAccess.getPatientFiles();
        colRamqCode.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("ramqCode"));
        colLastName.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("firstName"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("lastName"));
        tvPatientFile.setItems(list);
    }
    private void insertRecord(){
        DataAccessObject dbAccess = new DataAccessObject();
        dbAccess.insertRecord(tfRamqCode.getText(), tfFirstName.getText(), tfLastName.getText());
        showPatientFiles();
    }
    private void updateRecord(){
        DataAccessObject dbAccess = new DataAccessObject();
        dbAccess.updateRecord(tfFirstName.getText(), tfLastName.getText(), tfRamqCode.getText());
        showPatientFiles();
    }

    private void deleteButton(){
        DataAccessObject dbAccess = new DataAccessObject();
        dbAccess.deleteButton(tfRamqCode.getText());
        showPatientFiles();
    }




}