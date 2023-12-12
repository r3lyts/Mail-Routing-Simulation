package Model;

/**
 * @author tylersmall
 *
 * Class for a contact that has an id, name, and email
 */
public class Contact {
    private int contactID;
    private String contactName;
    private String email;

    /**
     * gets the contact id
     * @return contact id
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * sets the contact id
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * gets the contact name
     * @return contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * sets the contact name
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * gets the contact email
     * @return contact email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the contact email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
