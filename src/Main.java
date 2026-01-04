import config.*;
import services.*;
import commands.*;

import ui.cli.*;
import ui.cli.tasks.*;

import ui.tray.*;

import ui.tray.tasks.AllTrayMenu;
import ui.tray.tasks.MoonlightTrayMenu;
import ui.tray.tasks.UsbipTrayMenu;

import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String configPath = "config.dat";
        String imagePath = "icon.png";
        BuildConfig config = LoadConfig.loadOrDefault(configPath);

        // Use loaded config objects
        UsbipConfig usbipConfig = config.getUsbipConfig();
        MoonlightConfig moonlightConfig = config.getMoonlightConfig();
        BrightnessCtlConfig brightnessCtlConfig = config.getBrightnessCtlConfig();

        // Services
        SshService sshService = new SshService(null, null, 0);
        UsbipService usbipService = new UsbipService(sshService, usbipConfig);
        MoonlightService moonlightService = new MoonlightService(sshService, moonlightConfig);
        BrightnessCtlService brightnessctlService = new BrightnessCtlService(sshService, brightnessCtlConfig);
        
        // Commands
        StartCommand startCommand = new StartCommand(usbipService, moonlightService, brightnessctlService);
        StopCommand stopCommand = new StopCommand(usbipService, moonlightService, brightnessctlService);
        
        // UI
        CliHelper cliHelper = new CliHelper();
        SetupCliMenu setupCliMenu = new SetupCliMenu(configPath, cliHelper);
        StartCliMenu cliStartMenu = new StartCliMenu(cliHelper, startCommand);
        StopCliMenu cliStopMenu = new StopCliMenu(cliHelper, stopCommand);
        ConfigureCliMenu cliConfigureMenu = new ConfigureCliMenu(configPath, config, cliHelper, moonlightConfig, usbipConfig, brightnessCtlConfig, brightnessctlService);

        if (config.isAnyEmpty()) {
            setupCliMenu.run(brightnessCtlConfig, moonlightConfig, usbipConfig);
            try {
                SaveConfig.saveConfig(config, configPath);
            } catch (IOException e) {
                System.err.println("Failed to save config: " + e.getMessage());
            }
        }

        // Finally, point to main menu
        MainCliMenu mainMenu = new MainCliMenu(cliHelper, cliStartMenu, cliStopMenu, cliConfigureMenu);

        Thread tray = new Thread(() -> {
            List<TrayTask> tasks = List.of(
                new AllTrayMenu(startCommand, stopCommand),
                new MoonlightTrayMenu(startCommand, stopCommand),
                new UsbipTrayMenu(startCommand, stopCommand)
            );

            try {
                new TrayApp(tasks).run(imagePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread cli = new Thread(() -> {
            cliHelper.clear();
            mainMenu.run();
        });

        cli.start();
        // tray.start();
    }
}