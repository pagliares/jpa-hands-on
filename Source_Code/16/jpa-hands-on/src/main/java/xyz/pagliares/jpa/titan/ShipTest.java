package xyz.pagliares.jpa.titan;

import jakarta.persistence.EntityManager;
import xyz.pagliares.jpa.titan.controller.ShipController;
import xyz.pagliares.jpa.titan.entity.Ship;
import xyz.pagliares.jpa.titan.entity.exception.ShipNotFoundException;
import xyz.pagliares.jpa.titan.integration.ShipDAO;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;
import xyz.pagliares.jpa.titan.utility.KeyboardInput;

import java.util.List;

public class ShipTest {
    private static EntityManager entityManager = DatabaseUtility.getEntityManager();

    private static ShipDAO shipDAO = new ShipDAO(entityManager);
    private static ShipController shipController = new ShipController(shipDAO);


    public static void main(String[] args) {
        System.out.println("Populating SHIP table with fake data to ease testing");
        DatabaseUtility.populateShipTable();
        System.out.println("2 ships in the SHIP table were created");

        String choice = showMenu();

        while (!choice.equals("6")) {
            try {
                switch (choice) {
                    case "1" -> createShip();
                    case "2" -> findShipById();
                    case "3" -> listAllShips();
                    case "4" -> updateShip();
                    case "5" -> removeShip();
                }
            } catch(ShipNotFoundException shipNotFoundException) {
            System.out.println(shipNotFoundException.getMessage());
         }

            KeyboardInput.readInputAsString("Press <Enter/Return> key to return to the main menu");
            clearConsole();
            choice = showMenu();
        }
        close();
    }
    public static String showMenu() {
        String choice = null;
        System.out.println("\n\t\tShip management");
        do {
            System.out.println("1 - Create ship");
            System.out.println("2 - Find ship by ID");
            System.out.println("3 - List all ships");
            System.out.println("4 - Update ship");
            System.out.println("5 - Remove ship");
            System.out.println("6 - Exit");

            choice = KeyboardInput.readInputAsString("Select an option (1, 2, 3, 4, 5, or 6)...: ");

        } while (!choice.equals("1") &&
                !choice.equals("2") &&
                !choice.equals("3") &&
                !choice.equals("4") &&
                !choice.equals("5") &&
                !choice.equals("6"));

        return choice;
    }
    public static void createShip() {
        // 1 - grab ship data
        String shipName = KeyboardInput.readInputAsString("Enter ship name...: ");
        Double tonnage = KeyboardInput.readInputAsDouble("Enter ship tonnage...: ");

        // 2 - create a ship object with the data read
        Ship ship = new Ship();
        ship.setName(shipName);
        ship.setTonnage(tonnage);

        // 3 - Delegate to the controller - System operation
        shipController.persist(ship);
        System.out.println("Ship created with success !");

    }
    public static Ship findShipById() {
        // 1 - read customer id
        String shipID = KeyboardInput.readInputAsString("Enter ship Id...: ");
        Integer id = Integer.parseInt(shipID);

        // 2 - Delegates to the controller (System operation)
        Ship ship = null;
        try {
            ship = shipController.findShip(id);

        } catch (ShipNotFoundException e) {
            System.out.println(e.getMessage());;
        }

        // 3 - Returns the ship
        System.out.println("Ship details..:  " + ship);
        return ship;
    }
    public static void removeShip() {
        // 1 - read ship id
        String shipID = KeyboardInput.readInputAsString("Enter cabin Id...: ");
        Integer id = Integer.parseInt(shipID);

        // 2 - Delegates to the controller (System operation)
        try {
            shipController.removeShip(id);
            System.out.println("Ship removed with success !");
        } catch (ShipNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }
    public static void updateShip() throws ShipNotFoundException {
        // 1 - read ship id
        String shipID = KeyboardInput.readInputAsString("Enter ship Id...: ");
        Integer id = Integer.parseInt(shipID);

        // 2 - Delegates to the controller (System operation)
        Ship ship = null;
        try {
            ship = shipController.findShip(id);
        } catch (ShipNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // 3 - Presents current data and Read new ship data
        String newName = KeyboardInput.readInputAsString("Enter new ship name for (" + ship.getName()+")...: ");
        Double tonnage = KeyboardInput.readInputAsDouble("Enter new ship tonnage (" + ship.getTonnage()+")...: ");

        // 4 - Updates ship details on the heap
        ship.setName(newName);
        ship.setTonnage(tonnage);

        // 5 - Delegates to persistence
        shipController.updateShip(ship);

        // 6 - Shows confirmations
        System.out.println("Ship data updated successfully !");

    }
    public static void listAllShips() {
        // 1 - Delegates to the controller (System operation)
        List<Ship> ships = null;
        try {
            ships = shipController.listAllShips();
            // 2 - Prints ship list  in the console
            System.out.println(ships);
        } catch (ShipNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }

    /**
     * There isn't such an easy way to clear the console Screen in a portable manner.
     * Most of the solutions only work for the operating system console
     * (not the console bundled with the IDE of your choice).
     * See https://rootstack.com/en/blog/java-clear-screen for more details.
     * For our purposes, let's cheat a little bit :) !
     **/
    private static void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }
    }
    public static void close() {
        DatabaseUtility.close();
        System.exit(0);
    }
}

