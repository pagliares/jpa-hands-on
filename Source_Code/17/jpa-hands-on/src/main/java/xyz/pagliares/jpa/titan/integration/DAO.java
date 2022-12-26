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

    public void persist(Object entity) {
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().persist(entity);
        this.getEntityManager().getTransaction().commit();
    }

}
