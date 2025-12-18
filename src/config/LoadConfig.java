package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadConfig {

    public static BuildConfig loadOrDefault(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            // File doesn't exist → return new default config
            return new BuildConfig(new MoonlightConfig(), new SshConfig(), new UsbipConfig());
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (BuildConfig) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load config, creating default: " + e.getMessage());
            e.printStackTrace();
            return new BuildConfig(new MoonlightConfig(), new SshConfig(), new UsbipConfig());
        }
    }
}
