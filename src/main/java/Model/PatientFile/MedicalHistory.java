package Model.PatientFile;

import Model.Visitor.Visitable;
import Model.Visitor.Visitor;

import java.time.LocalDate;

public class MedicalHistory implements Visitable {

    public MedicalHistory() {
    }

    public MedicalHistory(String diagnosis, String treatment, String doctorName, int doctorLicense,
                          LocalDate startDate, LocalDate endDate) {
        validateDiagnosis(diagnosis);
        validateTreatment(treatment);
        validateDoctorName(doctorName);
        validateDoctorLicense(doctorLicense);
        validateStartDate(startDate);
        this.endDate = null;
    }

    String diagnosis;
    String treatment;
    String doctorName;
    int doctorLicense;
    LocalDate startDate;
    LocalDate endDate;

    public int getDoctorLicense() {
        return doctorLicense;
    }

    public void validateDoctorLicense(int doctorLicense) {
        // Valid DoctorLicense example : 12345
        // Invalid DoctorLicense examples : 97-8514, 2541-08
        if (doctorLicense >= 10000 && doctorLicense <= 99999)
            this.doctorLicense = doctorLicense;
        else
            this.doctorLicense = 0;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void validateDiagnosis(String diagnosis) {
        // Valid diagnosis examples : Cancer, Cold + temperature 39, end with punctuation
        // Invalid diagnosis example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (diagnosis != null) {
            if (diagnosis.matches(validFormat) && diagnosis.length() > 0)
                isValid = true;

            if (!isValid)
                this.diagnosis = null;
            else
                this.diagnosis = diagnosis;
        }
    }

    public String getTreatment() {
        return treatment;
    }

    public void validateTreatment(String treatment) {
        // Valid treatment examples : Sleep, pills, end with punctuation
        // Invalid treatment example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (treatment != null) {

            if (treatment.matches(validFormat) && treatment.length() > 0)
                isValid = true;

            if (!isValid)
                this.treatment = null;
            else
                this.treatment = treatment;
        }
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void validateDoctorName(String doctorName) {
        // Valid doctorName example : Dr House, Smith
        // Invalid doctorName example : Dr House licence 852147
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-\\s])*$";

        if (doctorName.matches(validFormat) && doctorName.length() > 0)
            isValid = true;

        if (!isValid)
            this.doctorName = null;
        else
            this.doctorName = doctorName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void validateStartDate(LocalDate startDate) {
        // Valid visitDate example : today date
        // Invalid phone examples : not today date
        boolean isValid = false;

        LocalDate date = LocalDate.now();
        if (startDate.equals(date))
            isValid = true;

        if (!isValid)
            this.startDate = null;
        else
            this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void validateEndDate(LocalDate endDate) {
        // Valid visitDate example : any date after startDate or empty field
        // Invalid phone examples : any date before startDate
        // When creating a MedicalHistory, this should be null
        boolean isValid = false;

        LocalDate date = LocalDate.now();
        if (endDate.equals(date))
            isValid = true;

        if (!isValid)
            this.endDate = null;
        else
            this.endDate = endDate;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMedicalHistory(this);
    }

    /**
     * Sets the diagnosis, treatment, and endDate this medical history.
     * <p>
     * TODO : Manage if endDate is null??
     * TODO : Validators?
     */

    public void modifyHistory(String firstName, String lastName, int license,
                              String diagnosis, String treatment, LocalDate startDate,
                              LocalDate endDate) {
        this.doctorName = firstName + " " + lastName;
        this.doctorLicense = license;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void modifyHistory(String diagnosis, String treatment, LocalDate endDate) {
        validateDiagnosis(diagnosis);
        validateTreatment(treatment);
        validateEndDate(endDate);

    }
}
