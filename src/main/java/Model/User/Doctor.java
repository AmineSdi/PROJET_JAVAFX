package Model.User;

import Model.ContactInformation.MedicalEstablishment;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.Visitor.Visitor;

import java.time.LocalDate;

public class Doctor extends User implements Visitor {

    public Doctor(int userId, String firstName, String lastName, String userName, String password,
                  int license, String specialty,
                  MedicalEstablishment medicalEstablishment) {
        super(userId, firstName, lastName, userName, password);
        validateLicense(license);
        validateSpecialty(specialty);
        this.medicalEstablishment = medicalEstablishment;
    }

    int license;
    String specialty;
    MedicalEstablishment medicalEstablishment;

    /** Note : Pour faire fonctionner le patron Visiteur, on doit garder certaines informations
     * dans la classe Doctor : diagnosis, treatment, visitSummary et notes pour le
     * MedicalVisit, et diagnosis, treatment, endDate pour le medicalHistory... Alors on va
     * voir si c'est ok de garder ces attributs ici en attendant une meilleure solution...
     * Ces infos vont venir des textfields à partir du MainController...
     * */
    String visitDiagnosis;
    String visitTreatment;
    String visitSummary;
    String visitNotes;
    String historyDiagnosis;
    String historyTreatment;
    LocalDate historyEndDate;

    public int getLicense() {
        return license;
    }

     public String getSpecialty() {
        return specialty;
    }

    public void validateSpecialty(String specialty) {
        // Valid specialty examples : any speciality
        // Invalid specialty example : empty field
        boolean isValid = false;
        String validFormat = "^\\w+(\\s\\w+)*$";
        if (specialty != null) {
            if (specialty.matches(validFormat) && specialty.length() > 0)
                isValid = true;

            if (!isValid)
                this.specialty = null;
            else
                this.specialty = specialty;
        }
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

    @Override
    public void visitMedicalHistory(MedicalHistory history) {
        history.modifyHistory(historyDiagnosis, historyTreatment, historyEndDate);
    }

    @Override
    public void visitMedicalVisit(MedicalVisit visit) {
        visit.modifyVisit(visitDiagnosis, visitTreatment, visitSummary, visitNotes);
    }

    public String getEstablishmentName() {
        return medicalEstablishment.getName();
    }
}
