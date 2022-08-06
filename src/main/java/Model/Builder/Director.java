package Model.Builder;
import Model.PatientFile.PatientFile;
public class Director {
    public PatientFile buildPatientFile(PatientFileBuilder builder, String ramqCode) {
        builder.buildVisits(ramqCode);
        builder.buildHistories(ramqCode);
        builder.buildContactInfo(ramqCode);
        PatientFile patientFile = builder.assemble();
        return patientFile;
    }
}
