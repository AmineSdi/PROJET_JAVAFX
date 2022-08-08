package Model.ContactInformation;

/**
 * This class models a Medical Establishment and its validation methods.
 */
public class MedicalEstablishment {
    //VARIABLES
    private int establishmentId;
    private String name;
    private ContactInformation contactInformation;

    //**************//
    //Public Methods//
    //**************//
    public MedicalEstablishment(int establishmentId, String name,
                                ContactInformation contactInformation) {
        validateEstablishmentId(establishmentId);
        validateName(name);
        addContactInformation(contactInformation);
    }

    public int getEstablishmentId() {
        return establishmentId;
    }

    public String getName() {
        return name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    //***************//
    //Private Methods//
    //***************//
    private void validateEstablishmentId(int establishmentId) {
        // Valid establishmentId example : 2022
        // Invalid establishmentId examples : MTL-2022, MTL2002
        if (establishmentId >= 1000 && establishmentId <= 9999)
            this.establishmentId = establishmentId;
        else
            this.establishmentId = 0;
    }

    private void validateName(String name) {
        // Valid name example : Pierre-Boucher Hospital, CHUM
        // Invalid name examples : Hospital 1
        boolean isValid = false;
        String validFormat = "^[A-Z]+([a-zA-Z-\\s]+)*$";
        if (name.matches(validFormat))
            isValid = true;
        if (!isValid)
            this.name = null;
        else
            this.name = name;
    }

    private void addContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }
}
