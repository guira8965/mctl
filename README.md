# mctl
## Prerequisites
- `moonlight` (and a apollo host)
- `brightnessctl` (optional)
- `usbip` (optional)

## TODO:
- [x] Basic functionality
- [x] Brightness control
- [x] Create bat script for sunshine host to automate moonlight.
- [x] Migrate to Python
- [x] Fetch environment variables / Create config file
- [x] USB/IP
- [x] Add config customization via terminal-based GUI
- [x] Apply architecture pattern to organize code
- [x] Migrated to Java whilst at the architecture pattern stage

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