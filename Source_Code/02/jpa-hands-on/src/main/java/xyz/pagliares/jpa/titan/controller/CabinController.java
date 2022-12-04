package xyz.pagliares.jpa.titan.controller;

import xyz.pagliares.jpa.titan.entity.Cabin;

public class CabinController extends Controller{

    public boolean persist(Cabin cabin) {
        entityManager.getTransaction().begin();
        entityManager.persist(cabin);
        entityManager.getTransaction().commit();
        return true;
    }
}
