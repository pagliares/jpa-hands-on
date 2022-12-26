package xyz.pagliares.jpa.titan.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import xyz.pagliares.jpa.titan.controller.CruiseController;
import xyz.pagliares.jpa.titan.controller.CustomerController;
import xyz.pagliares.jpa.titan.controller.ShipController;
import xyz.pagliares.jpa.titan.entity.*;
import xyz.pagliares.jpa.titan.entity.exception.ShipNotFoundException;
import xyz.pagliares.jpa.titan.integration.CruiseDAO;
import xyz.pagliares.jpa.titan.integration.CustomerDAO;
import xyz.pagliares.jpa.titan.integration.ShipDAO;

import java.time.LocalDate;
import java.util.Random;

public class DatabaseUtility {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private static CustomerController customerController;
    private static ShipController shipController;

    private static CruiseController cruiseController;

    private static CustomerDAO customerDAO;
    private static ShipDAO shipDAO;

    private static CruiseDAO cruiseDAO;


    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hands-on");
        entityManager = entityManagerFactory.createEntityManager();
        customerDAO = new CustomerDAO(entityManager);
        customerController = new CustomerController(customerDAO);
        shipDAO = new ShipDAO(entityManager);
        shipController = new ShipController(shipDAO);
        cruiseDAO = new CruiseDAO(entityManager);
        cruiseController = new CruiseController(cruiseDAO);
    }
    public static EntityManager getEntityManager() {
        return entityManager;
    }
    public static boolean close() {
        if (entityManager.isOpen() || entityManager != null)
            entityManager.close();
        if (entityManagerFactory.isOpen() || entityManagerFactory != null)
            entityManagerFactory.close();
        return true;
    }

    public static CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public static void setCustomerDAO(CustomerDAO customerDAO) {
        DatabaseUtility.customerDAO = customerDAO;
    }

    public static void populateCustomerTable() {
        Random random = new Random();
        // Considering dates that start on 01/01/1930.
        LocalDate initialDate = LocalDate.of(1930, 1, 1);

        // Considering dates that ends now
        LocalDate endDate = LocalDate.now();

        // 1 - create 10 customer objects in order to have some  initial data in the
        // table Customer in the database
        Customer customer = new Customer();
        for (int i = 0; i <= 9; i++) {
            customer.setFirstName("First_name" + i);
            customer.setLastName("Last name" + i);
            customer.setSsn(123456L + i);
            customer.setBirthDate(DateTimeUtility.generateRandomDateBetweenTwoObjectsLocalDate(initialDate, endDate));
            int result = random.nextInt(3) + 1;
            if (result == 1)
                customer.setCustomerType(CustomerType.REGISTERED);
            else if (result == 2)
                customer.setCustomerType(CustomerType.UNREGISTERED);
            else
                customer.setCustomerType(CustomerType.BIG_SPENDAH);

            String street = "Fake street " + i;
            String city = "Fake city " + i;
            String state = "F" + i;

            Address fakeAddress = new Address();
            fakeAddress.setStreet(street);
            fakeAddress.setCity(city);
            fakeAddress.setState(state);
            customer.setAddress(fakeAddress);

            // CreditCard
            CreditCard creditCard = new CreditCard();

            if (result == 1)
                creditCard.setOrganization("VISA");
            else if (result == 2)
                creditCard.setOrganization("MASTERCARD");
            else if (result == 3)
                creditCard.setOrganization("DINNERS");

            creditCard.setName("Atticus Finch");
            creditCard.setExpiration(LocalDate.of(2027, 5, 1));
            creditCard.setNumber(i + "234-4567-7890-0123");
            creditCard.setSecurityCode(12 + i);

            // Configure the bidirectional association
            creditCard.setCustomer(customer);
            customer.setCreditCard(creditCard);

            // Create the phones
            Phone phone1 = new Phone();
            phone1.setNumber("+1-303-555-0137");
            phone1.setType(1);

            Phone phone2 = new Phone();
            phone2.setNumber("+1-561-555-0145");
            phone2.setType(2);
            customer.addPhoneNumber(phone1);
            customer.addPhoneNumber(phone2);

            // 2 - Delegate the persistence to the controller - System operation
            customerController.persist(customer);

            // 3 - creates another customer
            customer = new Customer();
        }
    }

    public static void populateShipTable() {
        Ship ship1 = new Ship();
        ship1.setName("Titanic");
        ship1.setTonnage(50_000.00);

        Ship ship2 = new Ship();
        ship2.setName("Celebrity");
        ship2.setTonnage(75_000.00);

        // 2 - Delegate the persistence to the controller - System operation
        shipController.persist(ship1);
        shipController.persist(ship2);
    }

    public static void populateCruiseTable() throws ShipNotFoundException {
        Cruise cruise1 = new Cruise();
        cruise1.setName("Caribbean sea");
        cruise1.setShip(shipController.findShip(1));

        Cruise cruise2 = new Cruise();
        cruise2.setName("Greek islands");
        cruise2.setShip(shipController.findShip(2));

        // 2 - Delegate the persistence to the controller - System operation
        cruiseController.persist(cruise1);
        cruiseController.persist(cruise2);
    }
}

