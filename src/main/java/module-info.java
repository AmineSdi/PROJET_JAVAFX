module centralizedmedicalsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Application to javafx.fxml;
    exports Application;

    exports Model.User;
    opens Model.User to javafx.fxml;
    exports Model.ContactInformation;
    opens Model.ContactInformation to javafx.fxml;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Model.PatientFile;
    opens Model.PatientFile to javafx.fxml;
}