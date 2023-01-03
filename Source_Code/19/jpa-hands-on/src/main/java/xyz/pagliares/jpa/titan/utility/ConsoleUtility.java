package xyz.pagliares.jpa.titan.utility;

public class ConsoleUtility {
    /**
     * There isn't such an easy way to clear the console Screen in a portable manner.
     * Most of the solutions only work for the operating system console
     * (not the console bundled with the IDE of your choice).
     * See https://rootstack.com/en/blog/java-clear-screen for more details.
     * For our purposes, let's cheat a little bit :) !
     **/
    public static void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }
    }

    public static void close() {
        DatabaseUtility.close();
        System.exit(0);
    }
}
