module centralizedmedicalsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Application to javafx.fxml;
    exports Application;
    exports PatientFile;
    opens PatientFile to javafx.fxml;
    exports User;
    opens User to javafx.fxml;
    exports ContactInformation;
    opens ContactInformation to javafx.fxml;
    exports GUI;
    opens GUI to javafx.fxml;
}