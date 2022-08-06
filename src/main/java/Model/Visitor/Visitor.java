package Model.Visitor;
import Model.PatientFile.MedicalHistory;
import Model.PatientFile.MedicalVisit;

public interface Visitor {
    void visitMedicalHistory(MedicalHistory history);
    void visitMedicalVisit(MedicalVisit visit);

}
