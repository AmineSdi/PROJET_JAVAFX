package Model.Builder;

import Model.ContactInformation.ContactInformation;
import Model.PatientFile.Gender;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientFileBuilder implements Builder {

    public PatientFileBuilder(String ramqCode) {
        this.ramqCode = ramqCode;
        fetchPatientInfoFromDB(ramqCode);
    }

    String ramqCode;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate birthDate;
    String birthCity;
    String knownParents;

    List<MedicalVisit> visits = new ArrayList<>();
    List<MedicalHistory> histories = new ArrayList<>();
    ContactInformation contactInformation;

    @Override
    public void buildVisits(String ramqCode) {

        /* TODO : Use DAO to :
               -connect to Database
               -fetch an ArrayList of MedicalVisits for this patient :
                "SELECT * FROM MedicalVisits WHERE patientRamqCode = " + ramqCode
         */
//        visits = ...
    }

    @Override
    public void buildHistories(String ramqCode) {

        /* TODO : Use DAO to :
               -connect to Database
               -fetch an ArrayList of MedicalHistories for this patient :
                "SELECT * FROM MedicalHistories WHERE patientRamqCode = " + ramqCode
         */
//        histories = ...
    }

    @Override
    public void buildContactInfo(String ramqCode) {

        /* TODO : Use DAO to :
               -connect to Database
               -fetch contactInfoId
                "SELECT contactInfoId FROM PatientFiles WHERE patientRamqCode = " + ramqCode
               -fetch the contact information for that patient using the contactInfoId.
               "SELECT * FROM ContactInformation WHERE id = " + contactInfoId
         */

        // Use fetched data to construct ContactInformation.
        // contactInformation = new ContactInformation(...);

    }

    @Override
    public PatientFile assemble() {

        /* TODO : Use DAO to :
               -connect to Database
               -fetch the info for that Patient :
                "SELECT * FROM MedicalVisits WHERE patientRamqCode = " + ramqCode
         */
        // Use fetched data to construct PatientFile.
        PatientFile patientFile = new PatientFile(ramqCode, firstName,
                lastName, gender,birthCity,birthDate,knownParents);

        patientFile.setMedicalVisits(visits);
        patientFile.setMedicalHistories(histories);
        patientFile.setContactInformation(contactInformation);

        return patientFile;
    }

    /**
     * Sets the attributes of the current class to the values fetched from the Database.
     *
     * */
    private void fetchPatientInfoFromDB(String ramqCode) {
         /* TODO : Use DAO to :
               -connect to Database
               "SELECT firstName, lastName, gender, birthCity, birthDate, parentsName
               FROM PatientFiles WHERE ramqCode = " + ramqCode;
         */

//        firstName = ;
//        lastName = ;
//        gender = ;
//        birthCity = ;
//        birthDate = ;
//        knownParents = ;
    }
}
