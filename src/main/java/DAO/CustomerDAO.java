package DAO;

import Model.Customer;

import java.util.List;

public interface CustomerDAO {

    public int addCustomer(Customer customer);
    public int deleteCustomer(int customerID);
    public int updateCustomer(int customerID);
    Customer findByCustomerID(int customerID);
    List<Customer> findAllCustomers();
}
