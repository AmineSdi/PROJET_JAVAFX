package Model.Database;
import Model.ContactInformation.ContactInformation;
import Model.PatientFile.Gender;
import Model.PatientFile.PatientFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDate;

public class DataAccessObject {


    /**
     * This method makes a query to the in order to get all patient files
     * @return
     */
    public ObservableList<PatientFile> getPatientFiles(){
        ObservableList<PatientFile> patientFiles = FXCollections.observableArrayList();
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM PatientFiles";
        Statement statement;
        ResultSet resultSet;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            PatientFile file;
            while(resultSet.next()) {
                file = new PatientFile(
                        resultSet.getString("ramqCode"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        Gender.FEMALE,
                        LocalDate.parse(resultSet.getString("birthDate")),
                        resultSet.getString("birthCity"),
                        resultSet.getString("parentsName"),
                        new ContactInformation(201, "PK Avenue", "Montreal", "H2X 3Y7", "(514) 987-3000", "bigluqam.ca")); // Todo: fix
                patientFiles.add(file);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return patientFiles;
    }

    public void insertRecord(String ramqCode, String firstName, String lastName){
        String query = "INSERT INTO PatientFiles VALUES (" + ramqCode +
                                    ",'" + firstName + "','" + lastName + "')";
        executeQuery(query);
    }

    public void updateRecord(String firstName, String lastName, String ramqCode){
        String query = "UPDATE PatientFiles SET firstName  = '" + firstName + "', lastName = '"
                + lastName + "' WHERE ramqCode = " + ramqCode + "";
        executeQuery(query);
    }

    public void deleteButton(String ramqCode){
        String query = "DELETE FROM PatientFiles WHERE ramqCode =" + ramqCode + "";
        executeQuery(query);
    }

    /**
     * This method adds to the MedicalVisits table a Medical visit
     * with the following informations
     * @param id
     * @param ramqCode
     * @param doctorLicense
     * @param visitDate
     * @param diagnosis
     * @param treatment
     * @param summary
     * @param notes
     */
    public void addMedicalVisit(int id, String ramqCode, int doctorLicense, String visitDate,
                                    String diagnosis, String treatment, String summary,
                                        String notes){
        String query =  "INSERT INTO MedicalVisits VALUES (" + id  + ",'" + ramqCode + "','" +
                                    doctorLicense +  "','" + visitDate + "','" + diagnosis +  "','"
                                            + treatment + "','" + summary + "','" + notes +"')";
        executeQuery(query);
    }

    /**
     * This methods adds to the MedicalHistories table a MedicalHistory
     * with the following informations
     * @param id
     * @param ramqCode
     * @param doctorLicense
     * @param diagnosis
     * @param treatment
     * @param startDate
     * @param endDate
     */
    public void addMedicalHistory(int id, String ramqCode, int doctorLicense, String diagnosis,
                                    String treatment, String startDate, String endDate){
        String query =  "INSERT INTO MedicalHistories VALUES (" + id  + ",'" + ramqCode + "','" +
                doctorLicense +  "','" + diagnosis + "','" + treatment +  "','"
                + startDate + "','" + endDate +"')";
        executeQuery(query);
    }
    /**
     * This method
     * @param query
     */
    private void executeQuery(String query) {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement st;
        try{
            st = connection.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
