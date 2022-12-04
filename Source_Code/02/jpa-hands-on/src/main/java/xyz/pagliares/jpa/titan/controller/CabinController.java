package xyz.pagliares.jpa.titan.controller;

import xyz.pagliares.jpa.titan.entity.Cabin;
import xyz.pagliares.jpa.titan.entity.Customer;

public class CabinController extends Controller{

    public boolean persist(Cabin cabin) {
        entityManager.getTransaction().begin();
        entityManager.persist(cabin);
        entityManager.getTransaction().commit();
        return true;
    }

    public Cabin findCabin(int id){
        return entityManager.find(Cabin.class, id);
    }
}
