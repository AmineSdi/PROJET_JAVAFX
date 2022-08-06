import Model.Builder.PatientFileBuilder;
import Model.Database.DataAccessObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientFileBuilderTest {

    PatientFileBuilder pfb, pfb1, pfb2;

    DataAccessObject dao = new DataAccessObject();

    public void initialize() {

        pfb.assemble();
        // Everything Valid
        pfb = new PatientFileBuilder("ABCD12345678", dao);
    }

    @Test public void validateEstablishmentName_Valid() {

    }


}
