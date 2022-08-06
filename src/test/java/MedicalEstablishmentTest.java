import Model.ContactInformation.ContactInformation;
import Model.ContactInformation.MedicalEstablishment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalEstablishmentTest {

    MedicalEstablishment me1, me2, me3;
    ContactInformation ci;

    @BeforeEach
    void initialize() {

        ci = new ContactInformation(1000, "St-Denis Street",
                                    "Montreal", "H2X0C1", "(514) 890-8000", "help@chum.qc");

        // Everything Valid
        me1 = new MedicalEstablishment(2022, "Charles-Lemoyne Hopital", ci);

        // Invalid establishmentId
        me2 = new MedicalEstablishment(10000, "CHUM", ci);

        // Invalid name
        me3 = new MedicalEstablishment(2022, "", ci);

    }

    @Test void validateEstablishmentId_Valid() {
        assertEquals(2022, me1.getEstablishmentId());
    }

    @Test void validateEstablishmentId_Invalid() {
        assertEquals(0, me2.getEstablishmentId());
    }

    @Test void validateName_Valid() {
        assertEquals("Charles-Lemoyne Hopital", me1.getName());
    }

    @Test void validateName_Invalid() {
        assertEquals(null, me3.getName());
    }


}
