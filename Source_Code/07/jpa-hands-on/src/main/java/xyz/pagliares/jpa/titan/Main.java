package xyz.pagliares.jpa.titan;

import jakarta.persistence.EntityManager;
import xyz.pagliares.jpa.titan.controller.CustomerController;
import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.entity.exception.CustomerNotFoundException;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;
import xyz.pagliares.jpa.titan.utility.KeyboardInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static EntityManager entityManager = DatabaseUtility.getEntityManager();
    private static CustomerController customerController = new CustomerController(entityManager);

    /**
     * In this example, we are going to read from the keyboard dates in Brazilian format (day/month/year)
     * For example, 07/05/1977 for May 7th, 1977.
     *
     * If you rather use a format such as 07-May-1977, you can configure SimpleDateFormat as below:
     * private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
     * In this case, the month initials are: Jan, Fev, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;
     *
     * It is important to note that this example is based on the method used to manipulate date and time
     * BEFORE Java SE 8. Next example in this hands-on we will update to newer recommended methods to deal with
     * Dates and time (Since Java SE 8).
     **/
    private static Locale locale = new Locale("pt","BR");
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", locale);

    public static void main(String[] args) {
        System.out.println("Populating Customer table with fake data to ease testing");
        populateCustomerTable();
        System.out.println("10 customers in the Customer table were created");

        String choice = showMenu();

        while (!choice.equals("7")) {
            switch (choice) {
                case "1" -> createCustomer();
                case "2" -> findCustomerById();
                case "3" -> listAllCustomers();
                case "4" -> updateCustomer();
                case "5" -> removeCustomer();
                case "6" -> printCustomerAge();
            }
            KeyboardInput.readInputAsString("Press <Enter/Return> key to return to the main menu");
            clearConsole();
            choice = showMenu();
        }
        close();
    }
    public static String showMenu() {
        String choice = null;
        System.out.println("\n\t\tCustomer management");
        do {
            System.out.println("1 - Create customer");
            System.out.println("2 - Find customer by ID");
            System.out.println("3 - List all customers");
            System.out.println("4 - Update customer");
            System.out.println("5 - Remove customer");
            System.out.println("6 - Print customer age");
            System.out.println("7 - Exit");

            choice = KeyboardInput.readInputAsString("Select an option (1, 2, 3, 4, 5, 6 or 7)...: ");

        } while (!choice.equals("1") &&
                !choice.equals("2") &&
                !choice.equals("3") &&
                !choice.equals("4") &&
                !choice.equals("5") &&
                !choice.equals("6") &&
                !choice.equals("7"));

        return choice;
    }
    public static void createCustomer() {
        // 1 - grab customer data
        String customerName = KeyboardInput.readInputAsString("Enter customer name...: ");
        String customerLastName = KeyboardInput.readInputAsString("Enter customer last name...: ");
        Date customerBirthDate = null;
        try {
            customerBirthDate = KeyboardInput.readStringInputAsJavaUtilDate("Enter customer birthdate (e.g. 07/05/1977) ");
            System.out.println("Customer created with success !");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        // 2 - create a customer object with the data read
        Customer customer = new Customer();
        customer.setFirstName(customerName);
        customer.setLastName(customerLastName);
        customer.setBirthDate(customerBirthDate);

        // 3 - Delegate to the controller - System operation
        customerController.persist(customer);
    }
    public static Customer findCustomerById() {
        // 1 - read customer id
        String customerId = KeyboardInput.readInputAsString("Enter customer Id...: ");
        Long id = Long.parseLong(customerId);

        // 2 - Delegates to the controller (System operation)
        Customer customer = null;
        try {
            customer = customerController.findCustomer(id);

        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());;
        }

        // 3 - Returns the customer
        return customer;
    }
    public static void removeCustomer() {
        // 1 - read customer id
        String customerId = KeyboardInput.readInputAsString("Enter customer Id...: ");
        Long id = Long.parseLong(customerId);

        // 2 - Delegates to the controller (System operation)
        try {
            customerController.removeCustomer(id);
            System.out.println("Customer removed with success !");
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }
    public static void updateCustomer() {
        // 1 - read customer id
        String customerId = KeyboardInput.readInputAsString("Enter customer Id...: ");
        Long id = Long.parseLong(customerId);

        // 2 - Delegates to the controller (System operation)
        Customer customer = null;
        try {
            customer = customerController.findCustomer(id);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // 3 - Presents current data and Read new customer data
        String newName = KeyboardInput.readInputAsString("Enter new customer name for (" + customer.getFirstName()+")...: ");
        String newLastName = KeyboardInput.readInputAsString("Enter new customer last name for (" + customer.getLastName()+")...: ");
        Date birthdate = null;
        try {
            Date currentBirthdate = customer.getBirthDate();
            String dateFormatted = formatter.format(currentBirthdate);

            birthdate = KeyboardInput.readStringInputAsJavaUtilDate("Enter new customer birthdate (e.g. 07/05/1977) (" + dateFormatted+")...: ");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // 4 - Updates customer details on the heap
        customer.setFirstName(newName);
        customer.setLastName(newLastName);
        customer.setBirthDate(birthdate);

        // 5 - Delegates to persistence
        customerController.updateCustomer(customer);

        // 6 - Shows confirmations
        System.out.println("Customer data updated successfully !");

    }
    public static void listAllCustomers() {
        // 1 - Delegates to the controller (System operation)
        List<Customer> customers = null;
        try {
            customers = customerController.listAllCustomers();
            // 2 - Prints customer list  in the console
            System.out.println(customers);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }
    public static void printCustomerAge() {
        Customer customer = findCustomerById();
        int age = customerController.calculateAge(customer.getBirthDate());
        System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() +
                " is" + age + " years old.");
    }
    public static void populateCustomerTable() {
        // Considering dates that start on 01/01/1930.
        GregorianCalendar gregorianCalendar = new GregorianCalendar(1930, 0, 1);
        Date initialDate = gregorianCalendar.getTime();

        // Considering dates that end on 31/12/2022.
        gregorianCalendar = new GregorianCalendar(2022, 11, 31);
        Date endDate = gregorianCalendar.getTime();


        // 1 - create 10 customer objects in order to have some  initial data in the
        // table Customer in the database
        Customer customer = new Customer();
        for (int i = 0; i <= 9; i++) {
            customer.setFirstName("First_name" + i);
            customer.setLastName("Last name" + i);
            customer.setBirthDate(generateRandomDateBetweenTwoDates(initialDate, endDate));

            // 2 - Delegate the persistence to the controller - System operation
            customerController.persist(customer);

            // 3 - creates another customer
            customer = new Customer();
        }
    }
    public static Date generateRandomDateBetweenTwoDates(Date startDateInclusive, Date endDateExclusive) {
        long startMillis = startDateInclusive.getTime();
        long endMillis = endDateExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
        return new Date(randomMillisSinceEpoch);
    }

    /**
     * There isn't such an easy way to clear the console Screen in a portable manner.
     * Most of the solutions only work for the operating system console
     * (not the console bundled with the IDE of your choice).
     * See https://rootstack.com/en/blog/java-clear-screen for more details.
     * For our purposes, let's cheat a little bit :) !
     **/
    private static void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }
    }
    public static void close() {
        DatabaseUtility.close();
        System.exit(0);
    }
}

