package Model.Builder;
import Model.ContactInformation.ContactInformation;
import Model.Database.DataAccessObject;
import Model.PatientFile.Gender;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static Model.PatientFile.Gender.*;

/**
 * This class implements a Builder software-design pattern in order
 * to ensure the proper construction of the complex PatientFile object
 */
public class PatientFileBuilder implements Builder {
    //VARIABLES
    private String ramqCode;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String birthCity;
    private String knownParents;
    private List<MedicalVisit> visits = new ArrayList<>();
    private List<MedicalHistory> histories = new ArrayList<>();
    private ContactInformation contactInformation;
    private DataAccessObject dataAccessObject;

    //**************//
    //Public Methods//
    //**************//

    /**
     * Instantiates a PatientFileBuilder
     * @param ramqCode : the id used to find the patient file in the database
     * @param dataAccessObject : allows to interact with the database
     */
    public PatientFileBuilder(String ramqCode, DataAccessObject dataAccessObject) {
        this.ramqCode = ramqCode;
        this.dataAccessObject = dataAccessObject;
        fetchPatientInfoFromDB();
    }

    @Override
    public void buildVisits(String ramqCode) {
        visits = dataAccessObject.getMedicalVisits(ramqCode);
    }

    @Override
    public void buildHistories(String ramqCode) {
        histories = dataAccessObject.getMedicalHistories(ramqCode);
    }

    @Override
    public void buildContactInfo(String ramqCode) {
        contactInformation = dataAccessObject.getContactInformation(ramqCode);// new ContactInformation(...);
    }

    /**
     * This methods combine the different objects to build a PatientFile
     * @return a properly built PatientFile
     */
    @Override
    public PatientFile assemble() {
        PatientFile patientFile = new PatientFile(ramqCode, firstName,
                lastName, gender,birthCity,birthDate,knownParents);
        if (patientFile.getRamqCode() == null) {
            patientFile = null;
        } else {
            patientFile.setMedicalVisits(visits);
            patientFile.setMedicalHistories(histories);
            patientFile.setContactInformation(contactInformation);
        }
        return patientFile;
    }

    //**************//
    //Private Methods//
    //**************//
    /**
     * Sets the attributes of the current class to the values fetched from the Database.
     *
     * */
    private void fetchPatientInfoFromDB() {
        HashMap<String, String> result = dataAccessObject.getPatientFileInfoFromDB(ramqCode);
        if (result != null) {
            String resultGender = result.get("gender");
            firstName = result.get("firstName");
            lastName = result.get("lastName");
            gender = resultGender == "FEMALE" ? FEMALE : (resultGender == "MALE" ? MALE : OTHER);
            birthCity = result.get("birthCity");
            birthDate = LocalDate.parse(result.get("birthDate"));
            knownParents = result.get("parentsName");
        } else {
            this.ramqCode = null;
        }
    }
}
