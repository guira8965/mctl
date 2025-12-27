# mctl
## Prerequisites
- `moonlight` (and a apollo host)
- `brightnessctl` (optional)
- `usbip` (optional)

## Directory Tree
```bash
~/src/
├── Main.java
├── commands/
│   ├── StartCommand.java
│   └── StopCommand.java
├── config/
│   ├── BuildConfig.java
│   ├── LoadConfig.java
│   ├── MoonlightConfig.java
│   ├── SaveConfig.java
│   ├── SshConfig.java
│   └── UsbipConfig.java
├── services/
│   ├── BrightnessCtlService.java
│   ├── MoonlightService.java
│   ├── SshService.java
│   └── UsbipService.java
└── ui/
    ├── cli/
    │   └── tasks
    │       ├── CliConfigureMenu.java
    │       ├── CliHelper.java
    │       ├── CliMainMenu.java
    │       ├── CliStartMenu.java
    │       └── CliStopMenu.java
    └── tray/
        ├── tasks/
        │   ├── AllTrayMeny.java
        │   ├── MoonlightTrayMeny.java
        │   └── UsbipTrayMenu.java
        ├── TrayApp.java
        └── TrayTask.java
```