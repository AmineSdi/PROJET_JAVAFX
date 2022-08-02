package Model.PatientFile;

import Model.ContactInformation.ContactInformation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientFile {

    // TODO : Private constructor for builder pattern?
    public PatientFile(String ramqCode, String firstName, String lastName, Gender gender,
                       String birthCity, LocalDate birthDate, String knownParents) {
        validateRamqCode(ramqCode);
        validateFirstName(firstName);
        validateLastName(lastName);
        validateGender(gender);
        validateBirthCity(birthCity);
        validateBirthDate(birthDate);
        validateKnownParents(knownParents);
    }

    String ramqCode;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate birthDate;
    String birthCity;
    String knownParents;
    ContactInformation contactInformation;
    List<MedicalVisit> medicalVisits = new ArrayList<MedicalVisit>();
    List<MedicalHistory> medicalHistories = new ArrayList<MedicalHistory>();

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

    public void validateRamqCode(String ramqCode) {
        // Valid ramqCode example : ABCD11223344
        // Invalid ramqCodee example : ABCD 11223344
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

    public void validateFirstName(String firstName) {
        // Valid firstName example : any first name without number
        // Invalid firstName example : any number in the first name or empty string
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

    public void validateLastName(String lastName) {
        // Valid lastName example : any last name without number
        // Invalid lastName example : any number in the last name or empty string
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

    public void validateGender(Gender gender) {
        // Valid lastName example : any last name without number
        // Invalid lastName example : any number in the last name or empty string
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

    public void validateBirthDate(LocalDate birthDate) {
        // Valid birthDate example :
        // Invalid birthDate examples :
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

    public void validateBirthCity(String birthCity) {
        // Valid street example : Montreal
        // Invalid street example : Montréal or empty string
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

    public void validateKnownParents(String knownParents) {
        // Valid street example : any name(s) without number
        // Invalid street example : any name(s) with number or empty string
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

    // For Builder pattern
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<MedicalVisit> getMedicalVisits() {
        return medicalVisits;
    }

    // For Builder pattern
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

    // For Builder pattern
    public void setMedicalHistories(List<MedicalHistory> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }

}
