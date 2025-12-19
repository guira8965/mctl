package ui;

import java.util.Scanner;
import commands.*;

public class CliStopMenu {
    private final Scanner scanner = new Scanner(System.in);

    private final CliHelper cliHelper;
    private final StopCommand stopCommand;

    public CliStopMenu(CliHelper cliHelper, StopCommand stopCommand) {
        this.cliHelper = cliHelper;
        this.stopCommand = stopCommand;
    }

    public void run() {
        while (true) {
            cliHelper.clear();
            System.out.println("<-- mctl/stop -->");
            System.out.println("[1] Stop All");
            System.out.println("[2] Stop Moonlight");
            System.out.println("[3] Stop USB/IP");
            System.out.println("[0] Back");
            System.out.print("Select an option: ");

            String choice = String.valueOf(scanner.next().trim());
            
            switch (choice) {
                case "0" -> {return;}
                case "1" -> {stopCommand.run(1);}
                case "2" -> {stopCommand.run(2);}
                case "3" -> {stopCommand.run(3);}  
                default -> {
                    System.out.println("Invalid option. Try again.");
                    cliHelper.promptEnterKey();
                    continue;
                }
            }
        }
    }
}
