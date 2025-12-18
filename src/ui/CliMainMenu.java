package ui;

import java.util.*;

public class CliMainMenu {
    private final Scanner scanner = new Scanner(System.in);
    
    private final CliHelper cliHelper;
    private final CliStartMenu cliStartMenu;
    private final CliStopMenu cliStopMenu;
    private final CliConfigureMenu cliConfigureMenu;
    
    public CliMainMenu(
        CliHelper cliHelper,
        CliStartMenu cliStartMenu,
        CliStopMenu cliStopMenu,
        CliConfigureMenu cliConfigureMenu
    ) {
        this.cliHelper = cliHelper;
        this.cliStartMenu = cliStartMenu;
        this.cliStopMenu = cliStopMenu;
        this.cliConfigureMenu = cliConfigureMenu;
    }

    public void run() {
        while (true) {
            cliHelper.clear();
            System.out.println("--- mctl ---");
            System.out.println("[1] Start");
            System.out.println("[2] Stop");
            System.out.println("[3] Configure");
            System.out.println("[0] Exit");
            System.out.print("Select an option: ");

            String choice = String.valueOf(scanner.next().trim());
            scanner.nextLine();

            switch (choice) {
                case "0" -> {return;}
                case "1" -> {
                    cliHelper.clear();
                    cliStartMenu.run();
                }
                case "2" -> {
                    cliHelper.clear();
                    cliStopMenu.run();
                }
                case "3" -> {
                    cliHelper.clear();
                    cliConfigureMenu.run();
                } 
                default -> {
                    System.out.println("Invalid option. Try again.");
                    cliHelper.promptEnterKey();
                }
            }
        }
    }
}