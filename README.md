# mctl 
mctl is just a personal project. it's a simplified script based on [apollo](https://github.com/ClassicOldSong/Apollo) just for my personal use case.

## Prerequisites
- `brightnessctl`
- `moonlight` (and a apollo host)
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
