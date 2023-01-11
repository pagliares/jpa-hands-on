package xyz.pagliares.jpa.titan.integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.entity.exception.CustomerNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class CustomerDAO extends DAO {
    public CustomerDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public void persist(Customer customer) {
        this.getEntityManager().getTransaction().begin();
        customer.setTimeCreated(LocalDateTime.now());
        this.getEntityManager().persist(customer);
        this.getEntityManager().getTransaction().commit();
    }
    public Customer findCustomer(Long id) throws CustomerNotFoundException{
        Customer customer = this.getEntityManager().find(Customer.class, id);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with ID " + id + " not found !");
        return customer;
    }
    public List<Customer> listAllCustomers() throws CustomerNotFoundException {
        EntityManager entityManager = this.getEntityManager();
        Query query = entityManager.createQuery("SELECT C FROM Customer C");
        List<Customer> result = (List<Customer>)query.getResultList();
        if (result.size() == 0)
            throw new CustomerNotFoundException("There are no customers in the database !");
        return result;
    }
    public void removeCustomer(Long id) throws CustomerNotFoundException{
        this.getEntityManager().getTransaction().begin();
        Customer customer = this.getEntityManager().find(Customer.class, id);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with ID " + id + " not found !");
        this.getEntityManager().remove(customer);
        this.getEntityManager().getTransaction().commit();
    }

    public void updateCustomerFirstName(Long id, String newFirstName) throws CustomerNotFoundException {
        Customer customer = this.getEntityManager().find(Customer.class, id);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with ID " + id + " not found !");
        this.getEntityManager().getTransaction().begin();
        customer.setFirstName(newFirstName);
        this.getEntityManager().merge(customer);
        this.getEntityManager().getTransaction().commit();
    }

    public void updateCustomerLastName(Long id, String newLastName) throws CustomerNotFoundException {
        Customer customer = this.getEntityManager().find(Customer.class, id);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with ID " + id + " not found !");
        this.getEntityManager().getTransaction().begin();
        customer.setFirstName(newLastName);
        this.getEntityManager().merge(customer);
        this.getEntityManager().getTransaction().commit();
    }

    public void updateCustomerBirthDate(Long id, LocalDate newBirthDate) throws CustomerNotFoundException {
        Customer customer = this.getEntityManager().find(Customer.class, id);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with ID " + id + " not found !");
        this.getEntityManager().getTransaction().begin();
        customer.setBirthDate(newBirthDate);
        this.getEntityManager().merge(customer);
        this.getEntityManager().getTransaction().commit();
    }

    public static int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public List<Customer> findByName(String firstName, String lastName) {
        String queryString = "SELECT c FROM Customer c WHERE c.firstName = :firstName AND c.lastName = :lastName";
        Query query = this.getEntityManager().createQuery(queryString);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}
