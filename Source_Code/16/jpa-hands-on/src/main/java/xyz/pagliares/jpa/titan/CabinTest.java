package xyz.pagliares.jpa.titan;

import jakarta.persistence.EntityManager;
import xyz.pagliares.jpa.titan.controller.CabinController;
import xyz.pagliares.jpa.titan.entity.Cabin;
import xyz.pagliares.jpa.titan.entity.exception.CabinNotFoundException;
import xyz.pagliares.jpa.titan.integration.CabinDAO;
import xyz.pagliares.jpa.titan.utility.DatabaseUtility;
import xyz.pagliares.jpa.titan.utility.KeyboardInput;

import java.util.List;
import java.util.Random;

public class CabinTest {
    private static EntityManager entityManager = DatabaseUtility.getEntityManager();

    private static CabinDAO cabinDAO = new CabinDAO(entityManager);
    private static CabinController cabinController = new CabinController(cabinDAO);


    public static void main(String[] args) {
        System.out.println("Populating Cabin table with fake data to ease testing");
        populateCabinTable();
        System.out.println("10 cabins in the Cabin table were created");

        String choice = showMenu();

        while (!choice.equals("6")) {
            try {
                switch (choice) {
                    case "1" -> createCabin();
                    case "2" -> findCabinById();
                    case "3" -> listAllCabins();
                    case "4" -> updateCabin();
                    case "5" -> removeCabin();
                }
            } catch(CabinNotFoundException cabinNotFoundException) {
            System.out.println(cabinNotFoundException.getMessage());
         }

            KeyboardInput.readInputAsString("Press <Enter/Return> key to return to the main menu");
            clearConsole();
            choice = showMenu();
        }
        close();
    }
    public static String showMenu() {
        String choice = null;
        System.out.println("\n\t\tCabin management");
        do {
            System.out.println("1 - Create cabin");
            System.out.println("2 - Find cabin by ID");
            System.out.println("3 - List all cabins");
            System.out.println("4 - Update cabin");
            System.out.println("5 - Remove cabin");
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
    public static void createCabin() {
        // 1 - grab cabin data
        String cabinName = KeyboardInput.readInputAsString("Enter cabin name...: ");
        Integer deckLevel = KeyboardInput.readInputAsInteger("Enter deck level of the cabin...: ");

        Integer shipID = KeyboardInput.readInputAsInteger("Enter ID of the Ship the cabin is located ");

        Integer bedCount = KeyboardInput.readInputAsInteger("Enter number of beds in this cabin..: ");

        // 2 - create a cabin object with the data read
        Cabin cabin = new Cabin();
        cabin.setName(cabinName);
        cabin.setDeckLevel(deckLevel);
        cabin.setShipId(bedCount);

        // 3 - Delegate to the controller - System operation
        cabinController.persist(cabin);
        System.out.println("Cabin created with success !");

    }
    public static Cabin findCabinById() {
        // 1 - read customer id
        String cabinID = KeyboardInput.readInputAsString("Enter cabin Id...: ");
        Integer id = Integer.parseInt(cabinID);

        // 2 - Delegates to the controller (System operation)
        Cabin cabin = null;
        try {
            cabin = cabinController.findCabin(id);

        } catch (CabinNotFoundException e) {
            System.out.println(e.getMessage());;
        }

        // 3 - Returns the cabin
        System.out.println("Cabin details..:  " + cabin);
        return cabin;
    }
    public static void removeCabin() {
        // 1 - read cabin id
        String cabinID = KeyboardInput.readInputAsString("Enter cabin Id...: ");
        Integer id = Integer.parseInt(cabinID);

        // 2 - Delegates to the controller (System operation)
        try {
            cabinController.removeCabin(id);
            System.out.println("Customer removed with success !");
        } catch (CabinNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }
    public static void updateCabin() throws CabinNotFoundException {
        // 1 - read cabin id
        String cabinID = KeyboardInput.readInputAsString("Enter cabin Id...: ");
        Integer id = Integer.parseInt(cabinID);

        // 2 - Delegates to the controller (System operation)
        Cabin cabin = null;
        try {
            cabin = cabinController.findCabin(id);
        } catch (CabinNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        // 3 - Presents current data and Read new cabin data
        String newName = KeyboardInput.readInputAsString("Enter new cabin name for (" + cabin.getName()+")...: ");
        Integer newDeckLevel = KeyboardInput.readInputAsInteger("Enter new deck level for the cabin (" + cabin.getDeckLevel()+")...: ");
        Integer newShipID = KeyboardInput.readInputAsInteger("Enter new ID of the Ship the cabin is located (" + cabin.getShipId()+")...: ");
        Integer newBedCount = KeyboardInput.readInputAsInteger("Enter new number of beds in this cabin (" + cabin.getBedCount()+")..: ");

        // 4 - Updates cabin details on the heap
        cabin.setName(newName);
        cabin.setDeckLevel(newDeckLevel);
        cabin.setShipId(newShipID);
        cabin.setBedCount(newBedCount);

        // 5 - Delegates to persistence
        cabinController.updateCabin(cabin);

        // 6 - Shows confirmations
        System.out.println("Cabin data updated successfully !");

    }
    public static void listAllCabins() {
        // 1 - Delegates to the controller (System operation)
        List<Cabin> cabins = null;
        try {
            cabins = cabinController.listAllCabins();
            // 2 - Prints customer list  in the console
            System.out.println(cabins);
        } catch (CabinNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void populateCabinTable() {
        Random random = new Random();

        // 1 - create 10 cabin objects in order to have some  initial data in the
        // table Cabin in the database
        Cabin cabin = new Cabin();
        for (int i = 1; i <= 10; i++) {
            cabin.setName("Cabin name" + i);
            int result = random.nextInt(5)+1;
            cabin.setDeckLevel(result);
            cabin.setShipId(5);
            cabin.setBedCount(result);

            // 2 - Delegate the persistence to the controller - System operation
            cabinController.persist(cabin);

            // 3 - creates another cabin
            cabin = new Cabin();
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

