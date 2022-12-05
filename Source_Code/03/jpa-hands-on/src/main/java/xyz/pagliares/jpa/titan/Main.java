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

        System.out.println("\n\t\tExample demonstrating some database transactions");


        // Since we configured the primary key of Cabin as auto increment of type int,
        // we don't need to provided it.
        // If this was not the case we would explicitly specify it, e.g. cabin.setId(1)

        Cabin cabin = new Cabin();
        cabin.setName("Sea view cabin");
        cabin.setBedCount(2);
        cabin.setDeckLevel(3);
        cabin.setShipId(1);

        CabinController cabinController = new CabinController(entityManager);
        cabinController.persist(cabin);

        // Finding a customer in the database
        System.out.println("\nSearching for the customer with ID 1L in the database");

        customer = customerController.findCustomer(1L);
        System.out.println("Customer found: " + customer);

        // Finding a cabin in the database
        System.out.println("\nSearching for the cabin with ID 1 in the database");
        cabin = cabinController.findCabin(1);
        System.out.println("Cabin found: " + cabin);

        // Update number of beds from 2 to 3
        System.out.println("\nUpdating the number of beds from 2 to 3");
        cabinController.updateBedCount(1, 3);

        // Finding a cabin in the database in order to verify the update
        cabin = cabinController.findCabin(1);
        System.out.println("Cabin details: " + cabin);

        // Update the bean Cabin
        System.out.println("\nUpdating the cabin name from Sea view cabin to Master Suite by passing the whole updated bean entity");
        cabin = cabinController.findCabin(1);
        cabin.setName("Master Suite");
        cabinController.updateCabin(cabin);
        System.out.println("Cabin details: " + cabin);

        DatabaseUtility.close();
    }
}
