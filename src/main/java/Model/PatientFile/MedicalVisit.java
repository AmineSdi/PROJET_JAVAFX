package Model.PatientFile;

import Model.Visitor.Visitable;
import Model.Visitor.Visitor;

import java.time.LocalDate;

public class MedicalVisit implements Visitable {
    public MedicalVisit() {}
    public MedicalVisit(String establishmentName, String doctorName, int doctorLicense,
                        LocalDate visitDate, String diagnosis, String treatment,
                        String summary, String notes) {
        validateEstablishmentName(establishmentName);
        validateDoctorName(doctorName);
        validateDoctorLicense(doctorLicense);
        validateVisitDate(visitDate);
        validateDiagnosis(diagnosis);
        validateTreatment(treatment);
        validateVisitSummary(summary); //
        validateNotes(notes);
        //this.visitDate = visitDate;
        //this.visitSummary = visitSummary;
        //this.notes = notes;
    }

    String establishmentName;
    String doctorName; // not in Database
    int doctorLicense;
    LocalDate visitDate;
    String diagnosis;
    String treatment;
    String summary;
    String notes;

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void validateEstablishmentName(String establishmentName) {
        // Valid establishmentName example : CHUM, Maisonneuve Rosemont Hopital
        // Invalid establishmentName example : CHUM 3
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-\\s])*$";

        if (establishmentName.matches(validFormat))
            isValid = true;

        if (!isValid)
            this.establishmentName = null;
        else
            this.establishmentName = establishmentName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void validateDoctorName(String doctorName) {
        // Valid doctorName example : Dr House, Smith
        // Invalid doctorName example : Dr House licence 852147
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-\\s])*$";

        if (doctorName.matches(validFormat))
            isValid = true;

        if (!isValid)
            this.doctorName = null;
        else
            this.doctorName = doctorName;
    }

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

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void validateVisitDate(LocalDate visitDate) {
        // Valid visitDate example : today date
        // Invalid visitDate examples : not today date
        boolean isValid = false;

        LocalDate date = LocalDate.now();
        if (visitDate.equals(date))
            isValid = true;

        if (!isValid)
            this.visitDate = null;
        else
            this.visitDate = visitDate;
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
            if (diagnosis.matches(validFormat))
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

            if (treatment.matches(validFormat))
                isValid = true;

            if (!isValid)
                this.treatment = null;
            else
                this.treatment = treatment;
        }
    }

    public String getSummary() {
        return summary;
    }

    public void validateVisitSummary(String visitSummary) {
        // Valid visitSummary examples : High temperature for 2 days, end with punctuation
        // Invalid visitSummary example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (visitSummary != null) {

            if (visitSummary.matches(validFormat))
                isValid = true;

            if (!isValid)
                this.summary = null;
            else
                this.summary = visitSummary;
        }
    }

    public String getNotes() {
        return notes;
    }

    public void validateNotes(String notes) {
        // Valid visitSummary examples : Had cold in the past, end with punctuation
        // Invalid notes example : empty field, does not end with punctuation
        boolean isValid = false;
        String validFormat = "^[A-Za-z0-9,;?.'\"\\s]+[.?!]$";
        if (notes != null) {

            if (notes.matches(validFormat))
                isValid = true;
            if (!isValid)
                this.notes = null;
            else
                this.notes = notes;
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMedicalVisit(this);
    }


    /**
     * Sets the diagnosis, treatment, visitSummary and notes for this medical visit.
     * */
    public void modifyVisit(String medicalEstablishmentName, String firstName, String lastName,
                            int license, LocalDate date, String diagnosis, String treatment,
                            String summary, String notes) {
        this.establishmentName = medicalEstablishmentName;
        this.doctorName = firstName + " " + lastName;
        this.doctorLicense = license;
        this.visitDate = date;
        //this.diagnosis = diagnosis;
        //this.treatment = treatment;
        //this.visitSummary = visitSummary;
        //this.notes = notes;
        validateDiagnosis(diagnosis);
        validateTreatment(treatment);
        validateVisitSummary(summary);
        validateNotes(notes);
    }
}
