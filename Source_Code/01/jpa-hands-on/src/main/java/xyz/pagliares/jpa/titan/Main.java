package xyz.pagliares.jpa.titan;

import xyz.pagliares.jpa.titan.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        Customer guest = new Customer();
        guest.setFirstName("Florentino");
        guest.setLastName("Ariza");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hands-on");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(guest);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
