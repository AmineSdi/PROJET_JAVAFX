package Model.ContactInformation;

public class MedicalEstablishment {

    public MedicalEstablishment(int establishmentId, String name, ContactInformation contactInformation) {
        this.establishmentId = establishmentId;
        this.name = name;
        this.contactInformation = contactInformation;
    }

    int establishmentId;
    String name;
    ContactInformation contactInformation;

    public int getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }
}
