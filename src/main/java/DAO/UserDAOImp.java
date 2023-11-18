package DAO;

import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO{

    public User findUserByUsername(String username) throws SQLException {
        User user = null;
        String sql = "Select * FROM users Where User_Name = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            user = new User();
            user.setUserID(rs.getInt("User_ID"));
            user.setUsername(rs.getString("User_Name"));
            user.setPassword(rs.getString("Password"));
        }
        return user;
    }

    public int insert(String username, String password) throws SQLException {

        String sql = "INSERT INTO USERS (User_Name, Password) VALUES(?, ?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public int delete(int userID) throws SQLException {
        String sql = "DELETE FROM USERS WHERE User_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, userID);
        return ps.executeUpdate();


    }

    public List<User> findAll() throws SQLException {
        User user = null;
        List<User> userList = new ArrayList<>();

        String sql = "Select * FROM users";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            user = new User();
            user.setUserID(rs.getInt("User_ID"));
            user.setUsername(rs.getString("User_Name"));
            user.setPassword(rs.getString("Password"));
            userList.add(user);
        }
        return userList;
    }

}
