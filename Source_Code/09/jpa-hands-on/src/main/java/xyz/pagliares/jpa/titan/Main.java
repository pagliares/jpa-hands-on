package xyz.pagliares.jpa.titan;

import jakarta.persistence.EntityManager;
import xyz.pagliares.jpa.titan.controller.CustomerController;
import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.entity.CustomerType;
import xyz.pagliares.jpa.titan.entity.exception.CustomerNotFoundException;
import xyz.pagliares.jpa.titan.entity.exception.CustomerTypeNotFoundException;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;
import xyz.pagliares.jpa.titan.utility.KeyboardInput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static EntityManager entityManager = DatabaseUtility.getEntityManager();
    private static CustomerController customerController = new CustomerController(entityManager);

    /**
     * In this example, we are going to read from the keyboard dates in Brazilian format (day/month/year)
     * For example, 07/05/1977 for May 7th, 1977.
     *
     * If you rather use a format such as 07-May-1977, you can configure DateTimeFormatter as below:
     * private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.US);
     * In this case, the month initials are: Jan, Fev, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;
     *
     **/
    private static Locale locale = new Locale("pt","BR");

    // SimpleDateFormat was used Pre Java-SE 8. DateTimeFormatter is used since Java-SE 8)
//    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", locale);

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", locale);

    public static void main(String[] args) {
        System.out.println("Populating Customer table with fake data to ease testing");
        populateCustomerTable();
        System.out.println("10 customers in the Customer table were created");

        String choice = showMenu();

        while (!choice.equals("7")) {
            try {
                switch (choice) {
                    case "1" -> createCustomer();
                    case "2" -> findCustomerById();
                    case "3" -> listAllCustomers();
                    case "4" -> updateCustomer();
                    case "5" -> removeCustomer();
                    case "6" -> printCustomerAge();
                }
            } catch(CustomerTypeNotFoundException customerTypeNotFoundException) {
                System.out.println(customerTypeNotFoundException.getMessage());
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
    public static void createCustomer() throws CustomerTypeNotFoundException {
        // 1 - grab customer data
        String customerName = KeyboardInput.readInputAsString("Enter customer name...: ");
        String customerLastName = KeyboardInput.readInputAsString("Enter customer last name...: ");
        LocalDate customerBirthDate = null;
        try {
              customerBirthDate = KeyboardInput.readStringInputAsJavaLocalDate("Enter customer birthdate (e.g. 07/05/1977) ");

        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println(dateTimeParseException.getMessage());
            return;
        }
        String customerType = KeyboardInput.readInputAsString("Enter type of customer (REGISTERED, UNREGISTERED, BIG SPENDAH)..: ");

        // 2 - create a customer object with the data read
        Customer customer = new Customer();
        customer.setFirstName(customerName);
        customer.setLastName(customerLastName);
        customer.setBirthDate(customerBirthDate);
        if (customerType.equalsIgnoreCase("REGISTERED"))
            customer.setCustomerType(CustomerType.REGISTERED);
        else if (customerType.equalsIgnoreCase("UNREGISTERED"))
            customer.setCustomerType(CustomerType.UNREGISTERED);
        else if (customerType.equalsIgnoreCase("BIG SPENDAH"))
            customer.setCustomerType(CustomerType.BIG_SPENDAH);
        else
            throw new CustomerTypeNotFoundException("Customer type invalid !");

        // 3 - Delegate to the controller - System operation
        customerController.persist(customer);
        System.out.println("Customer created with success !");

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
        System.out.println("Customer details..:  " + customer);
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
    public static void updateCustomer() throws CustomerTypeNotFoundException {
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
        LocalDate birthDate = null;
        try {
            LocalDate currentBirthdate = customer.getBirthDate();
            String dateFormatted = dateTimeFormatter.format(currentBirthdate);

            birthDate = KeyboardInput.readStringInputAsJavaLocalDate("Enter new customer birthdate (e.g. 07/05/1977) (" + dateFormatted+")...: ");

        } catch (DateTimeParseException dateTimeParseException) {
            throw new RuntimeException(dateTimeParseException);
        }

        String customerType = KeyboardInput.readInputAsString("Enter new customer type (REGISTERED, UNREGISTERED, BIG SPENDAH). Currently he/she is (" + customer.getCustomerType()+")...: ");

        // 4 - Updates customer details on the heap
        customer.setFirstName(newName);
        customer.setLastName(newLastName);
        customer.setBirthDate(birthDate);
        if (customerType.equalsIgnoreCase("REGISTERED"))
            customer.setCustomerType(CustomerType.REGISTERED);
        else if (customerType.equalsIgnoreCase("UNREGISTERED"))
            customer.setCustomerType(CustomerType.UNREGISTERED);
        else if (customerType.equalsIgnoreCase("BIG SPENDAH"))
            customer.setCustomerType(CustomerType.BIG_SPENDAH);
        else
            throw new CustomerTypeNotFoundException("Customer type invalid !");

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
                " is " + age + " years old.");
    }
    public static void populateCustomerTable() {
        Random random = new Random();
        // Considering dates that start on 01/01/1930.
        LocalDate initialDate = LocalDate.of(1930,1, 1);

        // Considering dates that ends now
        LocalDate endDate = LocalDate.now();


        // 1 - create 10 customer objects in order to have some  initial data in the
        // table Customer in the database
        Customer customer = new Customer();
        for (int i = 0; i <= 9; i++) {
            customer.setFirstName("First_name" + i);
            customer.setLastName("Last name" + i);
            customer.setSsn(123456L+i);
            customer.setBirthDate(generateRandomDateBetweenTwoObjectsLocalDate(initialDate, endDate));
            int result = random.nextInt(3)+1;
            if (result == 1)
                customer.setCustomerType(CustomerType.REGISTERED);
            else if (result ==2)
                customer.setCustomerType(CustomerType.UNREGISTERED);
            else
                customer.setCustomerType(CustomerType.BIG_SPENDAH);

            // 2 - Delegate the persistence to the controller - System operation
            customerController.persist(customer);

            // 3 - creates another customer
            customer = new Customer();
        }
    }

    /** Method to generate random java.util.Date (Pre Java SE 8) **/
    public static Date generateRandomDateBetweenTwoObjectsJavaUtilDates(Date startDateInclusive, Date endDateExclusive) {
        long startMillis = startDateInclusive.getTime();
        long endMillis = endDateExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
        return new Date(randomMillisSinceEpoch);
    }

    /** Method to generate random LocalDate (Since Java SE 8) **/
    public static LocalDate generateRandomDateBetweenTwoObjectsLocalDate(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        long minDay = startDateInclusive.toEpochDay();
        long maxDay = endDateExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return (randomDate);
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

