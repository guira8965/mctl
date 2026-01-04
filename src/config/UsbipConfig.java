package config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsbipConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sshUser = "";
    private String sshHost = "";
    private int sshPort;

    private List<String> usbipPorts = new ArrayList<>();
    private String usbipHost = "";

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

    public String getUsbipHost() {
        return usbipHost;
    }
    public void setUsbipHost(String usbipHost) {
        this.usbipHost = usbipHost;
    }

    public List<String> getUsbipPorts() {
        return usbipPorts;
    }

    public void setUsbipPorts(List<String> usbipPorts) {
        this.usbipPorts = usbipPorts;
    }

    public void addUsbipPort(String port) {
        this.usbipPorts.add(port);
    }

    public boolean isEmpty() {
        return usbipPorts == null || usbipPorts.isEmpty() ||
            usbipHost == null || usbipHost.isEmpty();
    }
}
