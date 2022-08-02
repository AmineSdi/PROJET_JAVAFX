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
    public void initialize(){
        // Everything Valid
        mh1 = new MedicalHistory("Fever", "Pills", "Dr House",
                11111, startDate, null);

        // Everything Valid, but for modifyHistory tests
        mmh = new MedicalHistory("Cold", "Sleep", "Dr Gibson",
                55555, startDate, null);

        //NEEDS TO BE FIXED
        // Changes for modifyHistory
        //mmh.modifyHistory("Cancer", "Chemotherapy", LocalDate.now());

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

        // Changes for modifyHistory
        mmh.modifyHistory("John", "Smith", 55555, "Sleeping",
                "Sleep", LocalDate.now(), null);
    }



    @Test public void validateDiagnosis_Valid() {
        assertEquals("Fever", mh1.getDiagnosis());
    }

    @Test public void validateDiagnosis_Invalid() {
        assertEquals(null, mh2.getDiagnosis());
    }

    @Test public void validateTreatment_Valid() {
        assertEquals("Pills", mh1.getTreatment());
    }

    @Test public void validateTreatment_Invalid() {
        assertEquals(null, mh3.getTreatment());
    }

    @Test public void validateDoctorName_Valid() {
        assertEquals("Dr House", mh1.getDoctorName());
    }

    @Test public void validateDoctorName_Invalid() {
        assertEquals(null, mh4.getDoctorName());
    }

    @Test public void validateDoctorLicence_Valid() {
        assertEquals(11111, mh1.getDoctorLicense());
    }

    @Test public void validateDoctorLicence_Invalid() {
        assertEquals(0, mh5.getDoctorLicense());
    }

    @Test public void validateStartDate_Valid() {
        assertEquals(startDate, mh1.getStartDate());
    }

    @Test public void validateStartDate_Invalid() {
        assertEquals(null, mh6.getStartDate());
    }

    @Test public void validateEndDate_Valid() {
        assertEquals(null, mh1.getEndDate());
    }

    @Test public void validateEndDate_Invalid() {
        assertEquals(null, mh7.getEndDate());
    }

    @Test public void validateDiagnosisModify_Valid() {
        assertEquals("Cancer", mmh.getDiagnosis());
    }

    @Test public void validateTreatmentModify_Valid() {
        assertEquals("Chemotherapy", mmh.getTreatment());
    }

    @Test public void validateEndDateModify_Valid() {
        assertEquals(LocalDate.now(), mmh.getEndDate());
    }
}
