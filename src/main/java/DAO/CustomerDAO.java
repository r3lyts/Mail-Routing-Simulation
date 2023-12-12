package DAO;

import Model.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for accessing the customer database.
 */
public interface CustomerDAO {

    /**
     * Adds a customer to the customer database.
     * @param customer
     * @return number of customers added to the database
     * @throws SQLException
     */
    public int addCustomer(Customer customer) throws SQLException;

    /**
     * Deletes a customer from the database.
     * @param customerID
     * @return number of customers deleted from the database
     * @throws SQLException
     */
    public int deleteCustomer(int customerID) throws SQLException;

    /**
     * Updates a customer from the database
     * @param customer
     * @return number of customers updated in the database
     * @throws SQLException
     */
    public int updateCustomer(Customer customer) throws SQLException;

    /**
     * Finds a specific customer by customer id
     * @param customerID
     * @return a customer object
     * @throws SQLException
     */
    Customer findByCustomerID(int customerID) throws SQLException;

    /**
     * Finds a list of all customers in the database.
     * @return list of customers
     * @throws SQLException
     */
    List<Customer> findAllCustomers() throws SQLException;
}
