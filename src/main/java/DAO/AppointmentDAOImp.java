package DAO;

import Helper.SessionManager;
import Model.Appointment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

public class AppointmentDAOImp implements AppointmentDAO{

    public int addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, " +
                "Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getTitle());
        ps.setTimestamp(5, Timestamp.from(appointment.getStartTime()));
        ps.setTimestamp(6, Timestamp.from(appointment.getEndTime()));
        ps.setTimestamp(7, Timestamp.from(Instant.now()));
        ps.setString(8, SessionManager.getCurrentUser().getUsername());
        ps.setTimestamp(9, Timestamp.from(appointment.getLastUpdate()));
        ps.setString(10, SessionManager.getCurrentUser().getUsername());
        ps.setInt(11, appointment.getCustomerID());
        ps.setInt(12, appointment.getUserID());
        ps.setInt(13, appointment.getContactID());

        return ps.executeUpdate();
    }
    public int deleteAppointment(int appointmentID) {
        return 0;
    }
    public int updateAppointment() {
        return 0;
    }

}
