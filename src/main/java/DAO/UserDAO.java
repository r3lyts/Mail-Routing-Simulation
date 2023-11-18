package DAO;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User findUserByUsername(String username) throws SQLException;
    int insert(String username, String password) throws SQLException;
    int delete(int userID) throws SQLException;
    public List<User> findAll() throws SQLException;

    }
