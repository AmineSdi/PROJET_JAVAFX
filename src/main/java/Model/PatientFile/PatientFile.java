package Model.PatientFile;
import Model.ContactInformation.ContactInformation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models a patient file. Some of its attributes are lists of other objetcs,
 * like MedicalVisit and MedicalHistory. We use the Builder software-design pattern in order
 * to instantiate PatientFile objects.
 */
public class PatientFile {
    //VARIABLES
    private String ramqCode;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String birthCity;
    private String knownParents;
    private ContactInformation contactInformation;
    private List<MedicalVisit> medicalVisits = new ArrayList<MedicalVisit>();
    private List<MedicalHistory> medicalHistories = new ArrayList<MedicalHistory>();

    //**************//
    //Public Methods//
    //**************//
    public PatientFile(String ramqCode, String firstName, String lastName, Gender gender,
                       String birthCity, LocalDate birthDate, String knownParents) {
        this.ramqCode = ramqCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthCity = birthCity;
        this.birthDate = birthDate;
        this.knownParents = knownParents;
    }

    public void addMedicalVisit(MedicalVisit mv) {
        if (mv != null)
            medicalVisits.add(mv);
    }

    public void addMedicalHistory(MedicalHistory mh) {
        if (mh != null)
            medicalHistories.add(mh);
    }

    public String getRamqCode() {
        return ramqCode;
    }

    public void setRamqCode(String ramqCode) {
        this.ramqCode = ramqCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getKnownParents() {
        return knownParents;
    }

    public void setKnownParents(String knownParents) {
        this.knownParents = knownParents;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }


    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<MedicalVisit> getMedicalVisits() {
        return medicalVisits;
    }


    public void setMedicalVisits(List<MedicalVisit> medicalVisits) {
        this.medicalVisits = medicalVisits;
    }

    public List<MedicalHistory> getMedicalHistories() {
        return medicalHistories;
    }

    /**
     * Updates the end date of a specific medical history.
     */
    public void updateEndDate(int doctorLicense, String diagnosis, LocalDate startDate,
                              LocalDate endDate) {
        for (MedicalHistory history: medicalHistories) {
            if (history.getDoctorLicense() == doctorLicense
                    && history.getDiagnosis() == diagnosis
                    && history.getStartDate().isEqual(startDate)
                    && history.endDate == null)
                history.setEndDate(endDate);
        }
    }

    public void setMedicalHistories(List<MedicalHistory> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }

}
