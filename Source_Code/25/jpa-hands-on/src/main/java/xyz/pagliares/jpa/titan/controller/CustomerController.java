package xyz.pagliares.jpa.titan.controller;

import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.entity.exception.CustomerNotFoundException;
import xyz.pagliares.jpa.titan.integration.CustomerDAO;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class CustomerController {
    private CustomerDAO customerDAO;
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;;
    }
    public void persist(Customer customer) {
        customerDAO.persist(customer);
    }
    public Customer findCustomer(Long id) throws CustomerNotFoundException{
        return customerDAO.findCustomer(id);
    }

    public List<Customer> findCustomer(String firstName, String secondName) throws CustomerNotFoundException{
        List<Customer> customers =  customerDAO.findByName(firstName, secondName);
        if (customers.size() == 0)
            throw new CustomerNotFoundException("Customer(s) not found !");
        return customers;
    }
    public List<Customer> listAllCustomers() throws CustomerNotFoundException {
        return customerDAO.listAllCustomers();
    }
    public void removeCustomer(Long id) throws CustomerNotFoundException{
        customerDAO.removeCustomer(id);
    }
    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }

    public void updateCustomerFirstName(Long id, String newFirstName) throws CustomerNotFoundException {
        customerDAO.updateCustomerFirstName(id, newFirstName);
    }

    public void updateCustomerLastName(Long id, String newLastName) throws CustomerNotFoundException {
        customerDAO.updateCustomerLastName(id, newLastName);
    }

    public void updateCustomerBirthDate(Long id, LocalDate newBirthDate) throws CustomerNotFoundException {
        customerDAO.updateCustomerBirthDate(id, newBirthDate);
    }

    /**
     * Different from previous method  public static int calculateAge(Date dateOfBirth)
     * that uses java.util.Date, this method uses the API for date and time introduced
     * in Java SE 8 (Recommended API nowadays).
     **/
    public static int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public List<Customer> findCustomerByBirthDate(LocalDate today) throws CustomerNotFoundException {
         return customerDAO.findCustomerByBirthDate(today);
    }
}
