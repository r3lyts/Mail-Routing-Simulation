package DAO;

import Model.Appointment;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDAO {
    public int addAppointment(Appointment appointment) throws SQLException;
    public int deleteAppointment(int appointmentID) throws SQLException;
    public int updateAppointment(Appointment appointment) throws SQLException;
    public List<Appointment> findAll() throws SQLException;
    public Appointment findByID(int appointmentID) throws SQLException;
}
