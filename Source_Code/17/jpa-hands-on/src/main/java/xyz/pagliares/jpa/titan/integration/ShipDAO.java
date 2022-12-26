package xyz.pagliares.jpa.titan.integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import xyz.pagliares.jpa.titan.entity.Ship;
import xyz.pagliares.jpa.titan.entity.exception.ShipNotFoundException;
import java.util.List;

public class ShipDAO extends DAO {
    public ShipDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Ship findShip(int id) throws ShipNotFoundException{
        Ship ship = this.getEntityManager().find(Ship.class, id);
        if (ship == null)
            throw new ShipNotFoundException("Ship with ID " + id + " not found !");
        return ship;
    }
    public List<Ship> listAllShips() throws ShipNotFoundException {
        EntityManager entityManager = this.getEntityManager();
        Query query = entityManager.createQuery("SELECT S FROM Ship S");
        List<Ship> result = (List<Ship>)query.getResultList();
        if (result.size() == 0)
            throw new ShipNotFoundException("There are no ships in the database !");
        return result;
    }
    public void removeShip(int id) throws ShipNotFoundException{
        this.getEntityManager().getTransaction().begin();
        Ship ship = this.getEntityManager().find(Ship.class, id);
        if (ship == null)
            throw new ShipNotFoundException("Ship with ID " + id + " not found !");
        this.getEntityManager().remove(ship);
        this.getEntityManager().getTransaction().commit();
    }

}
