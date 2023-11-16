package DAO;

import Helper.SessionManager;
import Model.Country;
import Model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO{

    public int addCustomer(Customer customer) throws SQLException {
        Instant now = Instant.now();
        Timestamp currentTimestamp = Timestamp.from(now);
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5, currentTimestamp);
        ps.setString(6, SessionManager.getCurrentUser().getUsername());
        ps.setTimestamp(7, currentTimestamp);
        ps.setString(8, SessionManager.getCurrentUser().getUsername());
        ps.setInt(9, customer.getDivisionID());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }
    public int deleteCustomer(int customerID) {
        //delete appointments first
        return 0;
    }
    public int updateCustomer(Customer customer) throws SQLException {
        if (findByCustomerID(customer.getCustomerID()) == null) {
            return 0;
        }
        else {
            //update customer
            String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostalCode());
            ps.setString(4, customer.getPhone());
            ps.setTimestamp(5, Timestamp.from(Instant.now()));
            ps.setString(6, SessionManager.getCurrentUser().getUsername());
            ps.setInt(7, customer.getDivisionID());
            ps.setInt(8, customer.getCustomerID());

            return ps.executeUpdate();

        }

    }

    public Customer findByCustomerID(int customerID) throws SQLException {
        Customer customer = null;

        String sql = "SELECT * FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            customer = new Customer();
            customer.setCustomerID(rs.getInt("Customer_ID"));
            customer.setName(rs.getString("Customer_Name"));
            customer.setAddress(rs.getString("Address"));
            customer.setPostalCode(rs.getString("Postal_Code"));
            customer.setPhone(rs.getString("Phone"));
            Timestamp createDateTimestamp = rs.getTimestamp("Create_Date");
            if (createDateTimestamp != null) {
                customer.setCreateDate(createDateTimestamp.toInstant());
            }
            customer.setCreatedBy(rs.getString("Created_By"));
            Timestamp lastUpdateTimestamp = rs.getTimestamp("Last_Update");
            if (lastUpdateTimestamp != null) {
                customer.setLastUpdate(lastUpdateTimestamp.toInstant());
            }
            customer.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            customer.setDivisionID(rs.getInt("Division_ID"));
        }
        return customer;
    }
    public List<Customer> findAllCustomers() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = null;

        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            customer = new Customer();
            customer.setCustomerID(rs.getInt("Customer_ID"));
            customer.setName(rs.getString("Customer_Name"));
            customer.setAddress(rs.getString("Address"));
            customer.setPostalCode(rs.getString("Postal_Code"));
            customer.setPhone(rs.getString("Phone"));
            Timestamp createDateTimestamp = rs.getTimestamp("Create_Date");
            if (createDateTimestamp != null) {
                customer.setCreateDate(createDateTimestamp.toInstant());
            }
            customer.setCreatedBy(rs.getString("Created_By"));
            Timestamp lastUpdateTimestamp = rs.getTimestamp("Last_Update");
            if (lastUpdateTimestamp != null) {
                customer.setLastUpdate(lastUpdateTimestamp.toInstant());
            }
            customer.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            customer.setDivisionID(rs.getInt("Division_ID"));

            customerList.add(customer);
        }
        return customerList;
    }
}
