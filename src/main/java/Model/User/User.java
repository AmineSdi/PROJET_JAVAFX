package Model.User;

public abstract class User {

    public User(int userId, String firstName, String lastName, String userName, String password) {
        validateIUserId(userId);
        validateFirstName(firstName);
        validateLastName(lastName);
        // TODO : userName and password are useless here?
        // TODO : is this a Useless class? (User user = new Doctor()... doesn't have appropriate methods)
        validateUserName(userName);
        validatePassword(password);
    }

    int userId;
    String firstName;
    String lastName;
    String userName;
    String password;

    public int getUserId() {
        return userId;
    }

    public void validateIUserId(int userId) {
        if (userId >= 100000 && userId <= 999999)
            this.userId = userId;
        else
            this.userId = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void validateFirstName(String firstName) {
        // Valid firstName example : any first name without number
        // Invalid firstName example : any number in the first name or empty string
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-\\s])*$";

        if (firstName.matches(validFormat) && firstName.length() > 0)
            isValid = true;

        if (!isValid)
            this.firstName = null;
        else
            this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void validateLastName(String lastName) {
        // Valid lastName example : any last name without number
        // Invalid lastName example : any number in the last name or empty string
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-\\s])*$";

        if (lastName.matches(validFormat) && lastName.length() > 0)
            isValid = true;

        if (!isValid)
            this.lastName = null;
        else
            this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void validateUserName(String userName) {
        // Valid userName example : GHouseMD, House123
        // Invalid userName example : not 8 characters, invalid character or empty string
        boolean isValid = false;
        String validFormat = "^([a-zA-Z0-9]){8}$";

        if (userName.matches(validFormat))
            isValid = true;

        if (!isValid)
            this.userName = null;
        else
            this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void validatePassword(String password) {
        // Valid userName example : 12345678
        // Invalid userName example : not 8 characters, invalid character or empty string
        boolean isValid = false;
        String validFormat = "^([a-zA-Z0-9!@#$%?&]){8}$";

        if (password.matches(validFormat))
            isValid = true;

        if (!isValid)
            this.password = null;
        else
            this.password = password;
    }
}
