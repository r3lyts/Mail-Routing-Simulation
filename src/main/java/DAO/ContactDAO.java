package DAO;

import Model.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactDAO {
    String getContactName(int contactID) throws SQLException;
    List<Contact> findAll() throws SQLException;

    int getContactIDbyName(String contactName) throws SQLException;

    }
