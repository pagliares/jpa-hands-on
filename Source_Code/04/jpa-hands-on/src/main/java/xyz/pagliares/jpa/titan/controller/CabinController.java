package xyz.pagliares.jpa.titan.controller;

import jakarta.persistence.EntityManager;
import xyz.pagliares.jpa.titan.entity.Cabin;

public class CabinController extends Controller {
    public CabinController(EntityManager entityManager) {
        super(entityManager);
    }
    public void persist(Cabin cabin) {
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().persist(cabin);
        this.getEntityManager().getTransaction().commit();
    }
    public Cabin findCabin(int id){
        return this.getEntityManager().find(Cabin.class, id);
    }

    public void updateBedCount(int id, int newCount) {
        Cabin cabin = this.getEntityManager().find(Cabin.class, id);
        cabin.setBedCount(newCount);
    }

    public void updateCabin(Cabin cabin) {
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().merge(cabin);
        this.getEntityManager().getTransaction().commit();
    }

    public void removeCabin(int id) {
        this.getEntityManager().getTransaction().begin();
        Cabin cabin = this.getEntityManager().find(Cabin.class, id);
        this.getEntityManager().remove(cabin);
        this.getEntityManager().getTransaction().commit();
    }
}
