package DAO;

import Model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImp implements ContactDAO {

    public String getContactName(int contactID) throws SQLException {
        Contact contact = new Contact();
        String sql = "SELECT * FROM CONTACTS WHERE Contact_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            contact.setContactID(rs.getInt("Contact_ID"));
            contact.setContactName(rs.getString("Contact_Name"));
            contact.setEmail(rs.getString("Email"));
        }

        return contact.getContactName();
    }

    public List<Contact> findAll() throws SQLException {
        Contact contact = null;
        List<Contact> contactList = new ArrayList<>();

        String sql = "SELECT * FROM CONTACTS";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            contact = new Contact();
            contact.setContactID(rs.getInt("Contact_ID"));
            contact.setContactName(rs.getString("Contact_Name"));
            contact.setEmail(rs.getString("Email"));
            contactList.add(contact);

        }

        return contactList;
    }


}
