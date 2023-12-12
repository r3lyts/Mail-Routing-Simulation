package Model;

import HelperClasses.Helper;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author tylersmall
 * Appointment class that is used to describe an appointment
 */
public class Appointment {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Instant startTime;
    private Instant endTime;
    private Instant createDate;
    private String createdBy;
    private Instant lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;


    /**
     * gets the appointment id
     * @return the appointment id
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * sets the appointment id
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * gets the appointment title
     * @return the title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the description of the appointment
     * @return the description of the appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the appointment
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the location of the appointment.
     * @return the lcoation
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the appointment
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type of the appointment
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the start time of the appointment.
     * @return the start time
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the appointment.
     * @param startTime
     */
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    /**
     * gets the end time of the appointment
     * @return the end time
     */
    public Instant getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the appointment
     * @param endTime
     */
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    /**
     * gets the create date of the appointment
     * @return the create date
     */
    public Instant getCreateDate() {
        return createDate;
    }

    /**
     * sets the create date of the appointment.
     * @param createDate
     */
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    /**
     * gets the user who created the appointment.
     * @return user who created the appointment.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the users who created the appointment.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * gets the last update.
     * @return last update
     */
    public Instant getLastUpdate() {
        return lastUpdate;
    }

    /**
     * sets the last update time
     * @param lastUpdate
     */
    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated.
     * @return user who last updated
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * gets the customer id
     * @return the customer's id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * sets the customers id
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * gets the user id
     * @return the users id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * sets the user id
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

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
     * gets the start time in local time
     * @return local start time
     */
    public LocalDateTime getLocalStartTime() {
        return Helper.convertUTCToLocal(startTime);
    }

    /**
     * gets the end time in local time
     * @return local end time
     */
    public LocalDateTime getLocalEndTime() {
        return Helper.convertUTCToLocal(endTime);
    }
}
