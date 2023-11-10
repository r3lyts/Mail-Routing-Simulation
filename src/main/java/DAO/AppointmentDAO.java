package DAO;

import Model.Appointment;

import java.sql.SQLException;

public interface AppointmentDAO {
    public int addAppointment(Appointment appointment) throws SQLException;
    public int deleteAppointment(int appointmentID);
    public int updateAppointment();
}
