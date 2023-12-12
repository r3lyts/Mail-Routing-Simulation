package Model;

/**
 * @author tylersmall
 * Summarizes an appointment into type, month, and number of appointments by month
 */
public class AppointmentSummary {
    private String type;
    private String month;
    private int count;

    /**
     * Creates an appointment with type, month, and count
     * @param type
     * @param month
     * @param count
     */
    public AppointmentSummary(String type, String month, int count) {
        this.type = type;
        this.month = month;
        this.count = count;
    }

    /**
     * gets the type of appointment
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of appointment
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets the month of the appointment
     * @return appointment month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the appointment month
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Gets the count of appointments
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * sets the count of the appointment
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }
}
