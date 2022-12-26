package xyz.pagliares.jpa.titan;

import jakarta.persistence.EntityManager;
import xyz.pagliares.jpa.titan.controller.CruiseController;
import xyz.pagliares.jpa.titan.controller.ShipController;
import xyz.pagliares.jpa.titan.entity.Cruise;
import xyz.pagliares.jpa.titan.entity.exception.CruiseNotFoundException;
import xyz.pagliares.jpa.titan.entity.exception.ShipNotFoundException;
import xyz.pagliares.jpa.titan.integration.CruiseDAO;
import xyz.pagliares.jpa.titan.integration.ShipDAO;
import xyz.pagliares.jpa.titan.utility.ConsoleUtility;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;
import xyz.pagliares.jpa.titan.utility.KeyboardInput;

import java.util.List;

public class CruiseTest {
    private static EntityManager entityManager = DatabaseUtility.getEntityManager();

    private static CruiseDAO cruiseDAO = new CruiseDAO(entityManager);
    private static CruiseController cruiseController = new CruiseController(cruiseDAO);

    private static ShipDAO shipDAO = new ShipDAO(entityManager);

    private static ShipController shipController = new ShipController(shipDAO);

    public static void main(String[] args) {
        System.out.println("Populating SHIP and CRUISE tables with fake data to ease testing");
        try {
            DatabaseUtility.populateShipTable();
            DatabaseUtility.populateCruiseTable();

        } catch (ShipNotFoundException shipNotFoundException) {
            System.out.println(shipNotFoundException.getMessage());
        }
        System.out.println("2 ships in the SHIP table were created");

        String choice = showMenu();

        while (!choice.equals("6")) {
            try {
                switch (choice) {
                    case "1" -> createCruise();
                    case "2" -> findCruiseById();
                    case "3" -> listAllCruises();
                    case "4" -> updateCruise();
                    case "5" -> removeCruise();
                }
            } catch(CruiseNotFoundException cruiseNotFoundException) {
                System.out.println(cruiseNotFoundException.getMessage());
            }

            KeyboardInput.readInputAsString("Press <Enter/Return> key to return to the main menu");
            ConsoleUtility.clearConsole();
            choice = showMenu();
        }
        close();
    }
    public static String showMenu() {
        String choice = null;
        System.out.println("\n\t\tShip management");
        do {
            System.out.println("1 - Create cruise");
            System.out.println("2 - Find cruise by ID");
            System.out.println("3 - List all cruises");
            System.out.println("4 - Update cruise");
            System.out.println("5 - Remove cruise");
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
    public static void createCruise() {
        // 1 - grab cruise data
        String cruiseName = KeyboardInput.readInputAsString("Enter cruise name...: ");
        int shipID = KeyboardInput.readInputAsInteger("Enter ship id...: ");

        // 2 - create a ship object with the data read
        Cruise cruise = new Cruise();
        cruise.setName(cruiseName);
        try {
            cruise.setShip(shipController.findShip(1));
        } catch (ShipNotFoundException shipNotFoundException) {
            System.out.println(shipNotFoundException.getMessage());
        }

        // 3 - Delegate to the controller - System operation
        cruiseController.persist(cruise);
        System.out.println("Cruise created with success !");

    }
    public static Cruise findCruiseById() {
        // 1 - read cruise id
        String cruiseID = KeyboardInput.readInputAsString("Enter cruise Id...: ");
        Integer id = Integer.parseInt(cruiseID);

        // 2 - Delegates to the controller (System operation)
        Cruise cruise = null;
        try {
            cruise = cruiseController.findCruise(id);

        } catch (CruiseNotFoundException e) {
            System.out.println(e.getMessage());;
        }

        // 3 - Returns the cruise
        System.out.println("Cruise details..:  " + cruise);
        return cruise;
    }
    public static void removeCruise() {
        // 1 - read cruise id
        String cruiseID = KeyboardInput.readInputAsString("Enter cruise Id...: ");
        Integer id = Integer.parseInt(cruiseID);

        // 2 - Delegates to the controller (System operation)
        try {
            cruiseController.removeCruise(id);
            System.out.println("Cruise removed with success !");
        } catch (CruiseNotFoundException cruiseNotFoundException) {
            System.out.println(cruiseNotFoundException.getMessage());;
        }
    }
    public static void updateCruise() throws CruiseNotFoundException {
        // 1 - read cruise id
        String cruiseID = KeyboardInput.readInputAsString("Enter cruise Id...: ");
        Integer id = Integer.parseInt(cruiseID);

        // 2 - Delegates to the controller (System operation)
        Cruise cruise = null;
        try {
            cruise = cruiseController.findCruise(id);
        } catch (CruiseNotFoundException cruiseNotFoundException) {
            System.out.println(cruiseNotFoundException.getMessage());
            return;
        }

        // 3 - Presents current data and Read new cruise data
        String newName = KeyboardInput.readInputAsString("Enter new cruise name for (" + cruise.getName()+")...: ");
        Integer shipID = KeyboardInput.readInputAsInteger("Enter new cruise ship (" + cruise.getShip().getId()+")...: ");

        // 4 - Updates ship details on the heap
        cruise.setName(newName);
        try {
            cruise.setShip(shipController.findShip(shipID));
        } catch (ShipNotFoundException shipNotFoundException) {
            System.out.println(shipNotFoundException.getMessage());
        }

        // 5 - Delegates to persistence
        cruiseController.updateCruise(cruise);

        // 6 - Shows confirmations
        System.out.println("Cruise data updated successfully !");

    }
    public static void listAllCruises() {
        // 1 - Delegates to the controller (System operation)
        List<Cruise> cruises = null;
        try {
            cruises = cruiseController.listAllCruises();
            // 2 - Prints ship list  in the console
            System.out.println(cruises);
        } catch (CruiseNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void close() {
        DatabaseUtility.close();
        System.exit(0);
    }
}

