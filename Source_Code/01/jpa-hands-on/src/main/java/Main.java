import xyz.pagliares.hostelapp.entity.Guest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        Guest guest = new Guest();
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
