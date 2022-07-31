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
        this.ramqCode = ramqCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthCity = birthCity;
        this.birthDate = birthDate;
        this.knownParents = knownParents;
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

    // For Builder pattern
    public void setMedicalHistories(List<MedicalHistory> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }


}
