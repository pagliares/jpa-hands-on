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
        CustomerController customerController = new CustomerController(entityManager);
        CabinController cabinController = new CabinController(entityManager);

        System.out.println("\n\t\tExample demonstrating some database transactions");

        createCustomer(entityManager, customerController);

        createCabin(entityManager, cabinController);

        // Finding a customer in the database
        System.out.println("\nSearching for the customer with ID 1L in the database");
        Customer customer = customerController.findCustomer(1L);
        System.out.println("Customer found: " + customer);

        // Finding a cabin in the database
        System.out.println("\nSearching for the cabin with ID 1 in the database");
        Cabin cabin = cabinController.findCabin(1);
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

        // Removing the cabin with ID 1 (by using remove method of an EntityManager)
        System.out.println("\nRemoving the cabin with ID 1");
        cabinController.removeCabin(1);
        cabin = cabinController.findCabin(1);
        System.out.println("Cabin details after removal: " + cabin);

        createCabin(entityManager, cabinController);
        // Finding a cabin in the database
        // Note that since we configured the automatic generation of numerical primary keys (auto-increment)
        // in the entity bean Cabin, the next cabin ID is 2 and not 1. The value 1 for the primary key
        // was generated when we persisted the first cabin (remember we removed this cabin after
        // persisting it.
        System.out.println("\nSearching for the cabin with ID 2 in the database");
        cabin = cabinController.findCabin(2);
        System.out.println("Cabin found: " + cabin);


        DatabaseUtility.close();
    }
    public static void createCustomer(EntityManager entityManager, CustomerController customerController) {
        // Since we configured the primary key of Customer as auto increment of type Long,
        // we don't need to provided it.
        // If this was not the case we would explicitly specify it, e.g. customer.setId(3L)

        Customer customer = new Customer();
        customer.setFirstName("Florentino");
        customer.setLastName("Ariza");

        customerController.persist(customer);
    }
    public static void createCabin(EntityManager entityManager, CabinController cabinController) {
        // Since we configured the primary key of Cabin as auto increment of type int,
        // we don't need to provided it.
        // If this was not the case we would explicitly specify it, e.g. cabin.setId(1)

        Cabin cabin = new Cabin();
        cabin.setName("Sea view cabin");
        cabin.setBedCount(2);
        cabin.setDeckLevel(3);
        cabin.setShipId(1);

        cabinController.persist(cabin);
    }
}
