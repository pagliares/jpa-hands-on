package xyz.pagliares.jpa.titan.integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import xyz.pagliares.jpa.titan.entity.Cabin;
import xyz.pagliares.jpa.titan.entity.exception.CabinNotFoundException;
import java.util.List;

public class CabinDAO extends DAO {
    public CabinDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Cabin findCabin(int id){
        return this.getEntityManager().find(Cabin.class, id);
    }

    public List<Cabin> listAllCabins() throws CabinNotFoundException {
        EntityManager entityManager = this.getEntityManager();
        Query query = entityManager.createQuery("SELECT C FROM Cabin C");
        List<Cabin> result = (List<Cabin>)query.getResultList();
        if (result.size() == 0)
            throw new CabinNotFoundException("There are no cabins in the database !");
        return result;
    }

    public void removeCabin(int id) {
        this.getEntityManager().getTransaction().begin();
        Cabin cabin = this.getEntityManager().find(Cabin.class, id);
        this.getEntityManager().remove(cabin);
        this.getEntityManager().getTransaction().commit();
    }

    public void updateBedCount(int id, int newCount) {
        Cabin cabin = this.getEntityManager().find(Cabin.class, id);
        cabin.setBedCount(newCount);
    }
}
