package ui.cli.tasks;

import ui.cli.*;
import config.*;

public class SetupCliMenu {

    String configPath;
    private final CliHelper cliHelper;
    private final CliPrompt cliPrompt;

    public SetupCliMenu (
        String configPath,
        CliHelper cliHelper
    ) {
        this.configPath = configPath;
        this.cliHelper = cliHelper;
        this.cliPrompt = new CliPrompt();
    }

    public void run(BrightnessCtlConfig brightnessCtlConfig, MoonlightConfig moonlightConfig, UsbipConfig usbipConfig) {
        cliHelper.clear();
        
        System.out.println("Welcome to MCTL! (o////o)");
        System.out.println("It seems like your first time here,\nlet's set things up shall we?\n");

        cliHelper.pauseAndClear();

        System.out.println("<<< [1/3] - Initial Setup (Configure brightnessctl) >>>\n");
        brightnessCtlConfig.setSshUser(cliHelper.promptString(cliPrompt.prompt[0][0][0], null));
        brightnessCtlConfig.setSshHost(cliHelper.promptString(cliPrompt.prompt[0][0][1], null));
        brightnessCtlConfig.setSshPort(cliHelper.promptInt(cliPrompt.prompt[0][0][2], "22"));
        brightnessCtlConfig.setBrightnessAmount(cliHelper.promptInt(cliPrompt.prompt[0][0][3], "10"));
        System.out.println();

        cliHelper.pauseAndClear();

        System.out.println("<<< [2/3] - Initial Setup (Configure Moonlight) >>>\n");
        moonlightConfig.setSshUser(cliHelper.promptString(cliPrompt.prompt[0][1][0], null));
        moonlightConfig.setSshHost(cliHelper.promptString(cliPrompt.prompt[0][1][1], null));
        moonlightConfig.setSshPort(cliHelper.promptInt(cliPrompt.prompt[0][1][2], "22"));
        moonlightConfig.setDisplayNumber(cliHelper.promptInt(cliPrompt.prompt[0][1][3], "0"));
        moonlightConfig.setMoonlightPath(cliHelper.promptString(cliPrompt.prompt[0][1][4], "/usr/bin/moonlight"));
        moonlightConfig.setMoonlightHost(cliHelper.promptString(cliPrompt.prompt[0][1][5], null));
        moonlightConfig.setMoonlightApp(cliHelper.promptString(cliPrompt.prompt[0][1][6], "Desktop"));
        moonlightConfig.setResolution(cliHelper.promptString(cliPrompt.prompt[0][1][7], "1280x720"));
        moonlightConfig.setFps(cliHelper.promptInt(cliPrompt.prompt[0][1][8], "60"));
        moonlightConfig.setBitrate(cliHelper.promptInt(cliPrompt.prompt[0][1][9], "10000"));
        moonlightConfig.setPacketSize(cliHelper.promptInt(cliPrompt.prompt[0][1][10], "1500"));
        moonlightConfig.setDisplayMode(cliHelper.promptString(cliPrompt.prompt[0][1][11], "fullscreen"));
        moonlightConfig.setVideoEncoder(cliHelper.promptString(cliPrompt.prompt[0][1][12], "auto"));
        moonlightConfig.setVideoCodec(cliHelper.promptString(cliPrompt.prompt[0][1][13], "auto"));
        moonlightConfig.setPerformanceOverlay(cliHelper.promptBool(cliPrompt.prompt[0][1][14]));
        moonlightConfig.setVsync(cliHelper.promptBool(cliPrompt.prompt[0][1][15]));
        moonlightConfig.setFramePacing(cliHelper.promptBool(cliPrompt.prompt[0][1][16]));
        moonlightConfig.setGameOptimization(cliHelper.promptBool(cliPrompt.prompt[0][1][17]));
        System.out.println();

        cliHelper.pauseAndClear();

        System.out.println("<<< [3/3] - Initial Setup (Configure USB/IP)>>\n");
        usbipConfig.setSshUser(cliHelper.promptString(cliPrompt.prompt[0][2][0], null));
        usbipConfig.setSshHost(cliHelper.promptString(cliPrompt.prompt[0][2][1], null));
        usbipConfig.setSshPort(cliHelper.promptInt(cliPrompt.prompt[0][2][2], "22"));
        usbipConfig.setUsbipHost(cliHelper.promptString(cliPrompt.prompt[0][2][3], null));
        cliHelper.promptStringArray(cliPrompt.prompt[0][2][4], usbipConfig.getUsbipPorts()::clear, usbipConfig::addUsbipPort);
        System.out.println();
        
        cliHelper.pauseAndClear();

        System.out.println("<<< Setup Overview (1/3) >>>\n");
        System.out.println("--- SSH Configuration (brightnessctl) ---");
        System.out.println("SSH User: " + brightnessCtlConfig.getSshUser());
        System.out.println("SSH Host: " + brightnessCtlConfig.getSshHost());
        System.out.println("SSH Port: " + brightnessCtlConfig.getSshPort());
        System.out.println();

        cliHelper.pauseAndClear();

        System.out.println("<<< Setup Overview (2/3) >>>\n");
        System.out.println("--- SSH Configuration (Moonlight) ---");
        System.out.println("SSH User: " + moonlightConfig.getSshUser());
        System.out.println("SSH Host: " + moonlightConfig.getSshHost());
        System.out.println("SSH Port: " + moonlightConfig.getSshPort());
        System.out.println("\n--- Moonlight Configuration ---");
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
        System.out.println("--- SSH Configuration (USB/IP) ---");
        System.out.println("SSH User: " + usbipConfig.getSshUser());
        System.out.println("SSH Host: " + usbipConfig.getSshHost());
        System.out.println("SSH Port: " + usbipConfig.getSshPort());
        System.out.println("\n--- USB/IP Configuration ---");
        System.out.println("USB/IP Host: " + usbipConfig.getUsbipHost());
        System.out.println("USB/IP Bus ID/s: " + usbipConfig.getUsbipPorts());
        System.out.println();

        cliHelper.pauseAndClear();

        System.out.println("Enjoy using MCTL!");
        System.out.println("Author: guira8965 (Github)");
        System.out.println();

        cliHelper.pauseAndClear();
    }
}
