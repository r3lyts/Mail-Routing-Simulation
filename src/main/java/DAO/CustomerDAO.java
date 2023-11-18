package DAO;

import Model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

    public int addCustomer(Customer customer) throws SQLException;
    public int deleteCustomer(int customerID) throws SQLException;
    public int updateCustomer(Customer customer) throws SQLException;
    Customer findByCustomerID(int customerID) throws SQLException;
    List<Customer> findAllCustomers() throws SQLException;
}
