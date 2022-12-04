package xyz.pagliares.jpa.titan.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Controller {
    public static final EntityManagerFactory entityManagerFactory;
    public static final EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hands-on");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public boolean close() {
        if (entityManager != null)
            entityManager.close();
        if (entityManagerFactory != null)
            entityManagerFactory.close();
        return true;
    }
}
