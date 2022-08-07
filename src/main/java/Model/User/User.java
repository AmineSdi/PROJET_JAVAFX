package Model.User;

public abstract class User {
    //VARIABLES
    private int userId;
    String firstName;
    String lastName;
    private String userName;
    private String password;

    //**************//
    //Public Methods//
    //**************//
    public User(int userId, String firstName, String lastName, String userName, String password) {
        setId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setPassword(password);
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        if (id >= 10000 && id <= 99999)
            this.userId = id;
        else
            this.userId = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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

    public void setLastName(String lastName) {
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

    public void setUserName(String userName) {
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

    public void setPassword(String password) {
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
