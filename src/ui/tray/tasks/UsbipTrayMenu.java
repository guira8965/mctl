package ui.tray.tasks;

import java.awt.MenuItem;

import commands.*;
import ui.tray.*;

public class UsbipTrayMenu implements TrayTask {
    private StartCommand startCommand;
    private StopCommand stopCommand;

    public UsbipTrayMenu(StartCommand startCommand, StopCommand stopCommand) {
        this.startCommand = startCommand;
        this.stopCommand = stopCommand;
    }
    
    @Override
    public String getMenuName() {
        return "USB/IP";
    }

    @Override
    public MenuItem[] getMenuItems() {
        MenuItem start = new MenuItem("Start");
        start.addActionListener(e -> {
            startCommand.run(3);
        });

        MenuItem stop = new MenuItem("Stop");
        stop.addActionListener(e -> {
            stopCommand.run(3);
        });

        return new MenuItem[]{start, stop};
    }
}
