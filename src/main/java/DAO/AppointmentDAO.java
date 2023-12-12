package DAO;

import Model.Appointment;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for the appointment access in the database
 */
public interface AppointmentDAO {
    /**
     * Adds appointment to the database
     * @param appointment
     * @return number of appointments added to the database
     * @throws SQLException
     */
    public int addAppointment(Appointment appointment) throws SQLException;

    /**
     * Deletes an appointment from the database
     * @param appointmentID
     * @return number of appointments deleted from the database
     * @throws SQLException
     */
    public int deleteAppointment(int appointmentID) throws SQLException;

    /**
     * Updates a current appointment in the database
     * @param appointment
     * @return number of appointments updated
     * @throws SQLException
     */
    public int updateAppointment(Appointment appointment) throws SQLException;

    /**
     * Finds all the appointments listed in the database
     * @return a list of appointments
     * @throws SQLException
     */
    public List<Appointment> findAll() throws SQLException;

    /**
     * Finds one appointment by ID
     * @param appointmentID
     * @return An appointment
     * @throws SQLException
     */
    public Appointment findByID(int appointmentID) throws SQLException;
}
