package Model.ContactInformation;

public class ContactInformation {

    public ContactInformation(int number, String street, String city, String postalCode, String phone, String email) {
        this.number = number;
        this.street = street;
        this.city = city;
        validatePhone(phone);
        validateEmail(email);
        validatePostalCode(postalCode);
    }

    int number;
    String street;
    String city;
    String postalCode;
    String phone;
    String email;

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    private void validatePhone(String phone) {
        // Valid phone example : (514) 987-3000
        // Invalid phone examples : 514 987-3000, (514) 9873000, 514 987 3000
        boolean isValid = false;
        String validFormat = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";

        if (phone.matches(validFormat))
            isValid = true;

        if (!isValid)
            this.phone = null;
        else
            this.phone = phone;
    }

    private void validateEmail(String email) {
        // Valid email example : bigl@uqam.ca
        boolean isValid = false;
        String validFormat = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        if (email.matches(validFormat))
            isValid = true;

        if (!isValid)
            this.email = null;
        else
            this.email = email;
    }

    private void validatePostalCode(String postalCode) {
        // Valid postal code example : H2X3Y7 (no spaces)
        boolean isValid = false;
        String validFormat = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z][0-9][A-Z][0-9]$";

        if (postalCode.matches(validFormat))
            isValid = true;

        if (!isValid)
            this.postalCode = null;
        else
            this.postalCode = postalCode;
    }

}
