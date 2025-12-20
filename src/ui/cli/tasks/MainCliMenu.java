package ui.cli.tasks;

import ui.cli.CliHelper;

public class MainCliMenu {
    private final CliHelper cliHelper;
    private final StartCliMenu cliStartMenu;
    private final StopCliMenu cliStopMenu;
    private final ConfigureCliMenu cliConfigureMenu;
    
    public MainCliMenu(
        CliHelper cliHelper,
        StartCliMenu cliStartMenu,
        StopCliMenu cliStopMenu,
        ConfigureCliMenu cliConfigureMenu
    ) {
        this.cliHelper = cliHelper;
        this.cliStartMenu = cliStartMenu;
        this.cliStopMenu = cliStopMenu;
        this.cliConfigureMenu = cliConfigureMenu;
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            cliHelper.clear();
            System.out.println("<-- mctl -->");
            System.out.println("[1] Start");
            System.out.println("[2] Stop");
            System.out.println("[3] Configure");
            System.out.println("[0] Exit");
            switch (cliHelper.promptIntInRange("Select an option", 0, 3)) {
                case 0 -> {isRunning = cliHelper.promptExit();}
                case 1 -> {cliStartMenu.run();}
                case 2 -> {cliStopMenu.run();}
                case 3 -> {cliConfigureMenu.run();} 
                default -> {
                    cliHelper.notifyInvalidOption();
                    continue;
                }
            }
        }
    }
}