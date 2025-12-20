package config;

import java.io.Serializable;

public class BuildConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private MoonlightConfig moonlightConfig;
    private SshConfig sshConfig;
    private UsbipConfig usbipConfig;

    public BuildConfig(MoonlightConfig moonlightConfig, SshConfig sshConfig, UsbipConfig usbipConfig) {
        this.moonlightConfig = moonlightConfig;
        this.sshConfig = sshConfig;
        this.usbipConfig = usbipConfig;
    }

    public MoonlightConfig getMoonlightConfig() {
        return moonlightConfig;
    }

    public SshConfig getSshConfig() {
        return sshConfig;
    }

    public UsbipConfig getUsbipConfig() {
        return usbipConfig;
    }
    
    // Helper method to check if any config is missing
    public boolean isAnyEmpty() {
        return moonlightConfig.isEmpty() || sshConfig.isEmpty() || usbipConfig.isEmpty();
    }
}