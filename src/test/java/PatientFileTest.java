import Model.ContactInformation.ContactInformation;
import Model.PatientFile.Gender;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientFileTest {

    PatientFile pf1, pf2, pf3, pf4, pf5, pf6, pf7, pf;
    MedicalHistory mh, mhe;
    MedicalVisit mv, mve;
    LocalDate startDate = LocalDate.now();
    LocalDate visitDate = LocalDate.now();
    LocalDate bd = LocalDate.now();
    ContactInformation ci;

    List<MedicalHistory> medicalHistoryList = new ArrayList<>();
    List<MedicalVisit> medicalVisitList = new ArrayList<>();

    @BeforeEach
    void initialize() {

        medicalHistoryList.add(mh);

        mh = new MedicalHistory("Fever", "Pills", "Dr House",
                11111, startDate, null);

        medicalVisitList.add(mv);

        mv = new MedicalVisit("Montreal General Hospital",
                "Dr House", 12345, visitDate,"Fever",
                "Sleep", "High temperature 39 degree", "Happy");

        ci = new ContactInformation(1333, "Boulevard Jacques Cartier E",
                "Longueuil", "J4M2A5", "(450) 468-8111", "pb@qc.ca");

        pf = new PatientFile("ABCD12345678", "Gregory", "House",
                Gender.MALE, "Montreal", LocalDate.now(),
                "John House, Mary Smith");

        pf.addMedicalVisit(mv);
        pf.addMedicalHistory(mh);

        // Everything Valid
        pf1 = new PatientFile("ABCD12345678", "Gregory", "House",
                Gender.MALE, "Montreal", LocalDate.now(),
                "John House, Mary Smith");
        
        // Invalid ramqcode
        pf2 = new PatientFile("ABCD 12345678", "Gregory", "House",
                Gender.MALE, "Montreal", LocalDate.of(2000, 1, 1),
                "John House, Mary Smith");

        // Invalid firstName
        pf3 = new PatientFile("ABCD12345678", "Gregory 2", "House",
                Gender.MALE, "Montreal", LocalDate.of(2000, 1, 1),
                "John House, Mary Smith");

        // Invalid lastName
        pf4 = new PatientFile("ABCD12345678", "Gregory", "",
                Gender.MALE, "Montreal", LocalDate.of(2000, 1, 1),
                "John House, Mary Smith");

        // Invalid birthCity
        pf5 = new PatientFile("ABCD12345678", "Gregory", "House",
                Gender.MALE, "Montréal", LocalDate.of(2000, 1, 1),
                "John House, Mary Smith");

        // Invalid knownParents
        pf6 = new PatientFile("ABCD12345678", "Gregory", "House",
                Gender.MALE, "Montreal", LocalDate.of(2000, 1, 1),
                "");

        // Invalid birthDate
        pf7 =  new PatientFile("ABCD12345678", "Gregory", "House",
                Gender.MALE, "Montreal", LocalDate.of(2000, 1, 1),
                "John House, Mary Smith");
    }

    @Test void validateRamqCode_Valid () {
        assertEquals("ABCD12345678", pf1.getRamqCode());
    }

    @Test void validateRamqCode_Invalid () {
        assertEquals(null, pf2.getRamqCode());
    }

    @Test void validateFirstName_Valid () {
        assertEquals("Gregory", pf1.getFirstName());
    }

    @Test void validateFirstName_Invalid () {
        assertEquals(null, pf3.getFirstName());
    }

    @Test void validateLastName_Valid () {
        assertEquals("House", pf1.getLastName());
    }

    @Test void validateLastName_Invalid () {
        assertEquals(null, pf4.getLastName());
    }

    @Test void validateBirthCity_Valid () {
        assertEquals("Montreal", pf1.getBirthCity());
    }

    @Test void validateBirthCity_Invalid () {
        assertEquals(null, pf5.getBirthCity());
    }

    @Test void validateKnownParent_Valid () {
        assertEquals("John House, Mary Smith", pf1.getKnownParents());
    }

    @Test void validateKnownParent_Invalid () {
        assertEquals(null, pf6.getKnownParents());
    }

    @Test void validateGender_Valid () {
        assertEquals(Gender.MALE, pf1.getGender());
    }

    @Test void validateBirthDate_Valid () {
        assertEquals(bd, pf1.getBirthDate());
    }

    @Test void createContactInformation() {
        pf1.setContactInformation(ci);
        assertEquals(1333, pf1.getContactInformation().getNumber());
        assertEquals("Boulevard Jacques Cartier E", pf1.getContactInformation().getStreet());
        assertEquals("Longueuil", pf1.getContactInformation().getCity());
        assertEquals("J4M2A5", pf1.getContactInformation().getPostalCode());
        assertEquals("(450) 468-8111", pf1.getContactInformation().getPhone());
        assertEquals("pb@qc.ca", pf1.getContactInformation().getEmail());
    }
    @Test void createMedicalVisit() {
        assertNotNull(pf1.getMedicalVisits());
    }

    @Test void createMedicalHistory() {
        assertNotNull(pf1.getMedicalHistories());
    }

    @Test void addMedicalVisitTest() {
        assertEquals(1, medicalVisitList.size());
    }

    @Test void addMedicalHistoryTest() {
        assertEquals(1, medicalHistoryList.size());
    }
}
