package User;

public abstract class HealthProfessional extends User {

    public HealthProfessional(int userId, String firstName, String lastName, String userName,
                              String password, int healthProfessionalId) {
        super(userId, firstName, lastName, userName, password);
        this.healthProfessionalId = healthProfessionalId;
    }

    int healthProfessionalId;

    public int getHealthProfessionalId() {
        return healthProfessionalId;
    }

    public void setHealthProfessionalId(int healthProfessionalId) {
        this.healthProfessionalId = healthProfessionalId;
    }
}
