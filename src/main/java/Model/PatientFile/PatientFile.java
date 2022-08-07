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
        setRamqCode(ramqCode);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setBirthCity(birthCity);
        this.birthDate = birthDate;
        setKnownParents(knownParents);
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
        boolean isValid = false;
        String validFormat = "^[A-Z]{4}\\d{8}$";

        if (ramqCode.matches(validFormat))
            isValid = true;

        if (!isValid)
            this.ramqCode = null;
        else
            this.ramqCode = ramqCode;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-\\s])*$";

        if (firstName.matches(validFormat) && firstName.length() > 0)
            isValid = true;

        if (!isValid)
            this.firstName = null;
        else
            this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-\\s])*$";

        if (lastName.matches(validFormat) && lastName.length() > 0)
            isValid = true;

        if (!isValid)
            this.lastName = null;
        else
            this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        boolean isValid = gender.equals(Gender.MALE) || gender.equals(Gender.FEMALE)
                || gender.equals(Gender.OTHER);

        if (!isValid)
            this.gender = null;
        else
            this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        boolean isValid = false;

        LocalDate date = LocalDate.now();
        if (birthDate.equals(date))
            isValid = true;

        if (!isValid)
            this.birthDate = null;
        else
            this.birthDate = birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-\\s])*$";

        if (birthCity.matches(validFormat) && birthCity.length() > 0)
            isValid = true;

        if (!isValid)
            this.birthCity = null;
        else
            this.birthCity = birthCity;
    }

    public String getKnownParents() {
        return knownParents;
    }

    public void setKnownParents(String knownParents) {
        boolean isValid = false;
        String validFormat = "^([a-zA-Z-,\\s])*$";
        if (knownParents.matches(validFormat) && knownParents.length() > 0)
            isValid = true;
        if (!isValid)
            this.knownParents = null;
        else
            this.knownParents = knownParents;
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
