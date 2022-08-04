import Model.PatientFile.MedicalVisit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalVisitTest {

    MedicalVisit mv1, mv2, mv3, mv4, mv5, mv6, mv7, mv8, mv9;
    MedicalVisit mmv;
    LocalDate visitDate = LocalDate.now();
    LocalDate visitDate1 = LocalDate.of(2020, 3, 15);

    @BeforeEach
    void initialize() {
        // Everything Valid
        mv1 = new MedicalVisit("Montreal General Hospital",


                               "Dr House", 12345, visitDate,"Fever.",
                               "Sleep.", "High temperature 39 degree.", "Happy.");
        // Invalid establishmentName
        mv2 = new MedicalVisit("Montreal General Hospital 2",
                               "Dr House", 12345, visitDate,"Fever",
                               "Sleep", "High temperature 39 degree", "Happy");

        // Invalid doctorName
        mv3 = new MedicalVisit("Montreal General Hospital",
                               "Dr House Licence 8524", 12345, visitDate,"Fever",
                               "Sleep", "High temperature 39 degree", "Happy");

        // Invalid doctorLicense
        mv4 = new MedicalVisit("Montreal General Hospital",
                               "Dr House", 158, visitDate,"Fever",
                               "Sleep", "High temperature 39 degree", "Happy");

        // Invalid visitDate
        mv5 = new MedicalVisit("Montreal General Hospital",
                               "Dr House", 12345, visitDate1,"Fever",
                               "Sleep", "High temperature 39 degree", "Happy");

        // Invalid diagnosis
        mv6 = new MedicalVisit("Montreal General Hospital",
                               "Dr House", 12345, visitDate,"",
                               "Sleep", "High temperature 39 degree", "Happy");

        // Invalid treatment
        mv7 = new MedicalVisit("Montreal General Hospital",
                               "Dr House", 12345, visitDate,"Fever",
                               "", "High temperature 39 degree", "Happy");

        // Invalid visitSummary
        mv8 = new MedicalVisit("Montreal General Hospital",
                               "Dr House", 12345, visitDate,"Happy",
                               "Sleep", "", "Not covid");

        // Invalid notes
        mv9 = new MedicalVisit("Montreal General Hospital",
                               "Dr House", 12345, visitDate,"Fever",
                               "Sleep", "High temperature 39 degree", "");

        // Everything Valid, but for modifyVisit tests
        mmv = new MedicalVisit("CHUM", "Dr Gibson", 55555,
                visitDate,"Heart problem.", "Pill.",
                "Problem with the heart.", "Visit later.");

        // Changes for modifyVisit
        mmv.modifyVisit("CHUM", "Gibson","Smith", 25252,
                LocalDate.now(), "No cancer.", "Nothing to do.",
                "Patent is cancer free.", "To follow in 2 months.");

    }

    @Test void validateEstablishmentName_Valid() {
        assertEquals("Montreal General Hospital", mv1.getEstablishmentName());
    }

    @Test void validateEstablishmentName_Invalid() {
        assertEquals(null, mv2.getEstablishmentName());
    }

    @Test void validateDoctorName_Valid() {
        assertEquals("Dr House", mv1.getDoctorName());
    }

    @Test void validateDoctorName_Invalid() {
        assertEquals(null, mv3.getDoctorName());
    }

    @Test void validateDoctorLicense_Valid() {
        assertEquals(12345, mv1.getDoctorLicense());
    }

    @Test void validateDoctorLicense_Invalid() {
        assertEquals(0, mv4.getDoctorLicense());
    }

    @Test void validateVisitDate_Valid() {
        assertEquals(visitDate, mv1.getVisitDate());
    }

    @Test public void validateVisitDate_Invalid() {
        assertEquals(null, mv5.getVisitDate());
    }


    @Test public void validateDiagnosis_Valid() {
        assertEquals("Fever.", mv1.getDiagnosis());
    }

    @Test void validateDiagnosis_Invalid() {
        assertEquals(null, mv6.getDiagnosis());
    }


    @Test public void validateTreatment_Valid() {
        assertEquals("Sleep.", mv1.getTreatment());
    }

    @Test void validateTreatment_Invalid() {
        assertEquals(null, mv7.getTreatment());
    }

    @Test public void validateVisitSummary_Valid() {
        assertEquals("High temperature 39 degree.", mv1.getSummary());
    }





    @Test public void validateNotes_Valid() {
        assertEquals("Happy.", mv1.getNotes());
    }


    @Test public void validateNotes_Invalid() {
        assertEquals(null, mv9.getNotes());
    }

    @Test public void validateDiagnosisModify_Valid() {
        assertEquals("No cancer.", mmv.getDiagnosis());
    }

    @Test public void validateTreatmentModify_Valid() {
        assertEquals("Nothing to do.", mmv.getTreatment());
    }

    @Test public void validateVisitSummaryModify_Valid() {
        assertEquals("Patent is cancer free.", mmv.getSummary());
    }

    @Test public void validateNotesModify_Valid() {
        assertEquals("To follow in 2 months.", mmv.getNotes());
    }
}
