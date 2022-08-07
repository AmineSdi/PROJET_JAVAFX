package Model.PatientFile;

import Model.PatientFile.MedicalHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalHistoryTest {

    MedicalHistory mh1, mh2, mh3, mh4, mh5, mh6, mh7;
    MedicalHistory mmh;


    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();
    LocalDate startDate1 = LocalDate.of(2020, 3, 15);
    //LocalDate endDate1 = LocalDate.of(2020, 3, 15);

    @BeforeEach

    void initialize(){
        // Everything Valid
        mh1 = new MedicalHistory("He's sick", "He takes 3 pills. For 2 weeks",
                "Dr House", 11111, startDate, null);

        // Invalid diagnosis
        mh2 = new MedicalHistory("", "Pills", "Dr House",
                11111, startDate, null);

        // Invalid treatment
        mh3 = new MedicalHistory("Fever", "", "Dr House",
                11111, startDate, null);

        // Invalid doctorName
        mh4 = new MedicalHistory("Fever", "Pills", "",
                11111, startDate, null);

        // Invalid doctorLicence
        mh5 = new MedicalHistory("Fever", "Pills", "Dr House",
                100, startDate, null);

        // Invalid startDate
        mh6 = new MedicalHistory("Fever", "Pills", "Dr House",
                11111, startDate1, null);

        // Invalid endDate
        mh7 = new MedicalHistory("Fever", "Pills", "Dr House",
                11111, startDate, null);

        // Everything Valid, but for modifyHistory tests
        mmh = new MedicalHistory("Cold", "Sleep", "Dr Gibson",
                55555, startDate, null);


    }



    @Test void validateDiagnosis_Valid() {
        assertEquals("He's sick", mh1.getDiagnosis());
    }

    @Test void validateDiagnosis_Invalid() {
        assertEquals(null, mh2.getDiagnosis());
    }

    @Test void validateTreatment_Valid() {
        assertEquals("He takes 3 pills. For 2 weeks", mh1.getTreatment());
    }

    @Test void validateTreatment_Invalid() {
        assertEquals(null, mh3.getTreatment());
    }

    @Test void validateDoctorName_Valid() {
        assertEquals("Dr House", mh1.getDoctorName());
    }

    @Test void validateDoctorName_Invalid() {
        assertEquals(null, mh4.getDoctorName());
    }

    @Test void validateDoctorLicence_Valid() {
        assertEquals(11111, mh1.getDoctorLicense());
    }

    @Test void validateDoctorLicence_Invalid() {
        assertEquals(0, mh5.getDoctorLicense());
    }

    @Test void validateStartDate_Valid() {
        assertEquals(startDate, mh1.getStartDate());
    }

    @Test void validateStartDate_Invalid() {
        assertEquals(null, mh6.getStartDate());
    }

    @Test void validateEndDate_Valid() {
        assertEquals(null, mh1.getEndDate());
    }

    @Test void validateEndDate_Invalid() {
        assertEquals(null, mh7.getEndDate());
    }


    @Test void validateDoctorFirstModify_Valid() {
        assertEquals("Dr Gibson", mmh.getDoctorName());
    }

    @Test void validateLicenseModify_Valid() {
        assertEquals(55555, mmh.getDoctorLicense());
    }

    @Test void validateDiagnosisModify_Valid() {
        assertEquals("Cold", mmh.getDiagnosis());
    }

    @Test void validateTreatmentModify_Valid() {
        assertEquals("Sleep", mmh.getTreatment());
    }



    @Test void validateModifyHistory_Valid(){
        // Changes for modifyHistory
        mmh.modifyHistory("Joe", "Smith", 44444, "Covid-19",
                "Nothing", LocalDate.now(), null);
        assertEquals("Joe Smith", mmh.getDoctorName());
        assertEquals(44444, mmh.getDoctorLicense());
        assertEquals("Covid-19",mmh.getDiagnosis());
        assertEquals("Covid-19",mmh.getDiagnosis());
        assertEquals("Nothing", mmh.getTreatment());
        assertEquals(startDate, mmh.getStartDate());
    }

}
