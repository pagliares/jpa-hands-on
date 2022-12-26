package xyz.pagliares.jpa.titan.controller;

import xyz.pagliares.jpa.titan.entity.Cruise;
import xyz.pagliares.jpa.titan.entity.exception.CruiseNotFoundException;
import xyz.pagliares.jpa.titan.integration.CruiseDAO;
import java.util.List;

public class CruiseController {
    private CruiseDAO cruiseDAO;
    public CruiseController(CruiseDAO cruiseDAO) {
        this.cruiseDAO = cruiseDAO;;
    }
    public void persist(Cruise cruise) {
        cruiseDAO.persist(cruise);
    }

    public Cruise findCruise(int id) throws CruiseNotFoundException {
        return cruiseDAO.findCruise(id);
    }
    public List<Cruise> listAllCruises() throws CruiseNotFoundException {
        return cruiseDAO.listAllCruises();
    }
    public void removeCruise(int id) throws CruiseNotFoundException{
        cruiseDAO.removeCruise(id);
    }
    public void updateCruise(Cruise cruise) {
        cruiseDAO.updateCruise(cruise);
    }
}
