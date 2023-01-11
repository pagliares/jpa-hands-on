package xyz.pagliares.jpa.titan.inheritance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import xyz.pagliares.jpa.titan.inheritance.entity.Customer;
import xyz.pagliares.jpa.titan.inheritance.entity.Employee;
import xyz.pagliares.jpa.titan.inheritance.entity.Person;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hands-on");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Creating an employee
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setFirstName("Maria");
        employee.setLastName("Silva");

        employee.setStreet("Conselheiro Mayrink");
        employee.setCity("Caxambu");
        employee.setState("MG");
        employee.setZip("37440-000");

        // Creating a customer
        Customer customer = new Customer();
        customer.setFirstName("João");
        customer.setLastName("Martins");

        customer.setStreet("Camilo Soares");
        customer.setCity("São Lourenço");
        customer.setState("MG");
        customer.setZip("35400-000");

        // Creating a person
        Person person = new Person();
        person.setFirstName("Tatiana");
        person.setLastName("Figueiredo");

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.persist(customer);
        entityManager.persist(person);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
