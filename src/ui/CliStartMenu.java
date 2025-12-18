package ui;

import java.util.Scanner;
import commands.*;

public class CliStartMenu {
    private final Scanner scanner = new Scanner(System.in);

    private final CliHelper cliHelper;
    private final StartCommand startCommand;

    public CliStartMenu(CliHelper cliHelper, StartCommand startCommand){
        this.cliHelper = cliHelper;
        this.startCommand = startCommand;
    }
    
    public void run() {
        while (true) {
            cliHelper.clear();
            System.out.println("--- mctl/start ---");
            System.out.println("[1] Start All");
            System.out.println("[2] Start Moonlight");
            System.out.println("[3] Start USB/IP");
            System.out.println("[0] Back");
            System.out.print("Select an option: ");

            String choice = String.valueOf(scanner.next().trim());
            
            switch (choice) {
                case "0" -> {return;}
                case "1" -> {startCommand.run(1);}
                case "2" -> {startCommand.run(2);}
                case "3" -> {startCommand.run(3);}  
                default -> {
                    cliHelper.promptEnterKey();
                    continue;
                }
            }
        }
    }
}
