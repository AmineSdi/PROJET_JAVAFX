package Model.Builder;

import Model.PatientFile.PatientFile;

public class Director {

    /*
    TODO (Hypothesis) : Director will be used from MainController.
     Code can look something like this (called using RAMQCode textfield) :
        Director director = new Director();
        PatientFileBuilder builder = new PatientFileBuilder();
        director.buildPatientfile(builder, ramqCode);
        Patientfile patientFile = builder.assemble();
    */
    public PatientFile buildPatientFile(PatientFileBuilder builder, String ramqCode) {
        builder.buildVisits(ramqCode);
        builder.buildHistories(ramqCode);
        builder.buildContactInfo(ramqCode);

        PatientFile patientFile = builder.assemble();

        return patientFile;
    }
}
