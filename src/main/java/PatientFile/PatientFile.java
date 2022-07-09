package PatientFile;

import ContactInformation.ContactInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientFile {

    public PatientFile(String ramqCode, String firstName, String lastName, Gender gender,
                       Date birthDate, String birthCity, String knownParents,
                       ContactInformation contactInformation) {
        this.ramqCode = ramqCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthCity = birthCity;
        this.knownParents = knownParents;
        this.contactInformation = contactInformation;
    }

    String ramqCode;
    String firstName;
    String lastName;
    Gender gender;
    Date birthDate;
    String birthCity;
    String knownParents;
    ContactInformation contactInformation;
    List<MedicalVisit> medicalVisits = new ArrayList<MedicalVisit>();
    List<MedicalHistory> medicalHistories = new ArrayList<MedicalHistory>();

    private void addMedicalVisit(MedicalVisit mv) {
        medicalVisits.add(mv);
    }

    private void addMedicalHistory(MedicalHistory mh) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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

    public void setMedicalHistories(List<MedicalHistory> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }
}
