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
    Doctor d1, d2;

    MedicalEstablishment establishment;
    ContactInformation ci;

    @BeforeEach
    public void initialize() {

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
    }

    @Test public void validateLicenseTest_Valid() {
        assertEquals(11111, d1.getLicense());
    }

    @Test public void validateLicenseTest_Invalid() {
        assertEquals(0, d2.getLicense());
    }
}


