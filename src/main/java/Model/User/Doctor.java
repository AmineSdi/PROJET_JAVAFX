package Model.User;

import Model.ContactInformation.MedicalEstablishment;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;

import java.time.LocalDate;

public class Doctor extends HealthProfessional {

    public Doctor(int userId, String firstName, String lastName, String userName, String password,
                  int healthProfessionalId,
                  int license, String specialty,
                  MedicalEstablishment medicalEstablishment) {
        super(userId, firstName, lastName, userName, password, healthProfessionalId);
        validateLicense(license);
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

    /**
     * Validates that the license number of the Doctor.
     * If invalid, sets the license to 0, which prevents the Doctor from using the application.
     *
     * @param license The license number to validate.
     */
    private void validateLicense(int license) {
        if (license >= 10000 && license <= 99999)
            this.license = license;
        else
            this.license = 0;
    }

    /**
     * Creates a new MedicalVisit, filling in the information about the Doctor and the Date.
     * The rest of the information (diagnosis, treatment, etc.) is initially empty and is set
     * through the user interface text fields.
     *
     * @return The new medical visit.
     */
    public MedicalVisit createVisit() {
        MedicalVisit mv = new MedicalVisit(this.medicalEstablishment.getName(),
                this.firstName + " " + this.lastName, this.license, LocalDate.now(),
                null, null, null, null);

        return mv;
    }

    /**
     * Creates a new MedicalHistory, filling in the information about the Doctor and the Date.
     * The rest of the information (diagnosis, treatment, etc.) is initially empty and is set
     * through the user interface text fields.
     *
     * @return The new medical history.
     */
    public MedicalHistory createHistory() {
        MedicalHistory mh = new MedicalHistory(null, null,
                this.firstName + " " + this.lastName, this.license,
                LocalDate.now(), null);

        return mh;
    }

}
