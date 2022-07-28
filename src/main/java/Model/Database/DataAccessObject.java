package Model.Database;
import Model.ContactInformation.ContactInformation;
import Model.PatientFile.Gender;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataAccessObject {


    /**
     * This method makes a query to the in order to get all patient files
     * @return
     */
    public ObservableList<PatientFile> getPatientFiles(){
        ObservableList<PatientFile> patientFiles = FXCollections.observableArrayList();
        String query = "SELECT * FROM PatientFiles";
        patientFiles = getPatientFileDB(query);
        return patientFiles;
    }

    private ObservableList<PatientFile> getPatientFileDB(String query){
        Statement statement;
        ResultSet resultSet;
        ObservableList<PatientFile> patientFiles = FXCollections.observableArrayList();
        Connection conn = DBConnection.getInstance().getConnection();
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
                        resultSet.getString("birthCity"),
                        LocalDate.parse(resultSet.getString("birthDate")),
                        resultSet.getString("parentsName"));
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
     * This method makes a query to the database in order to get all medical visits of a patient file.
     * @return
     */ // TODO : showMedicalVisits function in MaintController.(need to convert ArrayList to ObservableList)
    public List<MedicalVisit> getMedicalVisits(String ramqCode){
        List<MedicalVisit> medicalVisits; // On veut Arraylist. Vieux : FXCollections.observableArrayList();
        String query = "SELECT * FROM MedicalVisits WHERE patientRamqCode = " + ramqCode;
        medicalVisits = getMedicalVisitDB(query);
        return medicalVisits;
    }

    private List<MedicalVisit> getMedicalVisitDB(String query){
        Statement statement;
        ResultSet resultSet;
        List<MedicalVisit> visits = new ArrayList<>();//FXCollections.observableArrayList();
        Connection conn = DBConnection.getInstance().getConnection();
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            MedicalVisit visit;
            while(resultSet.next()) {
                int doctorLicense = resultSet.getInt("doctorLicense");

                // Get doctor name for that visit.
                ResultSet doctorNameRS = getDoctorName(doctorLicense, conn);
                String doctorName = doctorNameRS.getString("firstName")
                        + " " + doctorNameRS.getString("lastName");

                // Get establishment name for that visit.
                ResultSet establishmentNameRS = getEstablishmentName(doctorLicense, conn);
                String establishmentName = establishmentNameRS.getString("name");

                visit = new MedicalVisit(
                        establishmentName,
                        doctorName,
                        doctorLicense,
                        LocalDate.parse(resultSet.getString("visitDate")),
                        resultSet.getString("diagnosis"),
                        resultSet.getString("treatment"),
                        resultSet.getString("summary"),
                        resultSet.getString("notes"));
                visits.add(visit);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return visits;
    }

    /**
     * This method makes a query to the database in order to get all medical histories of a patient file.
     * @return
     */ // TODO : showMedicalHistories function in MainController.(need to convert ArrayList to ObservableList)
    public List<MedicalHistory> getMedicalHistories(String ramqCode){
        List<MedicalHistory> medicalHistories;
        String query = "SELECT * FROM MedicalVisits WHERE patientRamqCode = " + ramqCode;
        medicalHistories = getMedicalHistoryDB(query);
        return medicalHistories;
    }

    private List<MedicalHistory> getMedicalHistoryDB(String query){
        Statement statement;
        ResultSet resultSet;
        List<MedicalHistory> histories = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            MedicalHistory history;
            while(resultSet.next()) {
                int doctorLicense = resultSet.getInt("doctorLicense");

                // Get doctor name for that history.
                ResultSet doctorNameRS = getDoctorName(doctorLicense, conn);
                String doctorName = doctorNameRS.getString("firstName")
                        + " " + doctorNameRS.getString("lastName");

                history = new MedicalHistory(
                        resultSet.getString("diagnosis"),
                        resultSet.getString("treatment"),
                        doctorName,
                        doctorLicense,
                        LocalDate.parse(resultSet.getString("startDate")),
                        LocalDate.parse(resultSet.getString("endDate")));
                histories.add(history);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return histories;
    }

    /**
     * Gets the doctor's first and last name from his license number.
     * @param license
     * @return
     */
    private ResultSet getDoctorName(int license, Connection conn) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            String queryDoctorName = "SELECT firstName, lastName FROM Users WHERE id = " +
                    "(SELECT userId FROM Doctors WHERE license = " + license;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(queryDoctorName);
        } catch ( Exception ex) {
            ex.printStackTrace();
        }

        return resultSet;
    }

    /**
     * Gets the establishment's name from a doctor's license.
     * @param license
     * @return
     */
    private ResultSet getEstablishmentName(int license, Connection conn) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            String queryEstablishmentName = "SELECT Name FROM MedicalEstablishments WHERE id = " +
                    "(SELECT medicalEstablishmentId FROM Doctors WHERE license = " + license;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(queryEstablishmentName);
        } catch ( Exception ex) {
            ex.printStackTrace();
        }

        return resultSet;
    }

    /**
     * This method makes a query to the database in order to get the contact information of a patient.
     * @return
     */
    public ContactInformation getContactInformation(String ramqCode){
        ContactInformation contactInformation = null;
        String query = "SELECT * FROM ContactInformation WHERE id = " +
                "(SELECT contactInfoId FROM PatientFiles WHERE ramqCode = " + ramqCode;

        Connection conn = DBConnection.getInstance().getConnection();
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            contactInformation = new ContactInformation(
                    resultSet.getInt("number"),
                    resultSet.getString("street"),
                    resultSet.getString("city"),
                    resultSet.getString("postalCode"),
                    resultSet.getString("phone"),
                    resultSet.getString("email"));

        } catch ( Exception ex) {
            ex.printStackTrace();
        }

        return contactInformation;
    }


    /**
     * This method adds to the MedicalVisits table a Medical visit
     * with the following informations
     * @param ramqCode
     * @param doctorLicense
     * @param visitDate
     * @param diagnosis
     * @param treatment
     * @param summary
     * @param notes
     */
    public void addMedicalVisit(String ramqCode, String doctorLicense, String visitDate,
                                    String diagnosis, String treatment, String summary,
                                        String notes){
        String query =  "INSERT INTO MedicalVisits VALUES (" + ramqCode + "','" +
                                    doctorLicense +  "','" + visitDate + "','" + diagnosis +  "','"
                                            + treatment + "','" + summary + "','" + notes +"')";
        executeQuery(query);
    }

    /**
     * This methods adds to the MedicalHistories table a MedicalHistory
     * with the following informations
     * @param ramqCode
     * @param doctorLicense
     * @param diagnosis
     * @param treatment
     * @param startDate
     * @param endDate
     */
    public void addMedicalHistory(String ramqCode, int doctorLicense, String diagnosis,
                                    String treatment, String startDate, String endDate){
        String query =  "INSERT INTO MedicalHistories VALUES (" + ramqCode + "','" +
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
