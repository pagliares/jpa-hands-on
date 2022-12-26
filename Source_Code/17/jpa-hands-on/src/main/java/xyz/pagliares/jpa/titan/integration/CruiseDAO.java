package xyz.pagliares.jpa.titan.integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import xyz.pagliares.jpa.titan.entity.Cruise;
import xyz.pagliares.jpa.titan.entity.exception.CruiseNotFoundException;

import java.util.List;

public class CruiseDAO extends DAO {
    public CruiseDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Cruise findCruise(int id) throws CruiseNotFoundException {
        Cruise cruise = this.getEntityManager().find(Cruise.class, id);
        if (cruise == null)
            throw new CruiseNotFoundException("Cruise with ID " + id + " not found !");
        return cruise;
    }
    public List<Cruise> listAllCruises() throws CruiseNotFoundException {
        EntityManager entityManager = this.getEntityManager();
        Query query = entityManager.createQuery("SELECT C FROM Cruise C");
        List<Cruise> result = (List<Cruise>)query.getResultList();
        if (result.size() == 0)
            throw new CruiseNotFoundException("There are no cruises in the database !");
        return result;
    }
    public void removeCruise(int id) throws CruiseNotFoundException{
        this.getEntityManager().getTransaction().begin();
        Cruise cruise = this.getEntityManager().find(Cruise.class, id);
        if (cruise == null)
            throw new CruiseNotFoundException("Cruise with ID " + id + " not found !");
        this.getEntityManager().remove(cruise);
        this.getEntityManager().getTransaction().commit();
    }
    public void updateCruise(Cruise cruise) {
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().merge(cruise);
        this.getEntityManager().getTransaction().commit();
    }
}
