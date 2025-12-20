package ui;

import java.util.Scanner;
import config.*;

public class CliConfigureMenu {
    private final Scanner scanner = new Scanner(System.in);

    String configPath;
    private final BuildConfig buildConfig;
    private final CliHelper cliHelper;
    private SshConfig sshConfig;
    private MoonlightConfig moonlightConfig;
    private UsbipConfig usbipConfig;

    public CliConfigureMenu (
        String configPath,
        BuildConfig buildConfig,
        CliHelper cliHelper,
        SshConfig sshConfig,
        MoonlightConfig moonlightConfig,
        UsbipConfig usbipConfig
    ) {
        this.configPath = configPath;
        this.buildConfig = buildConfig;
        this.cliHelper = cliHelper;
        this.sshConfig = sshConfig;
        this.moonlightConfig = moonlightConfig;
        this.usbipConfig = usbipConfig;
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
            System.out.println("[1] Configure SSH");
            System.out.println("[2] Configure Moonlight");
            System.out.println("[3] Configure USB/IP");
            System.out.println("[0] Back");

            switch (cliHelper.promptIntInRange("Select an option", 0, 3)) {
                case 0 -> {isVisible = !isVisible;}
                case 1 -> configureSsh();
                case 2 -> configureMoonlight();
                case 3 -> configureUsbip();
                default -> {
                    System.out.println("Invalid option. Try again.");
                    break;
                }
            }
        }
    }

    // All of the prompts, since they are a LOT.
    private String[][] prompt = {
        { // SSH
            "Enter SSH User (e.g. user)",
            "Enter SSH Host (e.g. 192.168.0.1)",
            "Enter SSH Port [default: 22]"
        },
        { // Moonlight
            "Enter Linux Display Number [default: 0]",
            "Enter Moonlight Path [default: /usr/bin/moonlight]",
            "Enter Moonlight Host (e.g. 192.168.0.1)",
            "Enter Moonlight App [default: Desktop]",
            "Enter Resolution [default: 1280x720]",
            "Enter FPS [default: 60]",
            "Enter Bitrate (in KBps) [default: 10000]",
            "Enter Packet Size (in MTU) (e.g. 1500)",
            "Enter Display Mode (borderless/fullscreen/windowed) [default: fullscreen]",
            "Enter Video Encoder (auto/hardware/software) [default: auto]",
            "Enter Video Codec (auto/AV1/H.264/HEVC) [default: auto]",
            "Allow performance overlay? (true/false)",
            "Allow VSync? (true/false)",
            "Allow Frame Pacing? (true/false)",
            "Allow Game Optimization? (true/false)"
        },
        { // USB/IP
            "Enter USB/IP Host (e.g. 192.168.2.2)",
            "Enter USB/IP Ports (e.g. 0-1, 0-2)"
        }
    };

    public void setupConfig(SshConfig sshConfig, MoonlightConfig moonlightConfig, UsbipConfig usbipConfig) {
        cliHelper.clear();
        
        System.out.println("Welcome to MCTL! (o////o)");
        System.out.println("It seems like your first time here,\nlet's set things up shall we?\n");

        cliHelper.pauseAndClear();

        System.out.println("<<< [1/3] - Initial Setup (Configure SSH) >>>\n");
        sshConfig.setSshUser(cliHelper.promptString(prompt[0][0], null));
        sshConfig.setSshHost(cliHelper.promptString(prompt[0][1], null));
        sshConfig.setSshPort(cliHelper.promptInt(prompt[0][2], "22"));
        System.out.println();

        cliHelper.pauseAndClear();

        System.out.println("<<< [2/3] - Initial Setup (Configure Moonlight) >>>\n");
        moonlightConfig.setDisplayNumber(cliHelper.promptInt(prompt[1][0], "0"));
        moonlightConfig.setMoonlightPath(cliHelper.promptString(prompt[1][1], "/usr/bin/moonlight"));
        moonlightConfig.setMoonlightHost(cliHelper.promptString(prompt[1][2], null));
        moonlightConfig.setMoonlightApp(cliHelper.promptString(prompt[1][3], "Desktop"));
        moonlightConfig.setResolution(cliHelper.promptString(prompt[1][4], "1280x720"));
        moonlightConfig.setFps(cliHelper.promptInt(prompt[1][5], "60"));
        moonlightConfig.setBitrate(cliHelper.promptInt(prompt[1][6], "10000"));
        moonlightConfig.setPacketSize(cliHelper.promptInt(prompt[1][7], "1500"));
        moonlightConfig.setDisplayMode(cliHelper.promptString(prompt[1][8], "fullscreen"));
        moonlightConfig.setVideoEncoder(cliHelper.promptString(prompt[1][9], "auto"));
        moonlightConfig.setVideoCodec(cliHelper.promptString(prompt[1][10], "auto"));
        moonlightConfig.setPerformanceOverlay(cliHelper.promptBool(prompt[1][11]));
        moonlightConfig.setVsync(cliHelper.promptBool(prompt[1][12]));
        moonlightConfig.setFramePacing(cliHelper.promptBool(prompt[1][13]));
        moonlightConfig.setGameOptimization(cliHelper.promptBool(prompt[1][14]));
        System.out.println();

        cliHelper.pauseAndClear();

        System.out.println("<<< [3/3] - Initial Setup (Configure USB/IP)>>\n");
        usbipConfig.setHost(cliHelper.promptString(prompt[2][0], null));
        cliHelper.promptStringArray(prompt[2][1], usbipConfig.getPorts()::clear, usbipConfig::addPort);
        System.out.println();
        
        cliHelper.pauseAndClear();

        System.out.println("<<< Setup Overview (1/3) >>>\n");
        System.out.println("SSH User: " + sshConfig.getSshUser());
        System.out.println("SSH Host: " + sshConfig.getSshHost());
        System.out.println("SSH Port: " + sshConfig.getSshPort());
        System.out.println();

        cliHelper.pauseAndClear();

        System.out.println("<<< Setup Overview (2/3) >>>\n");
        System.out.println("Display Number: " + moonlightConfig.getDisplayNumber());
        System.out.println("Moonlight Path: " + moonlightConfig.getMoonlightPath());
        System.out.println("Moonlight Host: " + moonlightConfig.getMoonlightHost());
        System.out.println("Moonlight App: " + moonlightConfig.getMoonlightApp());
        System.out.println("Resolution: " + moonlightConfig.getResolution());
        System.out.println("FPS: " + moonlightConfig.getFps());
        System.out.println("Bitrate: " + moonlightConfig.getBitrate());
        System.out.println("Packet Size: " + moonlightConfig.getPacketSize());
        System.out.println("Display Mode: " + moonlightConfig.getDisplayMode());
        System.out.println("Video Encoder: " + moonlightConfig.getVideoEncoder());
        System.out.println("Video Codec: " + moonlightConfig.getVideoCodec());
        System.out.println("Performance Overlay: " + moonlightConfig.isPerformanceOverlay());
        System.out.println("VSync: " + moonlightConfig.isVsync());
        System.out.println("Frame Pacing: " + moonlightConfig.isFramePacing());
        System.out.println("Game Optimization: " + moonlightConfig.isGameOptimization());
        System.out.println();

        cliHelper.pauseAndClear();
        
        System.out.println("<<< Setup Overview (3/3) >>>\n");
        System.out.println("USB/IP Host: " + usbipConfig.getHost());
        System.out.println("USB/IP Bus ID/s: " + usbipConfig.getPorts());
        System.out.println();

        cliHelper.pauseAndClear();

        System.out.println("Enjoy using MCTL!");
        System.out.println("Author: guira8965 (Github)");
        System.out.println();

        cliHelper.pauseAndClear();
    }

    private void configureSsh() {
        boolean isVisible = true;
        while (isVisible) {
            cliHelper.clear();
            System.out.println("<-- mctl/configure/ssh -->");
            System.out.println("[1] SSH User (" + sshConfig.getSshUser() + ")");
            System.out.println("[2] SSH Host (" + sshConfig.getSshHost() + ")");
            System.out.println("[3] SSH Port (" + sshConfig.getSshPort() + ")");
            System.out.println("[0] Back");

            switch (cliHelper.promptIntInRange("Select an option: ", 0, 3)) {
                case 0 -> {isVisible = !isVisible;}
                case 1 -> {
                    sshConfig.setSshUser(cliHelper.promptString(prompt[0][0], null));
                    saveConfig();
                }
                case 2 -> {
                    sshConfig.setSshHost(cliHelper.promptString(prompt[0][1], null));
                    saveConfig();
                }
                case 3 -> {
                    sshConfig.setSshPort(cliHelper.promptInt(prompt[0][2], "22"));
                    saveConfig();
                }
                default -> {
                    System.out.println("Invalid option. Try again.");
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
            System.out.println("[1] Display Number (" + moonlightConfig.getDisplayNumber() + ")");
            System.out.println("[2] Moonlight Path (" + moonlightConfig.getMoonlightPath() + ")");
            System.out.println("[3] Moonlight Host (" + moonlightConfig.getMoonlightHost() + ")");
            System.out.println("[4] Moonlight App (" + moonlightConfig.getMoonlightApp() + ")");
            System.out.println("[5] Resolution (" + moonlightConfig.getResolution() + ")");
            System.out.println("[6] FPS (" + moonlightConfig.getFps() + ")");
            System.out.println("[7] Bitrate (" + moonlightConfig.getBitrate() + ")");
            System.out.println("[8] Packet Size (" + moonlightConfig.getPacketSize() + ")");
            System.out.println("[9] Display Mode (" + moonlightConfig.getDisplayMode() + ")");
            System.out.println("[10] Video Encoder (" + moonlightConfig.getVideoEncoder() + ")");
            System.out.println("[11] Video Codec (" + moonlightConfig.getVideoCodec() + ")");
            System.out.println("[12] Performance Overlay (" + moonlightConfig.isPerformanceOverlay() + ")");
            System.out.println("[13] VSync (" + moonlightConfig.isVsync() + ")");
            System.out.println("[14] Frame Pacing (" + moonlightConfig.isFramePacing() + ")");
            System.out.println("[15] Game Optimization (" + moonlightConfig.isGameOptimization() + ")");
            System.out.println("[0] Back");
            System.out.print("Select an option: ");

            String choice = String.valueOf(scanner.next().trim());
            scanner.nextLine();

            switch (choice) {
                case "0" -> {isVisible = !isVisible;}
                case "1" -> {
                    moonlightConfig.setDisplayNumber(cliHelper.promptInt(prompt[1][0], "0"));
                    saveConfig();
                }
                case "2" -> {
                    moonlightConfig.setMoonlightPath(cliHelper.promptString(prompt[1][1], "/usr/bin/moonlight"));
                    saveConfig();
                }
                case "3" -> {
                    moonlightConfig.setMoonlightHost(cliHelper.promptString(prompt[1][2], null));
                    saveConfig();
                }
                case "4" -> {
                    moonlightConfig.setMoonlightApp(cliHelper.promptString(prompt[1][3], "Desktop"));
                    saveConfig();
                }
                case "5" -> {
                    moonlightConfig.setResolution(cliHelper.promptString(prompt[1][4], "1280x720"));
                    saveConfig();
                }
                case "6" -> {
                    moonlightConfig.setFps(cliHelper.promptInt(prompt[1][5], "60"));
                    saveConfig();
                }
                case "7" -> {
                    moonlightConfig.setBitrate(cliHelper.promptInt(prompt[1][6], "10000"));
                    saveConfig();
                }
                case "8" -> {
                    moonlightConfig.setPacketSize(cliHelper.promptInt(prompt[1][7], "1500"));
                    saveConfig();
                }
                case "9" -> {
                    moonlightConfig.setDisplayMode(cliHelper.promptString(prompt[1][8], "fullscreen"));
                    saveConfig();
                }
                case "10" -> {
                    moonlightConfig.setVideoEncoder(cliHelper.promptString(prompt[1][9], "auto"));
                    saveConfig();
                }
                case "11" -> {
                    moonlightConfig.setVideoCodec(cliHelper.promptString(prompt[1][10], "auto"));
                    saveConfig();
                }
                case "12" -> {
                    boolean flag = moonlightConfig.isPerformanceOverlay();
                    moonlightConfig.setPerformanceOverlay(!flag);
                    saveConfig();
                }
                case "13" -> {
                    boolean flag = moonlightConfig.isVsync();
                    moonlightConfig.setVsync(!flag);
                    saveConfig();
                }
                case "14" -> {
                    boolean flag = moonlightConfig.isFramePacing();
                    moonlightConfig.setFramePacing(!flag);
                    saveConfig();
                }
                case "15" -> {
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
            System.out.println("[1] USB/IP Host (" + usbipConfig.getHost() + ")");
            System.out.println("[2] USB/IP Bus ID/s (" + usbipConfig.getPorts() + ")");
            System.out.println("[0] Exit");
            System.out.print("Select an option: ");

            String choice = String.valueOf(scanner.next().trim());
            scanner.nextLine();

            switch (choice) {
                case "0" -> {isVisible = !isVisible;}
                case "1" -> {
                    usbipConfig.setHost(cliHelper.promptString(prompt[2][0], null));
                    saveConfig();
                }
                case "2" -> {
                    cliHelper.promptStringArray(prompt[2][1], usbipConfig.getPorts()::clear, usbipConfig::addPort);
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