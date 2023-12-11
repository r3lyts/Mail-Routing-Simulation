package Model;

public class ContactAppointmentSummary {
    private String contactName;
    private int totalAppointments;

    public ContactAppointmentSummary(String contactName, int totalAppointments) {
        this.contactName = contactName;
        this.totalAppointments = totalAppointments;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }
}
