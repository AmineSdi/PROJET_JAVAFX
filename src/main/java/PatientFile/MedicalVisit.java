package PatientFile;

import java.util.Date;

public class MedicalVisit {
    public MedicalVisit(String establishmentName, String doctorName, Date visitDate, String diagnosis, String treatment, String visitSummary, String notes) {
        this.establishmentName = establishmentName;
        this.doctorName = doctorName;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.visitSummary = visitSummary;
        this.notes = notes;
    }

    String establishmentName;
    String doctorName;
    Date visitDate;
    String diagnosis;
    String treatment;
    String visitSummary;
    String notes;

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getVisitSummary() {
        return visitSummary;
    }

    public void setVisitSummary(String visitSummary) {
        this.visitSummary = visitSummary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
