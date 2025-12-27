package ui.tray.tasks;

import java.awt.MenuItem;

import commands.*;
import ui.tray.*;

public class AllTrayMenu implements TrayTask {
    private StartCommand startCommand;
    private StopCommand stopCommand;

    public AllTrayMenu(StartCommand startCommand, StopCommand stopCommand) {
        this.startCommand = startCommand;
        this.stopCommand = stopCommand;
    }

    @Override
    public String getMenuName() {
        return "All";
    }

    @Override
    public MenuItem[] getMenuItems() {
        MenuItem start = new MenuItem("Start");
        start.addActionListener(e -> {
            startCommand.run(1);
        });

        MenuItem stop = new MenuItem("Stop");
        stop.addActionListener(e -> {
            stopCommand.run(1);
        });

        return new MenuItem[]{start, stop};
    }
}