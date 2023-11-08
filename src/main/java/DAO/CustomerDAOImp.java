package DAO;

import Model.Country;
import Model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImp {

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
