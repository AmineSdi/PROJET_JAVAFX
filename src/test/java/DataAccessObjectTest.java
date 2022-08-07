import Model.ContactInformation.ContactInformation;
import Model.Database.DataAccessObject;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.User.Doctor;
import Model.PatientFile.Gender;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
public class DataAccessObjectTest {
    private String mockDatabasePath = "jdbc:sqlite:src/test/java/resources/MockDatabase.db";
    DataAccessObject dataAccessObject =
            new DataAccessObject(mockDatabasePath);
    Connection connection;
    private String cleanUpQuery =
            "DROP Table IF EXISTS MedicalHistories;\n" +
                    "DROP Table IF EXISTS MedicalVisits;\n" +
                    "DROP Table IF EXISTS Doctors;\n" +
                    "DROP Table IF EXISTS MedicalEstablishments;\n" +
                    "DROP Table IF EXISTS PatientFiles;\n" +
                    "DROP Table IF EXISTS Users;\n" +
                    "DROP Table IF EXISTS ContactInformation;\n" +
                    "\n" +
                    "CREATE Table Users (\n" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "   firstName varchar(30) NOT NULL,\n" +
                    "   lastName varchar(30) NOT NULL,\n" +
                    "   username text NOT NULL UNIQUE,\n" +
                    "   password text NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "CREATE Table ContactInformation (\n" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    number INTEGER NOT NULL,\n" +
                    "    street varchar(30),\n" +
                    "    city varchar (30),\n" +
                    "    postalCode varchar (6), \n" +
                    "    phone varchar(13),\n" +
                    "    email varchar(30)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE Table MedicalEstablishments(\n" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    name varchar(150) NOT NULL, \n" +
                    "    contactInfoId INTEGER NOT NULL, \n" +
                    "    FOREIGN KEY (contactInfoId) REFERENCES ContactInformation(id)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE Table Doctors (\n" +
                    "   license INTEGER(5) PRIMARY KEY,\n" +
                    "   specialty varchar(50) NOT NULL,\n" +
                    "   medicalEstablishmentId INTEGER NOT NULL,\n" +
                    "   userId INTEGER NOT NULL,\n" +
                    "   FOREIGN KEY (medicalEstablishmentId) REFERENCES MedicalEstablishments(id),\n" +
                    "   FOREIGN KEY (userId) REFERENCES Users(id)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE Table PatientFiles (\n" +
                    "   ramqCode varchar(12) PRIMARY KEY,\n" +
                    "   firstName varchar(30) NOT NULL,\n" +
                    "   lastName varchar(30) NOT NULL,\n" +
                    "   gender text CHECK( gender IN ('FEMALE','MALE','OTHER') ) NOT NULL,\n" +
                    "   birthCity varchar(30) NOT NULL,\n" +
                    "   birthDate text NOT NULL,\n" +
                    "   parentsName varchar(80) NOT NULL,\n" +
                    "   contactInfoId INTEGER NOT NULL, \n" +
                    "   FOREIGN KEY (contactInfoId) REFERENCES ContactInformation(id)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE Table MedicalHistories (\n" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "   patientRamqCode varchar(12) NOT NULL,\n" +
                    "   doctorLicense INTEGER (5) NOT NULL,\n" +
                    "   diagnosis varchar(50) NOT NULL,\n" +
                    "   treatment varchar(50) NOT NULL,\n" +
                    "   startDate text NOT NULL,\n" +
                    "   endDate text, \n" +
                    "   FOREIGN KEY (patientRamqCode) REFERENCES PatientFiles(ramqCode),\n" +
                    "   FOREIGN KEY (doctorLicense) REFERENCES Doctors(license),\n" +
                    "   unique (patientRamqCode, doctorLicense, diagnosis, startDate) ON CONFLICT IGNORE\n" +
                    ");\n" +
                    "\n" +
                    "CREATE Table MedicalVisits (\n" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "   patientRamqCode varchar(12) NOT NULL,\n" +
                    "   doctorLicense INTEGER (5) NOT NULL,\n" +
                    "   visitDate text NOT NULL,\n" +
                    "   diagnosis varchar(50) NOT NULL,\n" +
                    "   treatment varchar(50) NOT NULL,\n" +
                    "   summary text NOT NULL,\n" +
                    "   notes text NOT NULL, \n" +
                    "   FOREIGN KEY (patientRamqCode) REFERENCES PatientFiles(ramqCode),\n" +
                    "   FOREIGN KEY (doctorLicense) REFERENCES Doctors(license),\n" +
                    "   unique (patientRamqCode, visitDate, diagnosis, treatment, summary, notes) ON CONFLICT IGNORE\n" +
                    ");";

    @BeforeEach
    public void initialize() throws SQLException {
        connection = getConnection();
        executeQuery(cleanUpQuery);
        connection.close();
    }

//    @AfterEach
//    public void cleanUp() throws SQLException {
//        connection = getConnection();
//        executeQuery(cleanUpQuery);
//        connection.close();
//    }

    @Test
    public void addMedicalVisitTest() throws SQLException {
        connection = getConnection();
        MedicalVisit mv = new MedicalVisit("est", "House", 11111, LocalDate.now(), "dia", "tre", "sum", "not");
        dataAccessObject.addMedicalVisit("ALLA60050501", mv);
        Statement statement;
        ResultSet resultSet;
        try {
            String selectQuery = "select * from MedicalVisits;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);
            assertEquals("ALLA60050501", resultSet.getString("patientRamqCode"));
            assertEquals(LocalDate.now().toString(), resultSet.getString("visitDate"));
            assertEquals("dia", resultSet.getString("diagnosis"));
            assertEquals("tre", resultSet.getString("treatment"));
            assertEquals("sum", resultSet.getString("summary"));
            assertEquals("not", resultSet.getString("notes"));
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void addMedicalHistoryTest() throws SQLException {
        connection = getConnection();
        MedicalHistory mh = new MedicalHistory("dia", "tre",
                "House", 11111, LocalDate.now(), LocalDate.now());
        dataAccessObject.addMedicalHistory("ALLA60050501", mh);
        Statement statement;
        ResultSet resultSet;

        try {
            String selectQuery = "select * from MedicalHistories;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);
            assertEquals("ALLA60050501", resultSet.getString("patientRamqCode"));
            assertEquals(LocalDate.now().toString(), resultSet.getString("startDate"));
            assertEquals(LocalDate.now().toString(), resultSet.getString("endDate"));
            assertEquals("dia", resultSet.getString("diagnosis"));
            assertEquals("tre", resultSet.getString("treatment"));
            assertEquals(11111, resultSet.getInt("doctorLicense"));
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        connection.close();
    }

    public void patientExistsInDBTest() throws SQLException{
        connection = getConnection();
        Statement statement;
        boolean testResult;
        try {
            String insertQuery = "INSERT into PatientFiles VALUES (\"CHAC70110503\", \"Charlie\", \"Chaplin\", \"MALE\", \"Montreal\", \"1970-11-05\", \"Father: Chris, Mother: Caroline\", 4);";
            statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
            testResult = dataAccessObject.patientExistsInDB("CHAC70110503");
            assertTrue(testResult);
        } catch (Exception ex){
            ex.printStackTrace();
        }
   }


    @Test
    public void getStartDateTest() throws SQLException {
        MedicalHistory mh;
        connection = getConnection();
        mh = new MedicalHistory("dia","tre","House",11111, LocalDate.now(),null);
        dataAccessObject.addMedicalHistory("ALLA60050501", mh);
        LocalDate date = dataAccessObject.getStartDate("ALLA60050501", 11111, "dia");
        assertEquals(LocalDate.now(), date);
        connection.close();
    }

    @Test void updateEndDateTest() throws SQLException {
        MedicalHistory mh;
        connection = getConnection();
        mh = new MedicalHistory("dia","tre","House",11111, LocalDate.now(),LocalDate.now());
        dataAccessObject.addMedicalHistory("ALLA60050501", mh);
        LocalDate date = mh.getEndDate();
        assertNotNull(date);
        connection.close();
    }

    @Test
    public void getPatientFileInfoFromDBTest() throws SQLException {
        connection = getConnection();
        Statement statement;
        HashMap<String, String> resultHashMap = new HashMap<String, String>();
        try {
            String insertQuery = "INSERT into PatientFiles VALUES (\"CHAC70110503\", \"Charles-Valentin\", \"Alkan\", \"MALE\", \"Montreal\", \"1970-11-05\", \"Father: Chris, Mother: Caroline\", 4);";
            statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
            resultHashMap = dataAccessObject.getPatientFileInfoFromDB("CHAC70110503");
            assertEquals("CHAC70110503", resultHashMap.get("ramqCode"));
            assertEquals("Charles-Valentin", resultHashMap.get("firstName"));
            assertEquals("Alkan", resultHashMap.get("lastName"));
            assertEquals("MALE", resultHashMap.get("gender"));
            assertEquals("Montreal", resultHashMap.get("birthCity"));
            assertEquals("1970-11-05", resultHashMap.get("birthDate"));
            assertEquals("Father: Chris, Mother: Caroline", resultHashMap.get("parentsName"));
            assertEquals("4", resultHashMap.get("contactInfoId"));
        } catch (Exception ex){
            ex.printStackTrace();
        }
        connection.close();
    }

    private void executeQuery(String query) throws SQLException {
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        connection.close();
    }

    private Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(mockDatabasePath);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
