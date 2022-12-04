package xyz.pagliares.jpa.titan.controller;

import jakarta.persistence.EntityManager;

public abstract class Controller {
    private EntityManager entityManager;
    public Controller(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
