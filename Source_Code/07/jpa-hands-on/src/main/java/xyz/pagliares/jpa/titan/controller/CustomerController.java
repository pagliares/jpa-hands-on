package xyz.pagliares.jpa.titan.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.entity.exception.CustomerNotFoundException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerController extends Controller {
    public CustomerController(EntityManager entityManager) {
        super(entityManager);
    }
    public void persist(Customer customer) {
        this.getEntityManager().getTransaction().begin();
        // Just before persisting, generate date and time of creation
        // Using a not recommended java.util.Date class (pre Java SE 8)
        // In the next example we will use a better API (since Java SE 8) to deal
        // with date/time issues.
        customer.setTimeCreated(new Date());
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
    public void updateCustomer(Customer customer) {
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().merge(customer);
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

    public void updateCustomerBirthDate(Long id, Date newBirthDate) throws CustomerNotFoundException {
        Customer customer = this.getEntityManager().find(Customer.class, id);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with ID " + id + " not found !");
        this.getEntityManager().getTransaction().begin();
        customer.setBirthDate(newBirthDate);
        this.getEntityManager().merge(customer);
        this.getEntityManager().getTransaction().commit();
    }

    /**
     * This Method is unit tested properly for very different cases ,
     * taking care of Leap Year days difference in a year,
     * and date cases month and Year boundary cases (12/31/1980, 01/01/1980 etc)
     * This solution is presented at : https://bit.ly/3YkNqho
     **/
    public static int calculateAge(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
            age--;
        }
        return age;
    }
}
