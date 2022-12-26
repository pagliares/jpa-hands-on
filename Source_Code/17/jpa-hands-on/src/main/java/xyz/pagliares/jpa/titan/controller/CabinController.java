package xyz.pagliares.jpa.titan.controller;

import xyz.pagliares.jpa.titan.entity.Cabin;
import xyz.pagliares.jpa.titan.entity.Customer;
import xyz.pagliares.jpa.titan.entity.exception.CabinNotFoundException;
import xyz.pagliares.jpa.titan.integration.CabinDAO;

import java.util.List;

public class CabinController {
    private CabinDAO cabinDAO;

    public CabinController(CabinDAO cabinDAO) {
        this.cabinDAO = cabinDAO;
    }
    public void persist(Cabin cabin) {
        cabinDAO.persist(cabin);
    }
    public Cabin findCabin(int id) throws CabinNotFoundException {
        return cabinDAO.findCabin(id);
    }

    public void updateBedCount(int id, int newCount) {
        cabinDAO.updateBedCount(id, newCount);
    }

    public void updateCabin(Cabin cabin) {
        cabinDAO.update(cabin);
    }

    public void removeCabin(int id) throws CabinNotFoundException {
        cabinDAO.removeCabin(id);
    }

    public List<Cabin> listAllCabins() throws CabinNotFoundException {
        return cabinDAO.listAllCabins();
    }
}
