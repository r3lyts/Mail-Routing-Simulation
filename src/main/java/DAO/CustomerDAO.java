package DAO;

import Model.Customer;

import java.util.List;

public interface CustomerDAO {

    Customer findByCustomerID(int customerID);
    List<Customer> findAllCustomers();
}
