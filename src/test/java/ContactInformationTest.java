import Model.ContactInformation.ContactInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactInformationTest {

    ContactInformation c1, c2, c3, c4, c5, c6, c7;

    @BeforeEach
    public void initialize() {
        // Everything Valid
        c1 = new ContactInformation(201, "PK Avenue", "Montreal",
                "H2X3Y7", "(514) 987-3000", "bigl@uqam.ca");

        // Invalid phone
        c2 = new ContactInformation(201, "PK Avenue", "Montreal",
                "H2X3Y7", "514) 987-3000", "bigl@uqam.ca");

        // Invalid email
        c3 = new ContactInformation(201, "PK Avenue", "Montreal",
                "H2X3Y7", "(514) 987-3000", "bigluqam.ca");

        // Invalid postal code
        c4 = new ContactInformation(201, "PK Avenue", "Montreal",
                "H2X 3Y7", "(514) 987-3000", "bigluqam.ca");

        // Invalid number
        c5 = new ContactInformation(1, "PK Avenue", "Montreal",
                "H2X3Y7", "(514) 987-3000", "bigl@uqam.ca");

        // Invalid street
        c6 = new ContactInformation(201, "201 PK Avenue", "Montreal",
                "H2X3Y7", "(514) 987-3000", "bigl@uqam.ca");

        // Invalid city
        c7 = new ContactInformation(201, "PK Avenue", "Montréal",
                "H2X3Y7", "(514) 987-3000", "bigl@uqam.ca");

    }

    @Test public void validateNumber_Valid() {
        assertEquals(201, c1.getNumber());
    }

    @Test public void validateNumber_Invalid() {
        assertEquals(0, c5.getNumber());
    }

    @Test public void validateStreet_Valid() {
        assertEquals("PK Avenue", c1.getStreet());
    }

    @Test public void validateStreet_Invalid() {
        assertEquals(null, c6.getStreet());
    }

    @Test public void validateCity_Valid() {
        assertEquals("Montreal", c1.getCity());
    }

    @Test public void validateCity_Invalid() {
        assertEquals(null, c7.getCity());
    }

    @Test public void validatePhoneTest_Valid() {
        assertEquals("(514) 987-3000", c1.getPhone());
    }

    @Test public void validatePhoneTest_Invalid() {
        assertEquals(null, c2.getPhone());
    }

    @Test public void validateEmailTest_Valid() {
        assertEquals("bigl@uqam.ca", c1.getEmail());
    }

    @Test public void validateEmailTest_Invalid() {
        assertEquals(null, c3.getEmail());
    }

    @Test public void validatePostalCodeTest_Valid() {
        assertEquals("H2X3Y7", c1.getPostalCode());
    }

    @Test public void validatePostalCodeTest_Invalid() {
        assertEquals(null, c4.getPostalCode());
    }

}
