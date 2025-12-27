package services;

import config.SshConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SshService {
    // SSH parameters
    private SshConfig sshConfig;

    public SshService(
        SshConfig sshConfig
    ) {
        this.sshConfig = sshConfig;
    }
    
    // Builds SSH command by storing it within an arraylist.
    private List<String> buildSshCommand(String command) {
        List<String> cmd = new ArrayList<>();
        cmd.add("ssh");
        cmd.add(sshConfig.getSshUser() + "@" + sshConfig.getSshHost());
        cmd.add("-p");
        cmd.add((String.valueOf(sshConfig.getSshPort())));
        cmd.add(command);
        return cmd;
    }
    
    // Handles output and errors
    private void executeSshCommand(List<String> command) {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        pb.inheritIO();

        try {
            Process process = pb.start();

            // Read and print output
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("SSH command exited with code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error executing SSH command: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * MAIN: Helper function for running remove SSH.
     * Combines buildSshCommand() and executeSshCommand()
     */
    public void runSshCommand(String command) {
        List<String> cmd = buildSshCommand(command);
        executeSshCommand(cmd);
    }
}
