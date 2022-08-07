package Model.PatientFile;
import Model.ContactInformation.ContactInformation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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



    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }



    public Gender getGender() {
        return gender;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public String getBirthCity() {
        return birthCity;
    }

    public String getKnownParents() {
        return knownParents;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setMedicalVisits(List<MedicalVisit> medicalVisits) {
        this.medicalVisits = medicalVisits;
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
