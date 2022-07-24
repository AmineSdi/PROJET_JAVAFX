package Model.Visitor;

import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;

public interface Visitor {

    public void visitMedicalHistory(MedicalHistory history);
    public void visitMedicalVisit(MedicalVisit visit);


}
