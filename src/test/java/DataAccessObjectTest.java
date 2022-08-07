import Model.ContactInformation.ContactInformation;
import Model.Database.DataAccessObject;
import Model.PatientFile.MedicalVisit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @AfterEach
    public void cleanUp() throws SQLException {
        connection = getConnection();
        executeQuery(cleanUpQuery);
        connection.close();
    }

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

//    @Test
//    public void test2() throws SQLException {
////        System.out.println("INSERTING");
//        String query = "INSERT into MedicalVisits(patientRamqCode, doctorLicense, visitDate, diagnosis, treatment, summary, notes) VALUES (\"ALLA60050501\", 11111, \"2022-08-01\", \"Headache\", \"Rest\", \"No neuro sx.\", \"No red flags\");";
//        executeQuery(query);
//        connection.close();
//    }

    private void executeQuery(String query) {
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
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
