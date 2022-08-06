package Model.PatientFile;
import Model.Visitor.Visitable;
import Model.Visitor.Visitor;
import java.time.LocalDate;

public class MedicalHistory implements Visitable {
    //VARIABLES
    private String diagnosis;
    private String treatment;
    private String doctorName;
    private int doctorLicense;
    private LocalDate startDate;
    LocalDate endDate;

    //**************//
    //Public Methods//
    //**************//
    public MedicalHistory() {}

    public MedicalHistory(String diagnosis, String treatment, String doctorName, int doctorLicense,
                          LocalDate startDate, LocalDate endDate) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctorName = doctorName;
        this.doctorLicense = doctorLicense;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDoctorLicense() {
        return doctorLicense;
    }

    public void setDoctorLicense(int doctorLicense) {
        this.doctorLicense = doctorLicense;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMedicalHistory(this);
    }

    /**
     * Sets the diagnosis, treatment, and endDate this medical history.
     * */
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
}
