package config;

import java.io.Serializable;

public class BuildConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private BrightnessCtlConfig brightnessCtlConfig;
    private MoonlightConfig moonlightConfig;
    private UsbipConfig usbipConfig;

    public BuildConfig(BrightnessCtlConfig brightnessCtlConfig, MoonlightConfig moonlightConfig, UsbipConfig usbipConfig) {
        this.brightnessCtlConfig = brightnessCtlConfig;
        this.moonlightConfig = moonlightConfig;
        this.usbipConfig = usbipConfig;
    }

    public BrightnessCtlConfig getBrightnessCtlConfig() {
        return brightnessCtlConfig;
    }

    public MoonlightConfig getMoonlightConfig() {
        return moonlightConfig;
    }

    public UsbipConfig getUsbipConfig() {
        return usbipConfig;
    }
    
    // Helper method to check if any config is missing
    public boolean isAnyEmpty() {
        return moonlightConfig.isEmpty() || usbipConfig.isEmpty();
    }
}