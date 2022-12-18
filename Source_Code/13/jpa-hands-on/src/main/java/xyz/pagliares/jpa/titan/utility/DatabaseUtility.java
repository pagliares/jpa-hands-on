package xyz.pagliares.jpa.titan.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseUtility {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hands-on");
        entityManager = entityManagerFactory.createEntityManager();
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
}
