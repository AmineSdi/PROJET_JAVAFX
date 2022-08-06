import Model.ContactInformation.ContactInformation;
import Model.ContactInformation.MedicalEstablishment;
import Model.User.Doctor;
import  Model.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    User u1, u2, u3, u4, u5, u6;
    MedicalEstablishment establishment;
    ContactInformation ci;

    @BeforeEach

    void initialize() {

        ci = new ContactInformation(1333, "Boulvard Jacques Cartier E",
                "Longueuil", "J4M2A5", "(450) 468-8111", "pb@qc.ca");

        establishment = new MedicalEstablishment(1,
                "Pierre-Boucher Hospital", ci);

        // Everything Valid
        u1 = new Doctor(123456, "John", "Smith", "JohnSmit",
            "1234Aq#$", 123456, "Cancer specialist", establishment);

        // Invalid userId
        u2 = new Doctor(1, "John", "Smith", "JohnSmit",
                "1234Aq#$", 123456, "Cancer specialist", establishment);

        // Invalid firstName
        u3 = new Doctor(123456, "", "Smith", "JohnSmit",
                "1234Aq#$", 123456, "Cancer specialist", establishment);

        // Invalid lastName
        u4 = new Doctor(123456, "John", "", "JohnSmit",
                "1234Aq#$", 123456, "Cancer specialist", establishment);

        // Invalid userName
        u5 = new Doctor(123456, "John", "Smith", "123",
                "1234Aq#$", 123456, "Cancer specialist", establishment);

        // Invalid password
        u6 = new Doctor(123456, "John", "Smith", "JohnSmit",
                "54#$", 123456, "Cancer specialist", establishment);
    }


    @Test void validateUserId_Valid() {
        assertEquals(123456, u1.getUserId()); }

    @Test void validateUserId_Invalid() {
        assertEquals(0, u2.getUserId());
    }

    @Test void validateFirstName_Valid() {
        assertEquals("John", u1.getFirstName());}

    @Test void validateFirstName_Invalid() {
        assertEquals(null, u3.getFirstName());
    }

    @Test void validateLastName_Valid() {
        assertEquals("Smith", u1.getLastName());}

    @Test void validateLastName_Invalid() {
        assertEquals(null, u4.getLastName());
    }

    @Test void validateUserName_Valid() {
        assertEquals("JohnSmit", u1.getUserName()); }

    @Test void validateUserName_Invalid() {
        assertEquals(null, u5.getUserName());
    }

    @Test void validatePassword_Valid() {
        assertEquals("1234Aq#$", u1.getPassword());
    }

    @Test void validatePassword_Invalid() {
        assertEquals(null, u6.getPassword());
    }

}
