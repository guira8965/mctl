package config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsbipConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> ports = new ArrayList<>();
    private String host = "";

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }

    public void addPort(String port) {
        this.ports.add(port);
    }

    public boolean isEmpty() {
        return ports == null || ports.isEmpty() ||
            host == null || host.isEmpty();
    }
}
