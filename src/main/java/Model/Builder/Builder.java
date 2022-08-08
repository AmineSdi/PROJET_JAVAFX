package Model.Builder;
import Model.PatientFile.PatientFile;

public interface Builder {
    void buildVisits(String ramqCode);
    void buildHistories(String ramqCode);
    void buildContactInfo(String ramqCode);
    PatientFile assemble();
}
