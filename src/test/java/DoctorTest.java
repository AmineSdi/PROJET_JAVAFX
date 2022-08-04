import Model.ContactInformation.ContactInformation;
import Model.ContactInformation.MedicalEstablishment;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.User.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

//    User user;
//    HealthProfessional healthProfessional;
//    Doctor doctor;
    Doctor d1, d2, d3, doctor;

    MedicalEstablishment establishment;
    ContactInformation ci;
    MedicalVisit mmv;
    MedicalHistory mmh;

    LocalDate startDate = LocalDate.now();
    LocalDate visitDate = LocalDate.now();

    @BeforeEach
    public void initialize() {

        ci = new ContactInformation(1333, "Boulvard Jacques Cartier E",
                                    "Longueuil", "J4M2A5", "(450) 468-8111", "pb@qc.ca");

        establishment = new MedicalEstablishment(1,
                "Pierre-Boucher Hospital", ci);

        // Valid Doctor
        d1 = new Doctor(1, "Gregory", "House", "houseMD",
                        "aaa", 11111, "Internal Medicine", establishment);

        doctor = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 11111, "Internal Medicine", establishment);

        // Invalid Doctor License
        d2 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 1, "Internal Medicine", establishment);

        // Invalid Specialty
        d3 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 11111, "", establishment);

        // Everything Valid, but for modifyVisit tests
        mmv = new MedicalVisit("CHUM", "Dr Gibson", 55555,
                visitDate,"Heart problem.", "Pill.",
                "Problem with the heart.", "Visit later.");

        // Changes for modifyVisit
        mmv.modifyVisit("CHUM", "Gibson","Smith", 25252,
                LocalDate.now(), "No cancer.", "Nothing to do.",
                "Patent is cancer free.", "To follow in 2 months.");

        // Everything Valid, but for modifyHistory tests
        mmh = new MedicalHistory("Cold", "Sleep", "Dr Gibson",
                55555, startDate, null);

        // Changes for modifyHistory
        mmh.modifyHistory("Now has cancer.", "Chemotherapy?", LocalDate.now());
    }

    @Test public void validateLicenseTest_Valid() {
        assertEquals(11111, d1.getLicense());
    }

    @Test public void validateLicenseTest_Invalid() {
        assertEquals(0, d2.getLicense());
    }

    @Test public void validateSpecialtyTest_Valid() {
        assertEquals("Internal Medicine", d1.getSpecialty());
    }



    @Test public void validateSpecialtyTest_Invalid() {
        assertEquals(null, d3.getSpecialty());
    }

    @Test public void createVisitTest() {
        MedicalVisit mv = d1.createVisit();

        assertEquals("Pierre-Boucher Hospital", mv.getEstablishmentName());
        assertEquals("Gregory House", mv.getDoctorName());
        assertEquals(11111, mv.getDoctorLicense());
        assertEquals(LocalDate.now(), mv.getVisitDate());

        assertEquals(null, mv.getDiagnosis());
        assertEquals(null, mv.getTreatment());
        assertEquals(null, mv.getSummary());
        assertEquals(null, mv.getNotes());
    }

    @Test public void createHistoryTest() {
        MedicalHistory mh = d1.createHistory();
        assertEquals(null, mh.getDiagnosis());
        assertEquals(null, mh.getTreatment());
        assertEquals("Gregory House", mh.getDoctorName());
        assertEquals(11111, mh.getDoctorLicense());
        assertEquals(LocalDate.now(), mh.getStartDate());
        assertEquals(null, mh.getEndDate());
    }

    @Test public void medicalEstablishment_Valid() {
        assertEquals("Pierre-Boucher Hospital", d1.getEstablishmentName());

    }

}


