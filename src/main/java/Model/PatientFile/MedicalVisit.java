package Model.PatientFile;
import Model.Visitor.Visitable;
import Model.Visitor.Visitor;
import java.time.LocalDate;

/**
 * This class models a MedicalVisit and various validation methods.
 * It also notably plays a role in our Visitor software-design pattern
 * by implementing Visitable which makes it visitable by the class Doctor
 */
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
        this.establishmentName = establishmentName;
        validateDoctorName(doctorName);
        validateDoctorLicense(doctorLicense);
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.visitDate = visitDate;
        this.summary = summary;
        this.notes = notes;
    }

    public String getEstablishmentName() {
        return establishmentName;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getSummary() {
        return summary;
    }

    public String getNotes() {
        return notes;
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
