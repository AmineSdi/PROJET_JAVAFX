package Model.PatientFile;

import java.util.Date;

public class MedicalHistory {
    public MedicalHistory(String diagnosis, String treatment, String doctorName, Date startDate, Date endDate) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctorName = doctorName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    String diagnosis;
    String treatment;
    String doctorName;
    Date startDate;
    Date endDate;

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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
