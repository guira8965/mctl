package ui.cli.tasks;

import ui.cli.*;
import config.*;
import services.*;

public class ConfigureCliMenu {
    String configPath;
    private final BuildConfig buildConfig;
    private final CliHelper cliHelper;
    private final CliPrompt cliPrompt;
    private MoonlightConfig moonlightConfig;
    private UsbipConfig usbipConfig;
    private BrightnessCtlConfig brightnessCtlConfig;
    private BrightnessCtlService brightnessCtlService;

    public ConfigureCliMenu (
        String configPath,
        BuildConfig buildConfig,
        CliHelper cliHelper,
        MoonlightConfig moonlightConfig,
        UsbipConfig usbipConfig,
        BrightnessCtlConfig brightnessCtlConfig,
        BrightnessCtlService brightnessCtlService
    ) {
        this.configPath = configPath;
        this.buildConfig = buildConfig;
        this.cliHelper = cliHelper;
        this.moonlightConfig = moonlightConfig;
        this.usbipConfig = usbipConfig;
        this.brightnessCtlConfig = brightnessCtlConfig;
        this.brightnessCtlService = brightnessCtlService;
        this.cliPrompt = new CliPrompt();
    }
    
    private void saveConfig() {
        try {
            SaveConfig.saveConfig(buildConfig, configPath);
        } catch (Exception e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }
    
    public void run() {
        boolean isVisible = true;
        while (isVisible) {
            cliHelper.clear();
            System.out.println("<-- mctl/configure -->");
            System.out.println("[1] Configure brightnessctl");
            System.out.println("[2] Configure Moonlight");
            System.out.println("[3] Configure USB/IP");
            System.out.println("[0] Back");

            switch (cliHelper.promptIntInRange("Select an option", 0, 3)) {
                case 0 -> {isVisible = !isVisible;}
                case 1 -> configureBrightnessCtl();
                case 2 -> configureMoonlight();
                case 3 -> configureUsbip();
                default -> {
                    System.out.println("Invalid option. Try again.");
                    break;
                }
            }
        }
    }

    private void configureBrightnessCtl(){
        boolean isVisible = true;
        while (isVisible) {
            cliHelper.clear();
            System.out.println("<-- mctl/configure/brightnessctl -->");
            System.out.println("[1] SSH User (" + brightnessCtlConfig.getSshUser() + ")");
            System.out.println("[2] SSH Host (" + brightnessCtlConfig.getSshHost() + ")");
            System.out.println("[3] SSH Port (" + brightnessCtlConfig.getSshPort() + ")");
            System.out.println("[4] Brightness Amount (" + brightnessCtlConfig.getBrightnessAmount() + ")");
            System.out.println("[0] Exit");

            switch (cliHelper.promptIntInRange("Select an option", 0, 4)) {
                case 0 -> {isVisible = !isVisible;}
                case 1 -> {
                    brightnessCtlConfig.setSshUser(cliHelper.promptString(cliPrompt.prompt[1][0][0], null));
                    saveConfig();
                }
                case 2 -> {
                    brightnessCtlConfig.setSshHost(cliHelper.promptString(cliPrompt.prompt[1][0][1], null));
                    saveConfig();
                }
                case 3 -> {
                    brightnessCtlConfig.setSshPort(cliHelper.promptInt(cliPrompt.prompt[1][0][2], "22"));
                    saveConfig();
                }
                case 4 -> {
                    brightnessCtlConfig.setBrightnessAmount(cliHelper.promptInt(cliPrompt.prompt[1][0][3], "10"));
                    brightnessCtlService.set();
                    saveConfig();
                }
                default -> {
                    System.out.println("Invalid option. Try again.");
                    cliHelper.pause();
                    continue;
                }
            }
        }
    }

    private void configureMoonlight() {
        boolean isVisible = true;
        while (isVisible) {
            cliHelper.clear();
            System.out.println("<-- mctl/configure/moonlight -->");
            System.out.println("[1] SSH User (" + moonlightConfig.getSshUser() + ")");
            System.out.println("[2] SSH Host (" + moonlightConfig.getSshHost() + ")");
            System.out.println("[3] SSH Port (" + moonlightConfig.getSshPort() + ")");
            System.out.println("[4] Display Number (" + moonlightConfig.getDisplayNumber() + ")");
            System.out.println("[5] Moonlight Path (" + moonlightConfig.getMoonlightPath() + ")");
            System.out.println("[6] Moonlight Host (" + moonlightConfig.getMoonlightHost() + ")");
            System.out.println("[7] Moonlight App (" + moonlightConfig.getMoonlightApp() + ")");
            System.out.println("[8] Resolution (" + moonlightConfig.getResolution() + ")");
            System.out.println("[9] FPS (" + moonlightConfig.getFps() + ")");
            System.out.println("[10] Bitrate (" + moonlightConfig.getBitrate() + ")");
            System.out.println("[11] Packet Size (" + moonlightConfig.getPacketSize() + ")");
            System.out.println("[12] Display Mode (" + moonlightConfig.getDisplayMode() + ")");
            System.out.println("[13] Video Encoder (" + moonlightConfig.getVideoEncoder() + ")");
            System.out.println("[14] Video Codec (" + moonlightConfig.getVideoCodec() + ")");
            System.out.println("[15] Performance Overlay (" + moonlightConfig.isPerformanceOverlay() + ")");
            System.out.println("[16] VSync (" + moonlightConfig.isVsync() + ")");
            System.out.println("[17] Frame Pacing (" + moonlightConfig.isFramePacing() + ")");
            System.out.println("[18] Game Optimization (" + moonlightConfig.isGameOptimization() + ")");
            System.out.println("[0] Back");

            switch (cliHelper.promptIntInRange("Select an option", 0, 18)) {
                case 0 -> { isVisible = !isVisible; }
                case 1 -> {
                    moonlightConfig.setSshUser(cliHelper.promptString(cliPrompt.prompt[1][1][0], null));
                    saveConfig();
                }
                case 2 -> {
                    moonlightConfig.setSshHost(cliHelper.promptString(cliPrompt.prompt[1][1][1], null));
                    saveConfig();
                }
                case 3 -> {
                    moonlightConfig.setSshPort(cliHelper.promptInt(cliPrompt.prompt[1][1][2], "22"));
                    saveConfig();
                }
                case 4 -> {
                    moonlightConfig.setDisplayNumber(cliHelper.promptInt(cliPrompt.prompt[1][1][3], "0"));
                    saveConfig();
                }
                case 5 -> {
                    moonlightConfig.setMoonlightPath(cliHelper.promptString(cliPrompt.prompt[1][1][4], "/usr/bin/moonlight"));
                    saveConfig();
                }
                case 6 -> {
                    moonlightConfig.setMoonlightHost(cliHelper.promptString(cliPrompt.prompt[1][1][5], null));
                    saveConfig();
                }
                case 7 -> {
                    moonlightConfig.setMoonlightApp(cliHelper.promptString(cliPrompt.prompt[1][1][6], "Desktop"));
                    saveConfig();
                }
                case 8 -> {
                    moonlightConfig.setResolution(cliHelper.promptString(cliPrompt.prompt[1][1][7], "1280x720"));
                    saveConfig();
                }
                case 9 -> {
                    moonlightConfig.setFps(cliHelper.promptInt(cliPrompt.prompt[1][1][8], "60"));
                    saveConfig();
                }
                case 10 -> {
                    moonlightConfig.setBitrate(cliHelper.promptInt(cliPrompt.prompt[1][1][9], "10000"));
                    saveConfig();
                }
                case 11 -> {
                    moonlightConfig.setPacketSize(cliHelper.promptInt(cliPrompt.prompt[1][1][10], "1500"));
                    saveConfig();
                }
                case 12 -> {
                    moonlightConfig.setDisplayMode(cliHelper.promptString(cliPrompt.prompt[1][1][11], "fullscreen"));
                    saveConfig();
                }
                case 13 -> {
                    moonlightConfig.setVideoEncoder(cliHelper.promptString(cliPrompt.prompt[1][1][12], "auto"));
                    saveConfig();
                }
                case 14 -> {
                    moonlightConfig.setVideoCodec(cliHelper.promptString(cliPrompt.prompt[1][1][13], "auto"));
                    saveConfig();
                }
                case 15 -> {
                    boolean flag = moonlightConfig.isPerformanceOverlay();
                    moonlightConfig.setPerformanceOverlay(!flag);
                    saveConfig();
                }
                case 16 -> {
                    boolean flag = moonlightConfig.isVsync();
                    moonlightConfig.setVsync(!flag);
                    saveConfig();
                }
                case 17 -> {
                    boolean flag = moonlightConfig.isFramePacing();
                    moonlightConfig.setFramePacing(!flag);
                    saveConfig();
                }
                case 18 -> {
                    boolean flag = moonlightConfig.isGameOptimization();
                    moonlightConfig.setGameOptimization(!flag);
                    saveConfig();
                }
                default -> {
                    System.out.println("Invalid option. Try again.");
                    continue;
                }
            }
        }
    }

    private void configureUsbip(){
        boolean isVisible = true;
        while (isVisible) {
            cliHelper.clear();
            System.out.println("<-- mctl/configure/usbip -->");
            System.out.println("[1] SSH User (" + usbipConfig.getSshUser() + ")");
            System.out.println("[2] SSH Host (" + usbipConfig.getSshHost() + ")");
            System.out.println("[3] SSH Port (" + usbipConfig.getSshPort() + ")");
            System.out.println("[4] USB/IP Host (" + usbipConfig.getUsbipHost() + ")");
            System.out.println("[5] USB/IP Bus ID/s (" + usbipConfig.getUsbipPorts() + ")");
            System.out.println("[0] Exit");

            switch (cliHelper.promptIntInRange("Select an option", 0, 5)) {
                case 0 -> {isVisible = !isVisible;}
                case 1 -> {
                    usbipConfig.setSshUser(cliHelper.promptString(cliPrompt.prompt[1][2][0], null));
                    saveConfig();
                }
                case 2 -> {
                    usbipConfig.setSshHost(cliHelper.promptString(cliPrompt.prompt[1][2][1], null));
                    saveConfig();
                }
                case 3 -> {
                    usbipConfig.setSshPort(cliHelper.promptInt(cliPrompt.prompt[1][2][2], "22"));
                    saveConfig();
                }
                case 4 -> {
                    usbipConfig.setUsbipHost(cliHelper.promptString(cliPrompt.prompt[1][2][3], null));
                    saveConfig();
                }
                case 5 -> {
                    cliHelper.promptStringArray(cliPrompt.prompt[1][2][4], usbipConfig.getUsbipPorts()::clear, usbipConfig::addUsbipPort);
                    saveConfig();
                }
                default -> {
                    System.out.println("Invalid option. Try again.");
                    cliHelper.pause();
                    continue;
                }
            }
        }
    }
}