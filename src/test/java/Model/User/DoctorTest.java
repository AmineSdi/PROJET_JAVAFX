package Model.User;

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


    Doctor d1, d2, d3, d4, d5, doctor;


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



        doctor = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 11111, "Internal Medicine", establishment);


        // Invalid Doctor License
        d2 = new Doctor(1, "Gregory", "House", "houseMD",

                        "aaa", 1, "Internal Medicine", establishment);

        // Invalid Specialty
        d3 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 22222, "", establishment);

        d4 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 33333, "Internal Medicine", establishment);

        d5 = new Doctor(1, "Gregory", "House", "houseMD",
                "aaa", 11111, "Internal Medicine", establishment);

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

        d4.setLicense(51471);
        d5.setLicense(5);

    }

    @Test void createVisitTest() {
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




    @Test void validateLicenseTest_Valid() {
        assertEquals(51471, d4.getLicense());
    }

    @Test void validateLicenseTest_Invalid() {
        assertEquals(0, d5.getLicense());
    }

    @Test void validateSpecialtyTest_Valid() {
        assertEquals("Internal Medicine", d1.getSpecialty());
    }

    @Test void validateSpecialtyTest_Invalid() {
        assertEquals(null, d3.getSpecialty());
    }

    }





