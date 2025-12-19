package ui;

import java.util.Scanner;
import config.*;

public class CliConfigureMenu {
    private final Scanner scanner = new Scanner(System.in);

    private final CliHelper cliHelper;
    private final BuildConfig buildConfig;
    private SshConfig sshConfig;
    private MoonlightConfig moonlightConfig;
    private UsbipConfig usbipConfig;

    public CliConfigureMenu (
        CliHelper cliHelper,
        BuildConfig buildConfig,
        SshConfig sshConfig,
        MoonlightConfig moonlightConfig,
        UsbipConfig usbipConfig
    ) {
        this.cliHelper = cliHelper;
        this.buildConfig = buildConfig;
        this.sshConfig = sshConfig;
        this.moonlightConfig = moonlightConfig;
        this.usbipConfig = usbipConfig;
    }
    
    private void saveConfig() {
        try {
            SaveConfig.saveConfig(buildConfig, "config.dat");
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
            System.out.print("Select an option: ");

            String choice = String.valueOf(scanner.next().trim());
            scanner.nextLine();

            switch (choice) {
                case "0" -> {isVisible = !isVisible;}
                case "1" -> configureSsh();
                case "2" -> configureMoonlight();
                case "3" -> configureUsbip();
                default -> {
                    System.out.println("Invalid option. Try again.");
                    break;
                }
            }
        }
    }

    public void setupConfig(SshConfig sshConfig, MoonlightConfig moonlightConfig, UsbipConfig usbipConfig) {
        cliHelper.clear();

        System.out.println("Welcome to MCTL! (o////o)");
        System.out.println("It seems like your first time here,\nlet's set things up shall we?");
        System.out.println("");
        cliHelper.promptEnterKey();
        cliHelper.clear();

        System.out.println("<<< [1/3] - Initial Setup (Configure SSH) >>>");
        System.out.println("");
        System.out.print("Enter SSH User (e.g. user): ");
        sshConfig.setSshUser(scanner.next());
        System.out.print("Enter SSH Host (e.g. 192.168.0.1): ");
        sshConfig.setSshHost(scanner.next());
        System.out.print("Enter SSH Port (e.g. 22): ");
        sshConfig.setSshPort(scanner.nextInt());
        scanner.nextLine(); 
        System.out.println("");
        cliHelper.promptEnterKey();

        cliHelper.clear();

        System.out.println("<<< [2/3] - Initial Setup (Configure Moonlight) >>>");
        System.out.println("");
        System.out.print("Enter Linux Display Number (e.g. 0): ");
        moonlightConfig.setDisplayNumber(scanner.nextInt());
        scanner.nextLine(); 
        System.out.print("Enter Moonlight Path (e.g. /usr/bin/moonlight): ");
        moonlightConfig.setMoonlightPath(scanner.next());
        System.out.print("Enter Moonlight Host (e.g. 192.168.0.1): ");
        moonlightConfig.setMoonlightHost(scanner.next());
        System.out.print("Enter Moonlight App (e.g. app): ");
        moonlightConfig.setMoonlightApp(scanner.next());
        System.out.print("Enter Resolution (e.g. 1920x1080): ");
        moonlightConfig.setResolution(scanner.next());
        System.out.print("Enter FPS (e.g. 60): ");
        moonlightConfig.setFps(scanner.nextInt());
        scanner.nextLine(); 
        System.out.print("Enter Bitrate (e.g. 100000 [in KBps]): ");
        moonlightConfig.setBitrate(scanner.nextInt());
        scanner.nextLine(); 
        System.out.print("Enter Packet Size (e.g. 1492 [in MTU]): ");
        moonlightConfig.setPacketSize(scanner.nextInt());
        scanner.nextLine(); 
        System.out.print("Enter Display Mode (borderless/fullscreen/windowed): ");
        moonlightConfig.setDisplayMode(scanner.next());
        System.out.print("Enter Video Encoder (auto/hardware/software): ");
        moonlightConfig.setVideoEncoder(scanner.next());
        System.out.print("Enter Video Codec (auto/AV1/H.264/HEVC): ");
        moonlightConfig.setVideoCodec(scanner.next());
        scanner.nextLine(); 
        System.out.println("Allow performance overlay? (true/false): ");
        moonlightConfig.setPerformanceOverlay(scanner.nextBoolean());
        scanner.nextLine(); 
        System.out.println("Allow VSync? (true/false): ");
        moonlightConfig.setVsync(scanner.nextBoolean());
        scanner.nextLine(); 
        System.out.println("Allow Frame Pacing? (true/false): ");
        moonlightConfig.setFramePacing(scanner.nextBoolean());
        scanner.nextLine(); 
        System.out.println("Allow Game Optimization? (true/false): ");
        moonlightConfig.setGameOptimization(scanner.nextBoolean());
        scanner.nextLine();
        System.out.println("");
        cliHelper.promptEnterKey();
        
        cliHelper.clear();

        System.out.println("<<< [3/3] - Initial Setup (Configure USB/IP)>>>");
        System.out.println("");
        System.out.print("Enter USB/IP Host (e.g. 192.168.2.2): ");
        usbipConfig.setHost(scanner.next());
        scanner.nextLine(); 
        System.out.print("Enter USB/IP Ports (e.g. 0-1, 0-2): ");
        String portsInput = scanner.nextLine().trim();
        String[] ports = portsInput.split("\\s*,\\s*");
        usbipConfig.getPorts().clear();
        for (String port : ports) {
            usbipConfig.addPort(port);
        }
        System.out.println("");
        cliHelper.promptEnterKey();

        cliHelper.clear();

        System.out.println("<<< Setup Overview (1/3) >>>");
        System.out.println("");
        System.out.println("SSH User: " + sshConfig.getSshUser());
        System.out.println("SSH Host: " + sshConfig.getSshHost());
        System.out.println("SSH Port: " + sshConfig.getSshPort());
        System.out.println("");

        cliHelper.promptEnterKey();
        cliHelper.clear();

        System.out.println("<<< Setup Overview (2/3) >>>");
        System.out.println("");
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
        System.out.println("");

        cliHelper.promptEnterKey();
        cliHelper.clear();
        
        System.out.println("<<< Setup Overview (3/3) >>>");
        System.out.println("");
        System.out.println("USB/IP Host: " + usbipConfig.getHost());
        System.out.println("USB/IP Bus ID/s: " + usbipConfig.getPorts());
        System.out.println("");

        cliHelper.promptEnterKey();
        cliHelper.clear();

        System.out.println("Enjoy using MCTL!");
        System.out.println("author: guira8965 (github)");
        System.out.println("");

        cliHelper.promptEnterKey();
        cliHelper.clear();
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
            System.out.print("Select an option: ");

            String choice = String.valueOf(scanner.next().trim());
            scanner.nextLine();

            switch (choice) {
                case "0" -> {isVisible = !isVisible;}
                case "1" -> {
                    System.out.print("Enter SSH User (e.g. user): ");
                    sshConfig.setSshUser(scanner.next());
                    saveConfig();
                }
                case "2" -> {
                    System.out.print("Enter SSH Host (e.g. 192.168.0.1): ");
                    sshConfig.setSshHost(scanner.next());
                    saveConfig();
                }
                case "3" -> {
                    System.out.print("Enter SSH Port (e.g. 22): ");
                    sshConfig.setSshPort(scanner.nextInt());
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
                    System.out.print("Enter Linux Display Number (e.g. 0): ");
                    moonlightConfig.setDisplayNumber(scanner.nextInt());
                    saveConfig();
                }
                case "2" -> {
                    System.out.print("Enter Moonlight Path (e.g. /usr/bin/moonlight): ");
                    moonlightConfig.setMoonlightPath(scanner.next());
                    saveConfig();
                }
                case "3" -> {
                    System.out.print("Enter Moonlight Host (e.g. 192.168.0.1): ");
                    moonlightConfig.setMoonlightHost(scanner.next());
                    saveConfig();
                }
                case "4" -> {
                    System.out.print("Enter Moonlight App (e.g. app): ");
                    moonlightConfig.setMoonlightApp(scanner.next());
                    saveConfig();
                }
                case "5" -> {
                    System.out.print("Enter Resolution (e.g. 1920x1080): ");
                    moonlightConfig.setResolution(scanner.next());
                    saveConfig();
                }
                case "6" -> {
                    System.out.print("Enter FPS (e.g. 60): ");
                    moonlightConfig.setFps(scanner.nextInt());
                    saveConfig();
                }
                case "7" -> {
                    System.out.print("Enter Bitrate (e.g. 100000 [in KBps]): ");
                    moonlightConfig.setBitrate(scanner.nextInt());
                    saveConfig();
                }
                case "8" -> {
                    System.out.print("Enter Packet Size (e.g. 1492 [in MTU]): ");
                    moonlightConfig.setPacketSize(scanner.nextInt());
                    saveConfig();
                }
                case "9" -> {
                    System.out.print("Enter Display Mode (borderless/fullscreen/windowed): ");
                    moonlightConfig.setDisplayMode(scanner.next());
                    saveConfig();
                }
                case "10" -> {
                    System.out.print("Enter Video Encoder (auto/hardware/software): ");
                    moonlightConfig.setVideoEncoder(scanner.next());
                    saveConfig();
                }
                case "11" -> {
                    System.out.print("Enter Video Codec (auto/AV1/H.264/HEVC): ");
                    moonlightConfig.setVideoCodec(scanner.next());
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
                    System.out.print("Enter USB/IP Host (e.g. 192.168.2.2): ");
                    usbipConfig.setHost(scanner.next());
                    saveConfig();
                }
                case "2" -> {
                    System.out.print("Enter USB/IP Ports (e.g. 0-1, 0-2): ");
                    String portsInput = scanner.nextLine().trim();
                    String[] ports = portsInput.split("\\s*,\\s*");
                    usbipConfig.getPorts().clear();
                    for (String port : ports) {
                        usbipConfig.addPort(port);
                    }
                    saveConfig();
                }
                default -> {
                    System.out.println("Invalid option. Try again.");
                    cliHelper.promptEnterKey();
                    continue;
                }
            }
        }
    }
}