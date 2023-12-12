package Model;

/**
 * @author tylersmall
 *
 * Class for the contact appointment summary that is used to just describe a contact name and their total appointments.
 */
public class ContactAppointmentSummary {
    private String contactName;
    private int totalAppointments;

    /**
     * Constructor for the contact appointment summary
     * @param contactName
     * @param totalAppointments
     */
    public ContactAppointmentSummary(String contactName, int totalAppointments) {
        this.contactName = contactName;
        this.totalAppointments = totalAppointments;
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
     * gets the total number of appointments
     * @return total appointments
     */
    public int getTotalAppointments() {
        return totalAppointments;
    }

    /**
     * sets the total appointments
     * @param totalAppointments
     */
    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }
}
