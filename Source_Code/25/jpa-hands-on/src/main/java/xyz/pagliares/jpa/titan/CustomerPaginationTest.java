package xyz.pagliares.jpa.titan;

import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.integration.CustomerDAO;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;

import java.util.List;

public class CustomerPaginationTest {
    public static void main(String[] args) {
        DatabaseUtility.populateDatabase();
        CustomerDAO customerDAO = DatabaseUtility.getCustomerDAO();
        List<Customer> customers = customerDAO.getCustomers(3,5);
        for (Customer customer:customers) {
            System.out.println(customer);
        }
    }
}
