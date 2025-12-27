package ui.tray.tasks;

import java.awt.MenuItem;

import commands.*;
import ui.tray.*;

public class MoonlightTrayMenu implements TrayTask {
    private StartCommand startCommand;
    private StopCommand stopCommand;

    public MoonlightTrayMenu(StartCommand startCommand, StopCommand stopCommand) {
        this.startCommand = startCommand;
        this.stopCommand = stopCommand;
    }

    @Override
    public String getMenuName() {
        return "Moonlight";
    }

    @Override
    public MenuItem[] getMenuItems() {
        MenuItem start = new MenuItem("Start");
        start.addActionListener(e -> {
            startCommand.run(2);
        });

        MenuItem stop = new MenuItem("Stop");
        stop.addActionListener(e -> {
            stopCommand.run(2);
        });

        return new MenuItem[]{start, stop};
    }
}