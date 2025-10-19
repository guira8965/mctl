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
- [ ] Fetch environment variables / Create config file
- [ ] Allow multiple instances of moonlight via apollo ([reference](https://github.com/ClassicOldSong/Apollo/wiki/How-to-start-multiple-instances-of-Apollo))
- [ ] Migrate to C++
- [ ] Graphical user interface for phone and windows for easy access

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
