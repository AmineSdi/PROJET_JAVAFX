package Model.Database;
import Model.ContactInformation.ContactInformation;
import Model.ContactInformation.MedicalEstablishment;
import Model.PatientFile.Gender;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import Model.User.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataAccessObject {

    /**
     * Checks if the patient RAMQ code exists in the database.
     * @param ramqCode The patient RAMQ code
     * @return true if it exists, false otherwise.
     */
    public boolean patientExistsInDB(String ramqCode) {
        String query = "SELECT * FROM PatientFiles WHERE ramqCode = \""
                + ramqCode + "\" LIMIT 1";
        boolean isFound = false;
        Connection conn = DBConnection.getInstance().getConnection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            isFound = resultSet.isBeforeFirst();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return isFound;
    }

    public HashMap<String, String> getPatientFileInfoFromDB(String ramqCode) {
        String userQuery = "SELECT * FROM PatientFiles WHERE ramqCode = \""
                           + ramqCode + "\" LIMIT 1";
        boolean isFound = false;
        HashMap<String, String> result = null;

        Connection conn = DBConnection.getInstance().getConnection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(userQuery);
            isFound = resultSet.isBeforeFirst();

            if (isFound) {
                result = new HashMap<String, String>();
                result.put("ramqCode", resultSet.getString("ramqCode"));
                result.put("firstName", resultSet.getString("firstName"));
                result.put("lastName", resultSet.getString("lastName"));
                result.put("gender", resultSet.getString("gender"));
                result.put("birthCity", resultSet.getString("birthCity"));
                result.put("birthDate", resultSet.getString("birthDate"));
                result.put("parentsName", resultSet.getString("parentsName"));
                result.put("contactInfoId", resultSet.getString("contactInfoId"));
            }
        } catch ( Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }


    /**
     * Searches the database for a username and password.
     * @param username The username
     * @param password The password
     * @return The doctor associated with that username and password.
     */
    public Doctor findUsernameAndPassword(String username, String password) {
        String userQuery = "SELECT * FROM Users WHERE username = \""
                           + username + "\" AND password = \"" + password + "\"";
        boolean isFound = false;
        Doctor doctor = null;

        Connection conn = DBConnection.getInstance().getConnection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(userQuery);
            isFound = resultSet.isBeforeFirst();

            if (isFound) {
                int userId = resultSet.getInt("id");
                doctor = getDoctor(userId, resultSet, conn);
            }
        } catch ( Exception ex) {
            ex.printStackTrace();
        }

        return doctor;
    }

    /**
     * This method makes a query to the in order to get all patient files
     * @return
     */
    public ObservableList<PatientFile> getPatientFiles() {
        ObservableList<PatientFile> patientFiles = FXCollections.observableArrayList();
        String query = "SELECT * FROM PatientFiles";
        patientFiles = getPatientFileDB(query);
        return patientFiles;
    }

    private ObservableList<PatientFile> getPatientFileDB(String query) {
        Statement statement;
        ResultSet resultSet;
        ObservableList<PatientFile> patientFiles = FXCollections.observableArrayList();
        Connection conn = DBConnection.getInstance().getConnection();
        try {
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
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return patientFiles;
    }
    /**
     * This method makes a query to the database in order to get all medical visits of a patient file.
     * @return
     */ // TODO : showMedicalVisits function in MaintController.(need to convert ArrayList to ObservableList)
    public List<MedicalVisit> getMedicalVisits(String ramqCode) {
        List<MedicalVisit> medicalVisits; // On veut Arraylist. Vieux : FXCollections.observableArrayList();
        String query = "SELECT * FROM MedicalVisits WHERE patientRamqCode = \"" + ramqCode + "\"";
        medicalVisits = getMedicalVisitDB(query);
        return medicalVisits;
    }

    private List<MedicalVisit> getMedicalVisitDB(String query) {
        Statement statement;
        ResultSet resultSet;
        List<MedicalVisit> visits = new ArrayList<>();//FXCollections.observableArrayList();
        Connection conn = DBConnection.getInstance().getConnection();
        try {
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
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return visits;
    }

    /**
     * This method makes a query to the database in order to get all medical histories of a patient file.
     * @return
     */ // TODO : showMedicalHistories function in MainController.(need to convert ArrayList to ObservableList)
    public List<MedicalHistory> getMedicalHistories(String ramqCode) {
        List<MedicalHistory> medicalHistories;
        String query = "SELECT * FROM MedicalHistories WHERE patientRamqCode = \"" + ramqCode + "\"";
        medicalHistories = getMedicalHistoryDB(query);
        return medicalHistories;
    }

    private List<MedicalHistory> getMedicalHistoryDB(String query) {
        Statement statement;
        ResultSet resultSet;
        List<MedicalHistory> histories = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            MedicalHistory history;
            while(resultSet.next()) {
                int doctorLicense = resultSet.getInt("doctorLicense");

                // Get doctor name for that history.
                ResultSet doctorNameRS = getDoctorName(doctorLicense, conn);
                String doctorName = doctorNameRS.getString("firstName")
                                    + " " + doctorNameRS.getString("lastName");

                String startDate = resultSet.getString(("startDate"));
                LocalDate localStartDate = null;
                if (startDate != null)
                    localStartDate = LocalDate.parse(startDate);
                String endDate = resultSet.getString(("endDate"));
                LocalDate localEndDate = null;
                if (endDate != null) {
                    localEndDate = LocalDate.parse(endDate);
                }
                history = new MedicalHistory(
                    resultSet.getString("diagnosis"),
                    resultSet.getString("treatment"),
                    doctorName,
                    doctorLicense,
                    localStartDate,
                    localEndDate);
                histories.add(history);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return histories;
    }

    /**
     * Gets a doctor from a license number.
     * @param userId
     * @return
     */
    private Doctor getDoctor(int userId, ResultSet userResultSet,Connection conn) {
        Statement statement;
        ResultSet resultSet = null;
        Doctor doctor = null;
        try {
            String queryDoctorName = "SELECT * FROM Doctors WHERE userId = " + userId;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(queryDoctorName);

            MedicalEstablishment medicalEstablishment =
                getMedicalEstablishment(resultSet.getInt("medicalEstablishmentId"),
                                        conn);

            doctor = new Doctor(
                userId,
                userResultSet.getString("firstName"),
                userResultSet.getString("lastName"),
                userResultSet.getString("username"),
                userResultSet.getString("password"),
                resultSet.getInt("license"),
                resultSet.getString("specialty"),
                medicalEstablishment
            );

        } catch ( Exception ex) {
            ex.printStackTrace();
        }

        return doctor;
    }

    public ObservableList<MedicalVisit> getObservableVisitsList(String ramqCode){
        ObservableList<MedicalVisit> medicalVisits = FXCollections.observableArrayList();
        String query = "SELECT * FROM MedicalVisits WHERE patientRamqCode = \"" + ramqCode + "\"";
        medicalVisits = getObservableVisitsDB(query);
        return medicalVisits;
    }

    private ObservableList<MedicalVisit> getObservableVisitsDB(String query){
            Statement statement;
            ResultSet resultSet;
            ObservableList<MedicalVisit> visits = FXCollections.observableArrayList();
            Connection conn = DBConnection.getInstance().getConnection();
            try {
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
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            return visits;
    }

    public ObservableList<MedicalHistory> getObservableHistoryList(String ramqCode){
        ObservableList<MedicalHistory> medicalHistory = FXCollections.observableArrayList();
        String query = "SELECT * FROM MedicalHistories WHERE patientRamqCode = \"" + ramqCode + "\"";
        medicalHistory = getObservableHistoryDB(query);
        return medicalHistory;
    }

    private ObservableList<MedicalHistory> getObservableHistoryDB(String query){
        Statement statement;
        ResultSet resultSet;
        ObservableList<MedicalHistory> historyObservableList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getInstance().getConnection();
        try {
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
                historyObservableList.add(history);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return historyObservableList;
    }

    /**
     * Gets a MedicalEstablishment from an ID.
     * @param id
     * @return
     */
    private MedicalEstablishment getMedicalEstablishment(int id, Connection conn) {
        Statement statement;
        ResultSet resultSet = null;
        MedicalEstablishment medicalEstablishment = null;
        try {
            String query = "SELECT * FROM MedicalEstablishments WHERE id = " + id;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            ContactInformation contactInformation =
                getContactInformation(resultSet.getInt("contactInfoId"), conn);

            medicalEstablishment = new MedicalEstablishment(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                contactInformation
            );

        } catch ( Exception ex) {
            ex.printStackTrace();
        }


        return medicalEstablishment;
    }

    /**
     * Gets a ContactInformation from an ID.
     * @param id
     * @return
     */
    private ContactInformation getContactInformation(int id, Connection conn) {
        Statement statement;
        ResultSet resultSet;
        ContactInformation contactInformation = null;
        try {
            String query = "SELECT * FROM ContactInformation WHERE id = " + id;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            contactInformation = new ContactInformation(
                resultSet.getInt("number"),
                resultSet.getString("street"),
                resultSet.getString("city"),
                resultSet.getString("postalCode"),
                resultSet.getString("phone"),
                resultSet.getString("email")
            );

        } catch ( Exception ex) {
            ex.printStackTrace();
        }

        return contactInformation;
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
                                     "(SELECT userId FROM Doctors WHERE license = " + license + ")";
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
                                            "(SELECT medicalEstablishmentId FROM Doctors WHERE license = " + license + ")";
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
    public ContactInformation getContactInformation(String ramqCode) {
        ContactInformation contactInformation = null;
        String query = "SELECT * FROM ContactInformation WHERE id = " +
                       "(SELECT contactInfoId FROM PatientFiles WHERE ramqCode = \"" + ramqCode + "\")";

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
     * @param medicalVisit
     */
    public void addMedicalVisit(String ramqCode, MedicalVisit medicalVisit) {
        int doctorLicense = medicalVisit.getDoctorLicense();
        String visitDate = medicalVisit.getVisitDate().toString();
        String diagnosis = medicalVisit.getDiagnosis();
        String treatment = medicalVisit.getTreatment();
        String summary = medicalVisit.getSummary();
        String notes = medicalVisit.getNotes();
        String query =  "INSERT INTO MedicalVisits(patientRamqCode," +
                        " doctorLicense, visitDate, diagnosis, treatment, summary, notes) " +
                        " VALUES ('" + ramqCode + "','" +
                        doctorLicense +  "','" + visitDate + "','" + diagnosis +  "','"
                        + treatment + "','" + summary + "','" + notes +"')";
        executeQuery(query);
    }


    /**
     * This methods adds a MedicalHistory to the MedicalHistories table.
     *
     * @param ramqCode
     * @param medicalHistory
     */
    public void addMedicalHistory(String ramqCode, MedicalHistory medicalHistory) {
        int doctorLicense = medicalHistory.getDoctorLicense();
        String diagnosis = medicalHistory.getDiagnosis();
        String treatment = medicalHistory.getTreatment();
        LocalDate startDate = medicalHistory.getStartDate();
        LocalDate endDate = medicalHistory.getEndDate();

        String query =  "INSERT INTO MedicalHistories(patientRamqCode, doctorLicense," +
                        "diagnosis, treatment, startDate, endDate) VALUES ('" +
                        ramqCode + "','" + doctorLicense +  "','" + diagnosis + "','" +
                        treatment +  "','" + startDate;
        if (endDate != null)
            query = query + "','" + endDate + "')";
        else
            query = query + "', NULL)";
        executeQuery(query);
    }
    /**
     * This method
     * @param query
     */
    private void executeQuery(String query) {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
