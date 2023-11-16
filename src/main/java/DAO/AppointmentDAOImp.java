package DAO;

import Helper.SessionManager;
import Model.Appointment;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    public int deleteAppointment(int appointmentID) throws SQLException {

        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, appointmentID);
        return ps.executeUpdate();

    }
    public int updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.from(appointment.getStartTime()));
        ps.setTimestamp(6, Timestamp.from(appointment.getEndTime()));
        ps.setTimestamp(7, Timestamp.from(Instant.now()));
        ps.setString(8, appointment.getLastUpdatedBy());
        ps.setInt(9, appointment.getCustomerID());
        ps.setInt(10, appointment.getUserID());
        ps.setInt(11, appointment.getContactID());

        return ps.executeUpdate();
    }

    public List<Appointment> findAll() throws SQLException {
        List<Appointment> appointmentList = new ArrayList<>();
        Appointment appointment = null;

        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            appointment = new Appointment();
            appointment.setAppointmentID(rs.getInt("Appointment_ID"));
            appointment.setTitle(rs.getString("Title"));
            appointment.setDescription(rs.getString("Description"));
            appointment.setLocation(rs.getString("Location"));
            appointment.setType(rs.getString("Type"));
            Timestamp startTime = rs.getTimestamp("Start");
            if (startTime != null) {
                appointment.setStartTime(startTime.toInstant());
            }
            Timestamp endTime = rs.getTimestamp("End");
            if (endTime != null) {
                appointment.setEndTime(endTime.toInstant());
            }
            Timestamp createDateTimestamp = rs.getTimestamp("Create_Date");
            if (createDateTimestamp != null) {
                appointment.setCreateDate(createDateTimestamp.toInstant());
            }
            appointment.setCreatedBy(rs.getString("Created_By"));
            Timestamp lastUpdateTimestamp = rs.getTimestamp("Last_Update");
            if (lastUpdateTimestamp != null) {
                appointment.setLastUpdate(lastUpdateTimestamp.toInstant());
            }
            appointment.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            appointment.setCustomerID(rs.getInt("Customer_ID"));
            appointment.setUserID(rs.getInt("User_ID"));
            appointment.setContactID(rs.getInt("Contact_ID"));

            appointmentList.add(appointment);
        }

        return appointmentList;
    }


}
