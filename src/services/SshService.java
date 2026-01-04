package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SshService {
    // SSH parameters
    String sshUser;
    String sshHost;
    int sshPort;

    public SshService(
        String sshUser,
        String sshHost,
        int sshPort
    ) {
        this.sshUser = sshUser;
        this.sshHost = sshHost;
        this.sshPort = sshPort;
    }
    
    // Builds SSH command by storing it within an arraylist.
    private List<String> buildSshCommand(String command, String sshUser, String sshHost, int sshPort) {
        List<String> cmd = new ArrayList<>();
        cmd.add("ssh");
        cmd.add(sshUser + "@" + sshHost);
        cmd.add("-p");
        cmd.add((String.valueOf(sshPort)));
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
    public void runSshCommand(String command, String sshUser, String sshHost, int sshPort) {
        List<String> cmd = buildSshCommand(command, sshUser, sshHost, sshPort);
        executeSshCommand(cmd);
    }
}
