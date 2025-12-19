import config.*;
import services.*;
import commands.*;
import ui.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BuildConfig config = LoadConfig.loadOrDefault("config.dat");

        // Use loaded config objects
        SshConfig sshConfig = config.getSshConfig();
        UsbipConfig usbipConfig = config.getUsbipConfig();
        MoonlightConfig moonlightConfig = config.getMoonlightConfig();

        // Services
        SshService sshService = new SshService(sshConfig);
        UsbipService usbipService = new UsbipService(sshService, usbipConfig);
        MoonlightService moonlightService = new MoonlightService(sshService, moonlightConfig);
        BrightnessCtlService brightnessctl = new BrightnessCtlService(sshService);
        
        // Commands
        StartCommand startCommand = new StartCommand(usbipService, moonlightService, brightnessctl);
        StopCommand stopCommand = new StopCommand(usbipService, moonlightService, brightnessctl);
        
        // UI
        CliHelper cliHelper = new CliHelper();
        CliStartMenu cliStartMenu = new CliStartMenu(cliHelper, startCommand);
        CliStopMenu cliStopMenu = new CliStopMenu(cliHelper, stopCommand);
        CliConfigureMenu cliConfigureMenu = new CliConfigureMenu(cliHelper, config, sshConfig, moonlightConfig, usbipConfig);

        if (config.isAnyEmpty()) {
            cliConfigureMenu.setupConfig(config.getSshConfig(), config.getMoonlightConfig(), config.getUsbipConfig());
            try {
                SaveConfig.saveConfig(config, "config.dat");
            } catch (IOException e) {
                System.err.println("Failed to save config: " + e.getMessage());
            }
        }

        // Finally, point to main menu
        CliMainMenu mainMenu = new CliMainMenu(cliHelper, cliStartMenu, cliStopMenu, cliConfigureMenu);
        cliHelper.clear();
        mainMenu.run();
    }
}