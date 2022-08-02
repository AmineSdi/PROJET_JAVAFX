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
    Doctor d1, d2, d3;

    MedicalEstablishment establishment;
    ContactInformation ci;
    MedicalVisit mmv;
    MedicalHistory mmh, mh1, mh2, mh3, mh6;

    LocalDate startDate = LocalDate.now();
    LocalDate visitDate = LocalDate.now();
    LocalDate startDate1 = LocalDate.of(2020,1, 1);
    LocalDate startDate2 = LocalDate.of(2022,1,1);

    @BeforeEach
    void initialize() {

        ci = new ContactInformation(1333, "Boulvard Jacques Cartier E",
                "Longueuil", "J4M2A5", "(450) 468-8111", "pb@qc.ca");

        establishment = new MedicalEstablishment(1,
                "Pierre-Boucher Hospital", ci);

        // Valid Doctor
        d1 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 11111, "Internal Medicine", establishment);



        // Invalid Doctor License
        d2 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 1, "Internal Medicine", establishment);

        // Invalid Specialty
        d3 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 11111, "", establishment);
        // Invalid Specialty
        d3 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 22222, "", establishment);



        // Everything Valid, but for modifyVisit tests
        mmv = new MedicalVisit("CHUM", "Dr Gibson", 55555,
                visitDate, "Heart problem.", "Pill.",
                "Problem with the heart.", "Visit later.");

        // Changes for modifyVisit
        mmv.modifyVisit("CHUM", "Gibson", "Smith", 25252,
                LocalDate.now(), "No cancer.", "Nothing to do.",
                "Patent is cancer free.", "To follow in 2 months.");

        // Everything Valid, but for modifyHistory tests
        mmh = new MedicalHistory("Cold", "Sleep", "Dr Gibson",
                55555, startDate, null);


        d1.setHistoryDiagnosis("Pain");
        d2.setHistoryDiagnosis("@@@@");

        d1.setHistoryTreatment("He takes 3 pills. For 2 weeks");
        d2.setHistoryTreatment("$$$");

        d1.setHistoryStartDate(LocalDate.now());
        d2.setHistoryStartDate(startDate1);

        d1.setVisitDiagnosis("Broken bones");
        d2.setVisitDiagnosis("@@@");

        d1.setVisitTreatment("Surgery right now");
        d2.setVisitTreatment("@@&");

        d1.setVisitSummary("Feel bad.");
        d2.setVisitSummary("(*)");

        d1.setVisitNotes("Danger");
        d2.setVisitNotes("{[");

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

    @Test void createHistoryTest() {
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






    @Test void validateDiagnosisHistory_Valid() {
        assertEquals("Pain", d1.getHistoryDiagnosis());
    }

    @Test void validateDiagnosisHistory_Invalid() {
        assertEquals(null, d2.getHistoryDiagnosis());
    }

    @Test void validateTreatmentHistory_Valid() {
        assertEquals("He takes 3 pills. For 2 weeks", d1.getHistoryTreatment());
    }

    @Test void validateTreatmentHistory_Invalid() {
        assertEquals(null, d2.getHistoryTreatment());
    }

    @Test void validateStartDateHistory_Valid() {
        assertEquals(startDate, d1.getHistoryStartDate());
    }

    @Test void validateStartDateHistory_Invalid() {
        assertEquals(null, d2.getHistoryStartDate());
    }

    @Test void validateDiagnosisVisit_Valid() {
        assertEquals("Broken bones", d1.getVisitDiagnosis());
    }

    @Test void validateDiagnosisVisit_Invalid() {
        assertEquals(null, d2.getVisitDiagnosis());
    }

    @Test void validateTreatmentVisit_Valid() {
        assertEquals("Surgery right now", d1.getVisitTreatment());
    }

    @Test void validateTreatmentVisit_Invalid() {
        assertEquals(null, d2.getVisitTreatment());
    }

    @Test void validateSummaryVisit_Valid() {
        assertEquals("Feel bad.", d1.getVisitSummary());
    }

    @Test void validateSummaryVisit_Invalid() {
        assertEquals(null, d2.getVisitSummary());
    }

    @Test void validateSummaryNotes_Valid() {
        assertEquals("Danger", d1.getVisitNotes());
    }

    @Test void validateSummaryNotes_Invalid() {
        assertEquals(null, d2.getVisitNotes());
    }



}


