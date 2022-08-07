package Model.PatientFile;
import Model.Visitor.Visitable;
import Model.Visitor.Visitor;
import java.time.LocalDate;

public class MedicalVisit implements Visitable {
    //VARIABLES
    private String establishmentName;
    private String doctorName; // not in Database
    private int doctorLicense;
    private LocalDate visitDate;
    private String diagnosis;
    private String treatment;
    private String summary;
    private String notes;

    //**************//
    //Public Methods//
    //**************//
    public MedicalVisit() {}
    public MedicalVisit(String establishmentName, String doctorName, int doctorLicense,
                        LocalDate visitDate, String diagnosis, String treatment,
                        String summary, String notes) {
        validateEstablishmentName(establishmentName);
        validateDoctorName(doctorName);
        validateDoctorLicense(doctorLicense);
        validateDiagnosis(diagnosis);
        validateTreatment(treatment);
        validateVisitDate(visitDate);
        validateVisitSummary(summary);
        validateNotes(notes);
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void validateEstablishmentName(String establishmentName) {
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
        if (doctorLicense >= 10000 && doctorLicense <= 99999)
            this.doctorLicense = doctorLicense;
        else
            this.doctorLicense = 0;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void validateVisitDate(LocalDate visitDate) {
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
        boolean isValid = false;
        String validFormat = "^([a-zA-Z0-9-,.?'()\\s])*$";
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
        boolean isValid = false;
        String validFormat = "^([a-zA-Z0-9-,.?'()\\s])*$";
        if (treatment != null) {
            if (treatment.matches(validFormat) && treatment.length() > 0)
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
        boolean isValid = false;
        String validFormat = "^([a-zA-Z0-9-,.?'()\\s])*$";
        if (visitSummary != null) {

            if (visitSummary.matches(validFormat) && visitSummary.length() > 0)
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
        boolean isValid = false;
        String validFormat = "^([a-zA-Z0-9-,.?'()\\s])*$";
        if (notes != null) {
            if (notes.matches(validFormat) && notes.length() > 0)
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
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.summary = summary;
        this.notes = notes;
    }
}
