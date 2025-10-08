#!/bin/bash

# ------------------------------------------------------------
# PACKAGES
# ------------------------------------------------------------
MOONLIGHT_PATH="/usr/bin/moonlight"
SCREEN_PATH="/usr/bin/screen"

HOST="192.168.2.2"
APP="gui"

# ------------------------------------------------------------
# CONFIGURATION
# ------------------------------------------------------------
# MOONLIGHT CONFIG
moonlight(){
    local RESOLUTION="--resolution 1366x768"
    local FPS="--fps 60"
    local BITRATE="--bitrate 200000" # KBps
    local PACKET_SIZE="--packet-size 9000" # MTU
    local DISPLAY_MODE="--display-mode borderless" # borderless/fullscreen/windowed
    local VIDEO_ENCORDER="--video-decoder hardware" # auto/hardware/software
    local VIDEO_CODEC="--video-codec auto" # AV1/H.264/HEVC/auto
    local PERFORMANCE_OVERLAY="--performance-overlay" # --performance-overlay/--no-performance-overlay 
    local OTHER_OPTIONS=""
    local PARAMETERS="$RESOLUTION $FPS $BITRATE $PACKET_SIZE $DISPLAY_MODE $VIDEO_ENCORDER $VIDEO_CODEC $PERFORMANCE_OVERLAY $OTHER_OPTIONS"
    echo "$MOONLIGHT_PATH stream $PARAMETERS $HOST $APP" # Usage: moonlight [options] stream <host> "<app>"
}
# SCREEN CONFIG
screen() {
    PROCESS_NAME="moonlight"
    ARGUMENTS="-DmS"
    echo "$SCREEN_PATH $ARGUMENTS $PROCESS_NAME"
}

# ------------------------------------------------------------
# MAIN FUNCTIONS
# ------------------------------------------------------------

start(){
    local brightness="sudo brightnessctl set 0%"
    local run="$(screen) $(moonlight)"
    $brightness
    $run
}
stop(){
    local brightness="sudo brightnessctl set 0%"
    local disconnect="moonlight quit $HOST"
    local kill="killall -9 moonlight"
    $brightness
    $disconnect
    $kill
}