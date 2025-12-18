package config;

import java.io.Serializable;

public class MoonlightConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private int displayNumber;
    private String moonlightPath = "";
    private String moonlightHost = "";
    private String moonlightApp = "";
    private String resolution = "";
    private int fps;
    private int bitrate;
    private int packetSize;
    private String displayMode = "";
    private String videoEncoder = "";
    private String videoCodec = "";
    private boolean performanceOverlay;
    private boolean vsync;
    private boolean framePacing;
    private boolean gameOptimization;

    // Getters and Setters
    public int getDisplayNumber() {
        return displayNumber;
    }
    public void setDisplayNumber(int displayNumber) {
        this.displayNumber = displayNumber;
    }

    public String getMoonlightPath() {
        return moonlightPath;
    }
    public void setMoonlightPath(String moonlightPath) {
        this.moonlightPath = moonlightPath;
    }

    public String getMoonlightHost() {
        return moonlightHost;
    }
    public void setMoonlightHost(String moonlightHost) {
        this.moonlightHost = moonlightHost;
    }

    public String getMoonlightApp() {
        return moonlightApp;
    }
    public void setMoonlightApp(String moonlightApp) {
        this.moonlightApp = moonlightApp;
    }

    public String getResolution() {
        return resolution;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getFps() {
        return fps;
    }
    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getBitrate() {
        return bitrate;
    }
    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getPacketSize() {
        return packetSize;
    }
    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    public String getDisplayMode() {
        return displayMode;
    }
    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
    }

    public String getVideoEncoder() {
        return videoEncoder;
    }
    public void setVideoEncoder(String videoEncoder) {
        this.videoEncoder = videoEncoder;
    }

    public String getVideoCodec() {
        return videoCodec;
    }
    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public boolean isPerformanceOverlay() {
        return performanceOverlay;
    }
    public void setPerformanceOverlay(boolean performanceOverlay) {
        this.performanceOverlay = performanceOverlay;
    }

    public boolean isVsync() {
        return vsync;
    }
    public void setVsync(boolean vsync) {
        this.vsync = vsync;
    }

    public boolean isFramePacing() {
        return framePacing;
    }
    public void setFramePacing(boolean framePacing) {
        this.framePacing = framePacing;
    }

    public boolean isGameOptimization() {
        return gameOptimization;
    }
    public void setGameOptimization(boolean gameOptimization) {
        this.gameOptimization = gameOptimization;
    }

    // Helper method to check if essential fields are empty
    public boolean isEmpty() {
        return (moonlightPath == null || moonlightPath.isEmpty()) ||
            (moonlightHost == null || moonlightHost.isEmpty()) ||
            (moonlightApp == null || moonlightApp.isEmpty());
    }
}