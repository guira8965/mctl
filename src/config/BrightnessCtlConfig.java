package config;

import java.io.Serializable;

public class BrightnessCtlConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sshUser = "";
    private String sshHost = "";
    private int sshPort;
    
    private int brightnessAmount;

    public String getSshUser() {
        return sshUser;
    }
    public void setSshUser(String sshUser) {
        this.sshUser = sshUser;
    }

    public String getSshHost() {
        return sshHost;
    }
    public void setSshHost(String sshHost) {
        this.sshHost = sshHost;
    }

    public int getSshPort() {
        return sshPort;
    }
    public void setSshPort(int sshPort) {
        this.sshPort = sshPort;
    }

    // Getters and Setters
    public int getBrightnessAmount() {
        return brightnessAmount;
    }
    public void setBrightnessAmount(int brightnessAmount) {
        this.brightnessAmount = brightnessAmount;
    }
}