package ui.cli;

public class CliPrompt {
    public String[][][] prompt = {
        { // Setup Cli
            { // brightnessctl
                "Enter SSH User (e.g. user)",
                "Enter SSH Host (e.g. 192.168.0.1)",
                "Enter SSH Port [default: 22]",
                "Enter Brightness Amount (in %)"
            },
            { // Moonlight
                "Enter SSH User (e.g. user)",
                "Enter SSH Host (e.g. 192.168.0.1)",
                "Enter SSH Port [default: 22]",
                "Enter Linux Display Number [default: 0]",
                "Enter Moonlight Path [default: /usr/bin/moonlight]",
                "Enter Moonlight Host (e.g. 192.168.0.1)",
                "Enter Moonlight App [default: Desktop]",
                "Enter Resolution [default: 1280x720]",
                "Enter FPS [default: 60]",
                "Enter Bitrate (in KBps) [default: 10000]",
                "Enter Packet Size (in MTU) [default: 1500]",
                "Enter Display Mode (borderless/fullscreen/windowed) [default: fullscreen]",
                "Enter Video Encoder (auto/hardware/software) [default: auto]",
                "Enter Video Codec (auto/AV1/H.264/HEVC) [default: auto]",
                "Allow performance overlay? (true/false)",
                "Allow VSync? (true/false)",
                "Allow Frame Pacing? (true/false)",
                "Allow Game Optimization? (true/false)"
            },
            { // USB/IP
                "Enter SSH User (e.g. user)",
                "Enter SSH Host (e.g. 192.168.0.1)",
                "Enter SSH Port [default: 22]",
                "Enter USB/IP Host (e.g. 192.168.2.2)",
                "Enter USB/IP Ports (e.g. 0-1, 0-2)"
            }
        },
        { // Configure Cli
            { // SSH
                "Enter SSH User (e.g. user)",
                "Enter SSH Host (e.g. 192.168.0.1)",
                "Enter SSH Port (e.g. 22)",
                "Enter Brightness Amount (in %)"
            },
            { // Moonlight
                "Enter SSH User (e.g. user)",
                "Enter SSH Host (e.g. 192.168.0.1)",
                "Enter SSH Port (e.g. 22)",
                "Enter Linux Display Number (e.g. 0)",
                "Enter Moonlight Path (e.g. /usr/bin/moonlight)",
                "Enter Moonlight Host (e.g. 192.168.0.1)",
                "Enter Moonlight App (e.g. Desktop)",
                "Enter Resolution (e.g. 1280x720)",
                "Enter FPS (e.g. 60)",
                "Enter Bitrate (in KBps) (e.g. 10000)",
                "Enter Packet Size (in MTU) (e.g. 1500)",
                "Enter Display Mode (borderless/fullscreen/windowed)",
                "Enter Video Encoder (auto/hardware/software)",
                "Enter Video Codec (auto/AV1/H.264/HEVC)",
                "Allow performance overlay? (true/false)",
                "Allow VSync? (true/false)",
                "Allow Frame Pacing? (true/false)",
                "Allow Game Optimization? (true/false)"
            },
            { // USB/IP
                "Enter SSH User (e.g. user)",
                "Enter SSH Host (e.g. 192.168.0.1)",
                "Enter SSH Port (e.g. 22)",
                "Enter USB/IP Host (e.g. 192.168.2.2)",
                "Enter USB/IP Ports (e.g. 0-1, 0-2)"
            }
        },
    };
}
