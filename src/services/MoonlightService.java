package services;

import config.MoonlightConfig;

import java.util.ArrayList;
import java.util.List;

public class MoonlightService {
    // Moonlight parameters
    private final SshService sshService;
    private final MoonlightConfig moonlightConfig;

    public MoonlightService(
        SshService sshService,
        MoonlightConfig moonlightConfig
    ) {
        this.sshService = sshService;
        this.moonlightConfig = moonlightConfig;
    }

    private void ssh(String command) {
        sshService.runSshCommand(command, moonlightConfig.getSshUser(), moonlightConfig.getSshHost(), moonlightConfig.getSshPort());
    }

    // Start Moonlight streaming on the remote host
    public void startStreaming(){
        List<String> cmd = new ArrayList<>();

        cmd.add("export DISPLAY=:" + moonlightConfig.getDisplayNumber() + ";");
        cmd.add("nohup");
        cmd.add(moonlightConfig.getMoonlightPath());
        cmd.add("stream");
        cmd.add("--resolution " + moonlightConfig.getResolution());
        cmd.add("--fps " + String.valueOf(moonlightConfig.getFps()));
        cmd.add("--bitrate " + String.valueOf(moonlightConfig.getBitrate()));
        cmd.add("--packet-size " + String.valueOf(moonlightConfig.getPacketSize()));
        cmd.add("--display-mode " + moonlightConfig.getDisplayMode());
        cmd.add("--video-decoder " + moonlightConfig.getVideoEncoder());
        cmd.add("--video-codec " + moonlightConfig.getVideoCodec());
        
        cmd.add(moonlightConfig.isPerformanceOverlay() ? "--performance-overlay" : "--no-performance-overlay");
        cmd.add(moonlightConfig.isVsync() ? "--vsync" : "--no-vsync");
        cmd.add(moonlightConfig.isFramePacing() ? "--frame-pacing" : "--no-frame-pacing");
        cmd.add(moonlightConfig.isGameOptimization() ? "--game-optimization" : "--no-game-optimization");

        cmd.add(moonlightConfig.getMoonlightHost());
        cmd.add(moonlightConfig.getMoonlightApp());

        cmd.add("> moonlight.log 2>&1 &");

        String buildCommand = String.join(" ", cmd);

        System.out.println("Starting Moonlight...");
        ssh(buildCommand);
    }
    
    public void stopStreaming(){
        List<String> cmd = new ArrayList<>();
        cmd.add("export DISPLAY=:" + moonlightConfig.getDisplayNumber() + ";");
        cmd.add("nohup");

        cmd.add(moonlightConfig.getMoonlightPath() + " quit " + moonlightConfig.getMoonlightHost());
        
        cmd.add("> moonlight.log 2>&1 &");

        String buildCommand = String.join(" ", cmd);

        System.out.println("Stopping Moonlight...");
        ssh(buildCommand);
        ssh("killall moonlight");
    }
}
