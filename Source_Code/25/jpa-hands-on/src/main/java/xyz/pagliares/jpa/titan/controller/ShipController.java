package xyz.pagliares.jpa.titan.controller;

import xyz.pagliares.jpa.titan.entity.Ship;
import xyz.pagliares.jpa.titan.entity.exception.ShipNotFoundException;
import xyz.pagliares.jpa.titan.integration.ShipDAO;

import java.util.List;

public class ShipController {
    private ShipDAO shipDAO;
    public ShipController(ShipDAO shipDAO) {
        this.shipDAO = shipDAO;;
    }
    public void persist(Ship ship) {
        shipDAO.persist(ship);
    }

    public Ship findShip(int id) throws ShipNotFoundException {
        return shipDAO.findShip(id);
    }
    public List<Ship> listAllShips() throws ShipNotFoundException {
        return shipDAO.listAllShips();
    }
    public void removeShip(int id) throws ShipNotFoundException{
        shipDAO.removeShip(id);
    }
    public void updateShip(Ship ship) {
        shipDAO.update(ship);
    }
}
