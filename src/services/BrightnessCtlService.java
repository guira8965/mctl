package services;

import java.util.ArrayList;
import java.util.List;

import config.BrightnessCtlConfig;

public class BrightnessCtlService {
    private final SshService sshService;
    private final BrightnessCtlConfig brightnessCtlConfig;

    public BrightnessCtlService (SshService sshService, BrightnessCtlConfig brightnessCtlConfig) {
        this.sshService = sshService;
        this.brightnessCtlConfig = brightnessCtlConfig;
    }
    
    private void ssh(String command) {
        sshService.runSshCommand(command, brightnessCtlConfig.getSshUser(), brightnessCtlConfig.getSshHost(), brightnessCtlConfig.getSshPort());
    }
    
    public void off() {
        List<String> cmd = new ArrayList<>();

        cmd.add("brightnessctl set 0%");
        String buildCommand = String.join(" ", cmd);
        ssh(buildCommand);
    }

    public void set() {
        List<String> cmd = new ArrayList<>();

        cmd.add("brightnessctl set " + brightnessCtlConfig.getBrightnessAmount() + "%");
        String buildCommand = String.join(" ", cmd);
        ssh(buildCommand);
    }
}
