package services;

import java.util.ArrayList;
import java.util.List;

public class BrightnessCtlService {
    private final SshService sshService;

    public BrightnessCtlService (SshService sshService) {
        this.sshService = sshService;
    }
    
    public void off() {
        List<String> cmd = new ArrayList<>();

        cmd.add("sudo brightnessctl set 0%");
        String buildCommand = String.join(" ", cmd);
        sshService.runSshCommand(buildCommand);
    }

    public void on() {
        List<String> cmd = new ArrayList<>();

        cmd.add("sudo brightnessctl set 100%");
        String buildCommand = String.join(" ", cmd);
        sshService.runSshCommand(buildCommand);
    }
}
