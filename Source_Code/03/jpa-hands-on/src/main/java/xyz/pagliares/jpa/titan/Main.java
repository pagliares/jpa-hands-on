package xyz.pagliares.jpa.titan;

import jakarta.persistence.EntityManager;
import xyz.pagliares.jpa.titan.controller.CabinController;
import xyz.pagliares.jpa.titan.controller.CustomerController;
import xyz.pagliares.jpa.titan.entity.Cabin;
import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager = DatabaseUtility.getEntityManager();

        // Since we configured the primary key of Customer as auto increment of type Long,
        // we don't need to provided it.
        // If this was not the case we would explicitly specify it, e.g. customer.setId(3L)
        Customer customer = new Customer();
        customer.setFirstName("Florentino");
        customer.setLastName("Ariza");

        CustomerController customerController = new CustomerController(entityManager);
        customerController.persist(customer);

        // Since we configured the primary key of Cabin as auto increment of type int,
        // we don't need to provided it.
        // If this was not the case we would explicitly specify it, e.g. cabin.setId(1)

        Cabin cabin = new Cabin();
        cabin.setName("Master Suite");
        cabin.setBedCount(2);
        cabin.setDeckLevel(3);
        cabin.setShipId(1);

        CabinController cabinController = new CabinController(entityManager);
        cabinController.persist(cabin);

        // Finding a customer in the database
        customer = customerController.findCustomer(1L);
        System.out.println("Customer found: " + customer);

        // Finding a cabin in the database
        cabin = cabinController.findCabin(1);
        System.out.println("Cabin found: " + cabin);

        DatabaseUtility.close();
    }
}
