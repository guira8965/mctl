# mctl 
mctl is just a personal project. it's a simplified script based on [apollo](https://github.com/ClassicOldSong/Apollo) just for my personal use case.

## Prerequisites
- `brightnessctl`
- `moonlight` (and a apollo host)
- `usbip` (optional)

## TODO:
- [x] Basic functionality
- [x] Brightness control
- [x] Create bat script for sunshine host to automate moonlight.
- [x] Migrate to Python
- [x] Fetch environment variables / Create config file
- [x] USB/IP
- [x] Add config customization via terminal-based GUI
- [ ] Apply architecture pattern to organize code
- [ ] FUNCTION: Check for "missing prerequisites"
- [ ] WINDOWS ONLY: Allow multiple instances of moonlight via apollo ([reference](https://github.com/ClassicOldSong/Apollo/wiki/How-to-start-multiple-instances-of-Apollo))

## Directory Tree
```bash
~/
└── mctl/
    ├── mctl.py
    ├── config.yaml
```