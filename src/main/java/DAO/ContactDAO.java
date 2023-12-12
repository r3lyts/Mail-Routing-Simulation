package DAO;

import Model.Contact;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for accessing the Contact Database.
 *
 * @author tylersmall
 */
public interface ContactDAO {

    /**
     * Gets the contact name given a contact id
     * @param contactID
     * @return contact name
     * @throws SQLException
     */
    String getContactName(int contactID) throws SQLException;

    /**
     * Finds all the contacts in the database
     * @return list of contacts
     * @throws SQLException
     */
    List<Contact> findAll() throws SQLException;

    /**
     * Gets a contact id by using the name.
     * @param contactName
     * @return contact id
     * @throws SQLException
     */
    int getContactIDbyName(String contactName) throws SQLException;

    }
