package DAO;

import Model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * interface for accessing the user database.
 */
public interface UserDAO {

    /**
     * finds a user by user name
     * @param username
     * @return a user
     * @throws SQLException
     */
    User findUserByUsername(String username) throws SQLException;

    /**
     * Inserts a user into the database
     * @param username
     * @param password
     * @return number of users inserted into the database
     * @throws SQLException
     */
    int insert(String username, String password) throws SQLException;

    /**
     * Deletes a user from the database
     * @param userID
     * @return numbers of users deleted from the database
     * @throws SQLException
     */
    int delete(int userID) throws SQLException;

    /**
     * Gets a list of all the users in the user database.
     * @return a list of all users.
     * @throws SQLException
     */
    public List<User> findAll() throws SQLException;

    }
