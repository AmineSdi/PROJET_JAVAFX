package Model.User;

import Model.ContactInformation.MedicalEstablishment;

public class Doctor extends HealthProfessional {

    public Doctor(int userId, String firstName, String lastName, String userName, String password,
                  int healthProfessionalId,
                  int license, String specialty,
                  MedicalEstablishment medicalEstablishment) {
        super(userId, firstName, lastName, userName, password, healthProfessionalId);
        this.license = license;
        this.specialty = specialty;
        this.medicalEstablishment = medicalEstablishment;
    }

    int license;
    String specialty;
    MedicalEstablishment medicalEstablishment;

    public int getLicense() {
        return license;
    }

    public void setLicense(int license) {
        this.license = license;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
