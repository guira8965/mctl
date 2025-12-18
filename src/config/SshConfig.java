package config;

import java.io.Serializable;

public class SshConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sshUser = "";
    private String sshHost = "";
    private int sshPort;

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

    // Helper method to check if essential fields are empty
    public boolean isEmpty() {
        return (sshUser == null || sshUser.isEmpty()) ||
            (sshHost == null || sshHost.isEmpty()) ||
            sshPort == 0;
    }
}