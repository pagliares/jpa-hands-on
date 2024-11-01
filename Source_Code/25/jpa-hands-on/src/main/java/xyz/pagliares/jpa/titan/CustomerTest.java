package xyz.pagliares.jpa.titan;

import xyz.pagliares.jpa.titan.controller.CustomerController;
import xyz.pagliares.jpa.titan.entity.*;
import xyz.pagliares.jpa.titan.entity.exception.CustomerNotFoundException;
import xyz.pagliares.jpa.titan.entity.exception.CustomerTypeNotFoundException;
import xyz.pagliares.jpa.titan.integration.CustomerDAO;
import xyz.pagliares.jpa.titan.utility.ConsoleUtility;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;
import xyz.pagliares.jpa.titan.utility.DateTimeUtility;
import xyz.pagliares.jpa.titan.utility.KeyboardInput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CustomerTest {
    private static CustomerDAO customerDAO = DatabaseUtility.getCustomerDAO();
     private static CustomerController customerController = new CustomerController(customerDAO);
    public static void main(String[] args) {
        DatabaseUtility.populateDatabase();
        // Persisting one more customer
        Customer gregor = new Customer();
        gregor.setFirstName("Gregor");
        gregor.setLastName("Samsa");
        gregor.setBirthDate(LocalDate.of(1800, 5, 5));
        customerDAO.persist(gregor);

        String choice = showMenu();

        while (!choice.equals("9")) {
            try {
                switch (choice) {
                    case "1" -> createCustomer();
                    case "2" -> findCustomerById();
                    case "3" -> listAllCustomers();
                    case "4" -> updateCustomer();
                    case "5" -> removeCustomer();
                    case "6" -> printCustomerAge();
                    case "7" -> findCustomerByName();
                    case "8" -> findCustomerByBirthDate();

                }
            } catch(CustomerTypeNotFoundException customerTypeNotFoundException) {
                System.out.println(customerTypeNotFoundException.getMessage());
            }

            KeyboardInput.readInputAsString("Press <Enter/Return> key to return to the main menu");
            ConsoleUtility.clearConsole();
            choice = showMenu();
        }
        ConsoleUtility.close();
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
            System.out.println("7 - Find customer by name");
            System.out.println("8 - Find customer by birth date");
            System.out.println("9 - Exit");

            choice = KeyboardInput.readInputAsString("Select an option (1..9)...: ");

        } while (!choice.equals("1") &&
                !choice.equals("2") &&
                !choice.equals("3") &&
                !choice.equals("4") &&
                !choice.equals("5") &&
                !choice.equals("6") &&
                !choice.equals("7") &&
                !choice.equals("8") &&
                !choice.equals("9"));

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

        String customerStreet = KeyboardInput.readInputAsString("Enter customer street...: ");
        String customerCity = KeyboardInput.readInputAsString("Enter new customer city...: ");
        String customerState = KeyboardInput.readInputAsString("Enter new customer state...: ");

        Address address = new Address();
        address.setStreet(customerStreet);
        address.setCity(customerCity);
        address.setState(customerState);
        customer.setAddress(address);

        // Credit Card
        LocalDate expiration = null;
        String organization = KeyboardInput.readInputAsString("Enter credit card organization (VISA, MASTERCARD, or DINNERS)...: ");
        try {
            expiration = KeyboardInput.readStringInputAsJavaLocalDate("Enter credit card expiration date (e.g. 15/10/2027) ");

        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println(dateTimeParseException.getMessage());
            return;
        }

        String number = KeyboardInput.readInputAsString("Enter credit card number, e.g (1234-4321-1234-4321)..: ");
        String nameOnCard = KeyboardInput.readInputAsString("Enter name on card ...: ");
        Integer securityCode = KeyboardInput.readInputAsInteger("Enter security code ...: ");

        CreditCard creditCard = new CreditCard();
        creditCard.setOrganization(organization.toUpperCase());
        creditCard.setName(nameOnCard);
        creditCard.setExpiration(expiration);
        creditCard.setNumber(number);
        creditCard.setSecurityCode(securityCode);

        // Configure the bidirectional association
        creditCard.setCustomer(customer);
        customer.setCreditCard(creditCard);

        boolean more = true;
        while (more) {
            String phoneNumber = KeyboardInput.readInputAsString("Enter phone number. E.g: (+1-303-555-0137)...: ");
            Integer phoneType = KeyboardInput.readInputAsInteger("Enter phone type (1 or 2)...: ");
            Phone phone = new Phone();
            phone.setNumber(phoneNumber);
            phone.setType(phoneType);
            customer.addPhoneNumber(phone);
            String result = KeyboardInput.readInputAsString("Enter another phone? (Y/N)...: ");
            if (result.charAt(0) == 'n' || result.charAt(0) == 'N')
                break;
        }

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
            String dateFormatted = DateTimeUtility.dateTimeFormatter.format(currentBirthdate);

            birthDate = KeyboardInput.readStringInputAsJavaLocalDate("Enter new customer birthdate (" + dateFormatted+")...: ");

        } catch (DateTimeParseException dateTimeParseException) {
            throw new RuntimeException(dateTimeParseException);
        }

        String customerType = KeyboardInput.readInputAsString("Enter new customer type (REGISTERED, UNREGISTERED, BIG SPENDAH). Currently he/she is (" + customer.getCustomerType()+")...: ");

        String customerStreet = KeyboardInput.readInputAsString("Enter new customer street (" + customer.getAddress().getStreet()+")...: ");
        String customerCity = KeyboardInput.readInputAsString("Enter new customer city (" + customer.getAddress().getCity()+")...: ");
        String customerState = KeyboardInput.readInputAsString("Enter new customer state (" + customer.getAddress().getState()+")...: ");

        Address address = new Address();
        address.setStreet(customerStreet);
        address.setCity(customerCity);
        address.setState(customerState);

        for (Phone phone : customer.getPhoneNumbers()){
            String phoneNumber = KeyboardInput.readInputAsString("Enter new phone number (" + phone.getNumber()+")...: ");
            Integer phonetype = KeyboardInput.readInputAsInteger("Enter new phone type (" + phone.getType()+")...: ");
            phone.setNumber(phoneNumber);
            phone.setType(phonetype);
        }

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

        customer.setAddress(address);

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

    public static List<Customer> findCustomerByName() {
        // 1 - read customer name
        String customerFirstName = KeyboardInput.readInputAsString("Enter customer first name...: ");
        String customerLastName = KeyboardInput.readInputAsString("Enter customer last name...: ");

        // 2 - Delegates to the controller (System operation)
        List<Customer> customers = null;
        try {
            customers = customerController.findCustomer(customerFirstName, customerLastName);
            // 3 - Returns the customer
            for (Customer customer:customers) {
                System.out.println("Customer details..:  " + customer);
            }
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());;
        }

        return customers;
    }

    public static List<Customer> findCustomerByBirthDate() {
        // 1 - read customer name

        LocalDate customerBirthDate = null;
        try {
            customerBirthDate = KeyboardInput.readStringInputAsJavaLocalDate("Enter customer birthdate (e.g. 07/05/1977) ");

        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println(dateTimeParseException.getMessage());
        }

        // 2 - Delegate to the controller (System operation)
        List<Customer> customers = null;
        try {
            customers = customerController.findCustomerByBirthDate(customerBirthDate);
            for (Customer customer:customers) {
                System.out.println("Customer details..:  " + customer);
            }
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());;
        }
        return customers;
    }

}

