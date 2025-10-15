# mctl 
mctl is a simplified script for [moonlight](https://github.com/moonlight-stream/moonlight-qt).

## Prerequisites
- `brightnessctl`
- `moonlight` (and a sunshine host)
- `openbox` + `picom`
- `x11`

## Features
- [x] Basic functionality
- [x] Brightness control
- [x] Create bat script for sunshine host to automate moonlight.
- [ ] Volume control
- [ ] Graphical user interface for phone and windows for easy access
- [ ] Fetch environment variables / Create config file
- [ ] Migrate to C++
- [ ] [rip](https://github.com/ClassicOldSong/Apollo/wiki/How-to-start-multiple-instances-of-Apollo)

## Directory Tree
```bash
~/
├── .bashrc
├── .bash_profile
├── .xinitrc
├── mctl.sh
└── .config/
    ├── openbox/
    │   └── autostart
    └── picom/
        └── picom.conf
```
