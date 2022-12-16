package xyz.pagliares.jpa.titan.integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import xyz.pagliares.jpa.titan.entity.Cabin;
import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.entity.exception.CabinNotFoundException;
import xyz.pagliares.jpa.titan.entity.exception.CustomerNotFoundException;

import java.util.List;

public class CabinDAO extends DAO {
    public CabinDAO(EntityManager entityManager) {
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

    public List<Cabin> listAllCabins() throws CabinNotFoundException {
        EntityManager entityManager = this.getEntityManager();
        Query query = entityManager.createQuery("SELECT C FROM Cabin C");
        List<Cabin> result = (List<Cabin>)query.getResultList();
        if (result.size() == 0)
            throw new CabinNotFoundException("There are no cabins in the database !");
        return result;
    }
}
