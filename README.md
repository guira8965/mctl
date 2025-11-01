# mctl 
mctl is just a personal project. it's a simplified script based on [apollo](https://github.com/ClassicOldSong/Apollo) just for my personal use case.

## Prerequisites
- `brightnessctl`
- `moonlight` (and a apollo host)
- `openbox` + `picom`
- `x11`
- `usbip`

## TODO:
- [x] Basic functionality
- [x] Brightness control
- [x] Create bat script for sunshine host to automate moonlight.
- [x] Migrate to Python
- [x] Fetch environment variables / Create config file
- [x] USB/IP
- [ ] Add config customization via terminal-based GUI
- [ ] Check for "missing prerequisites"
- [ ] Volume control
- [ ] Allow multiple instances of moonlight via apollo ([reference](https://github.com/ClassicOldSong/Apollo/wiki/How-to-start-multiple-instances-of-Apollo))
- [ ] Graphical user interface for phone and windows for easy access

## Directory Tree
```bash
~/
├── .bashrc
├── .bash_profile
├── .xinitrc
├── mctl.py
├── config.yaml
└── .config/
    ├── openbox/
    │   └── autostart
    └── picom/
        └── picom.conf
```
