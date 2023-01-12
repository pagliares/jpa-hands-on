package xyz.pagliares.jpa.titan;

import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.integration.CustomerDAO;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;

import java.util.Iterator;
import java.util.List;

public class CustomerPaginationTest {
    public static void main(String[] args) {
        DatabaseUtility.populateDatabase();
        CustomerDAO customerDAO = DatabaseUtility.getCustomerDAO();
        List<Customer> customers = customerDAO.getCustomers(3,5);

        System.out.println(""" 
                                        
                                        List 3 customers, starting with ID 6
        """);


        for (Customer customer:customers) {
            System.out.println(customer);
        }

        System.out.println("""
                            
                            List all customers, in small steps aiming performance improvement
        """);
        List results;
        int first = 0;
        int max =3;
        do {
            results = customerDAO.getCustomers(max, first);
            Iterator<Customer> it = results.iterator();
            while(it.hasNext()){
                System.out.println(it.next());
            }
            customerDAO.getEntityManager().clear();
            System.out.println("---------------------");
            first = first + results.size();
        } while (results.size() > 0);
    }
}
