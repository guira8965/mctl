package ui.cli.tasks;

import ui.cli.*;
import commands.*;

public class StopCliMenu {
    private final CliHelper cliHelper;
    private final StopCommand stopCommand;

    public StopCliMenu(CliHelper cliHelper, StopCommand stopCommand) {
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
            
            switch (cliHelper.promptIntInRange("Select an option", 0, 3)) {
                case 0 -> {return;}
                case 1 -> {stopCommand.run(1);}
                case 2 -> {stopCommand.run(2);}
                case 3 -> {stopCommand.run(3);}  
                default -> {
                    cliHelper.notifyInvalidOption();
                    continue;
                }
            }
        }
    }
}
