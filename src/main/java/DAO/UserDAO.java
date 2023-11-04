package DAO;

import Model.User;

import java.sql.SQLException;

public interface UserDAO {
    User findUserByUsername(String username) throws SQLException;
    int insert(String username, String password) throws SQLException;
    int delete(int userID) throws SQLException;
}
