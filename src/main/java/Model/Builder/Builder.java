package Model.Builder;

import Model.ContactInformation.ContactInformation;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;
import Model.PatientFile.PatientFile;

import java.util.List;

public interface Builder {

    void buildVisits(String ramqCode);
    void buildHistories(String ramqCode);
    void buildContactInfo(String ramqCode);
    PatientFile assemble();
}
