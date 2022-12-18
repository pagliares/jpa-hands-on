package xyz.pagliares.jpa.titan.integration;

import jakarta.persistence.EntityManager;

public abstract class DAO {
    private EntityManager entityManager;
    public DAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
