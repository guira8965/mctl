package services;

import config.UsbipConfig;

import java.io.IOException;

public class UsbipService {
    private final SshService sshService;
    private UsbipConfig usbipConfig;

    public UsbipService(
        SshService sshService,
        UsbipConfig usbipConfig
    ) {
        this.sshService = sshService;
        this.usbipConfig = usbipConfig;
    }

    private void ssh(String command) {
        sshService.runSshCommand(command, usbipConfig.getSshUser(), usbipConfig.getSshHost(), usbipConfig.getSshPort());
    }

    private void attach(String b){
        try {
            // Bind on remote host
            ssh("usbip bind -b " + b);

            // Attach locally
            System.out.println("Attaching USB/IP bus " + b + " from " + usbipConfig.getUsbipHost() + "...");
            ProcessBuilder pb = new ProcessBuilder(
                "usbip", "attach", "-r", usbipConfig.getUsbipHost(), "-b", b
            );
            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.print("USB/IP attach failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("USB/IP attach failed with exit code: " + b + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void detach(String b){
        try {
            // Unbind on remote host
            ssh("usbip unbind -b " + b);

            // Detach locally
            ProcessBuilder pb = new ProcessBuilder("usbip", "detach", "-a");
            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.print("USB/IP detach failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.print("Error detaching USB/IP: " + e);
            e.printStackTrace();
        }
    }

    public void attachAll(){
        for (String i : usbipConfig.getUsbipPorts()) {
            attach(i);
        }
    }

    public void detachAll(){
        for (String i : usbipConfig.getUsbipPorts()) {
            detach(i);
        }
    }
}