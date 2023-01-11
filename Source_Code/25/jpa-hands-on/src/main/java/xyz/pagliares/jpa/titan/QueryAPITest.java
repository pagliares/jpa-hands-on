package xyz.pagliares.jpa.titan;

import jakarta.persistence.*;
import xyz.pagliares.jpa.titan.controller.CustomerController;
import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.integration.CustomerDAO;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;
import java.time.LocalDate;

public class QueryAPITest {

    private static CustomerDAO customerDAO = DatabaseUtility.getCustomerDAO();

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hands-on");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        DatabaseUtility.populateDatabase();

        // Persisting one more customer
        Customer gregor = new Customer();
        gregor.setFirstName("Gregor");
        gregor.setLastName("Samsa");
        gregor.setBirthDate(LocalDate.of(1800, 5, 5));

        customerDAO.persist(gregor);

        // Verifying whether Gregor Samsa was correctly persisted
        try {
            Query query = entityManager.createQuery(
                    "Select c from Customer c where c.firstName = 'Gregor' and c.lastName = 'Samsa'");
            Customer customer = (Customer)query.getSingleResult();
            System.out.println("Customer found! (details below)");
            System.out.println(customer);
        }
        catch(NoResultException noResultException){
            System.out.println("No customer found !");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
