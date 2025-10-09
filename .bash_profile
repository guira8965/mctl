#
# ~/.bash_profile
#

# Load ~/.bashrc for aliases and colors
[ -f ~/.bashrc ] && . ~/.bashrc

# Only auto-start X if:
# - Not in SSH
# - No DISPLAY set
# - On TTY1
if [ -z "$SSH_CONNECTION" ] && [ -z "$DISPLAY" ] && [ "$(tty)" = "/dev/tty1" ]; then
    exec startx
fi
