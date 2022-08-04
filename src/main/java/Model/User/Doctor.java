package Model.User;

import Model.ContactInformation.MedicalEstablishment;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.Visitor.Visitor;

import java.time.LocalDate;

public class Doctor extends User implements Visitor {


    //CONSTRUCTEUR
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
    //VARIABLES
    String visitDiagnosis;
    String visitTreatment;
    String visitSummary;
    String visitNotes;

    public String getHistoryDiagnosis() {
        return historyDiagnosis;
    }

    public void setHistoryDiagnosis(String historyDiagnosis) {
        // Valid diagnosis examples : Cancer, Cold + temperature 39, end with punctuation
        // Invalid diagnosis example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (historyDiagnosis != null) {
            if (historyDiagnosis.matches(validFormat))
                isValid = true;

            if (!isValid)
                this.historyDiagnosis = null;
            else
                this.historyDiagnosis = historyDiagnosis;
        }
    }

    public String getHistoryTreatment() {
        return historyTreatment;
    }

    public void setHistoryTreatment(String historyTreatment) {
        // Valid treatment examples : Sleep, pills, end with punctuation
        // Invalid treatment example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (historyTreatment != null) {

            if (historyTreatment.matches(validFormat))
                isValid = true;

            if (!isValid)
                this.historyTreatment = null;
            else
                this.historyTreatment = historyTreatment;
        }
    }

    public LocalDate getHistoryEndDate() {
        return historyEndDate;
    }

    public void setHistoryEndDate(LocalDate historyEndDate) {
        // Valid historyEndDate example : today date
        // Invalid historyEndDate examples : not today date
        boolean isValid = false;

        LocalDate date = LocalDate.now();
        if (historyEndDate.equals(date))
            isValid = true;

        if (!isValid)
            this.historyEndDate = null;
        else
            this.historyEndDate = historyEndDate;
    }

    String historyDiagnosis;
    String historyTreatment;

    public LocalDate getHistoryStartDate() {
        return historyStartDate;
    }

    public void setHistoryStartDate(LocalDate historyStartDate) {
        // Valid historyStartDate example : today date
        // Invalid historyStartDate examples : not today date
        boolean isValid = false;

        LocalDate date = LocalDate.now();
        if (historyStartDate.equals(date))
            isValid = true;

        if (!isValid)
            this.historyStartDate = null;
        else
            this.historyStartDate = historyStartDate;
    }

    LocalDate historyStartDate;
    LocalDate historyEndDate;

    //SETTERS AND GETTERS
    public String getVisitDiagnosis() {
        return visitDiagnosis;
    }
    public void setVisitDiagnosis(String visitDiagnosis) {
        // Valid diagnosis examples : Cancer, Cold + temperature 39, end with punctuation
        // Invalid diagnosis example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (visitDiagnosis != null) {
            if (visitDiagnosis.matches(validFormat))
                isValid = true;

            if (!isValid)
                this.visitDiagnosis = null;
            else
                this.visitDiagnosis = visitDiagnosis;
        }
    }
    public String getVisitTreatment() {
        return visitTreatment;
    }
    public void setVisitTreatment(String visitTreatment) {
        // Valid treatment examples : Sleep, pills, end with punctuation
        // Invalid treatment example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (visitTreatment != null) {

            if (visitTreatment.matches(validFormat))
                isValid = true;

            if (!isValid)
                this.visitTreatment = null;
            else
                this.visitTreatment = visitTreatment;
        }
    }
    public String getVisitSummary() {
        return visitSummary;
    }
    public void setVisitSummary(String visitSummary) {
        // Valid visitSummary examples : High temperature for 2 days, end with punctuation
        // Invalid visitSummary example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (visitSummary != null) {

            if (visitSummary.matches(validFormat))
                isValid = true;

            if (!isValid)
                this.visitSummary = null;
            else
                this.visitSummary = visitSummary;
        }
    }
    public String getVisitNotes() {
        return visitNotes;
    }
    public void setVisitNotes(String visitNotes) {
        // Valid visitNotes examples : Had cold in the past, end with punctuation
        // Invalid visitNotes example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (visitNotes != null) {

            if (visitNotes.matches(validFormat))
                isValid = true;
            if (!isValid)
                this.visitNotes = null;
            else
                this.visitNotes = visitNotes;
        }
    }
    public int getLicense() {
        return license;
    }
    public void setLicense(int license) {
        // Valid license example : 12345
        // Invalid license examples : 97-8514, 2541-08
        if (license >= 10000 && license <= 99999)
            this.license = license;
        else
            this.license = 0;
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

    //PRIVATE METHODS
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

    //PUBLIC METHODS
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
        history.modifyHistory(firstName, lastName, license, historyDiagnosis, historyTreatment,
                historyStartDate, historyEndDate);
    }

    @Override
    public void visitMedicalVisit(MedicalVisit visit) {
        visit.modifyVisit(medicalEstablishment.getName(), firstName, lastName, license, LocalDate.now(),
                          visitDiagnosis, visitTreatment, visitSummary, visitNotes);
    }

    public String getEstablishmentName() {
        return medicalEstablishment.getName();
    }
}
