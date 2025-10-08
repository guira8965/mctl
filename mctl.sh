#!/bin/bash

# ------------------------------------------------------------
# DEFAULTS
# ------------------------------------------------------------
MOONLIGHT_PATH="/usr/bin/moonlight"
SCREEN_PATH="/usr/bin/screen"

host="192.168.2.2"
app="gui"

# ------------------------------------------------------------
# CONFIGURATION
# ------------------------------------------------------------
# MOONLIGHT CONFIG
moonlight(){
    local resolution="--resolution 1366x768"
    local fps="--fps 60"
    local bitrate="--bitrate 100000" # KBps
    local packet_size="--packet-size 9000" # MTU
    local display_mode="--display-mode fullscreen" # borderless/fullscreen/windowed
    local video_encoder="--video-decoder hardware" # auto/hardware/software
    local video_codec="--video-codec auto" # AV1/H.264/HEVC/auto
    local performance_overlay="--no-performance-overlay" # --performance-overlay/--no-performance-overlay 
    local other_options="--no-vsync --no-frame-pacing --no-game-optimization" 
    local parameters="$resolution $fps $bitrate $packet_size $display_mode $video_encoder $video_codec $performance_overlay $other_options"
    echo "$MOONLIGHT_PATH stream $parameters $host $app" # Usage: moonlight [options] stream <host> "<app>"
}
# SCREEN CONFIG
screen() {
    local process_name="moonlight"
    local arguments="-dmS"
    echo "$SCREEN_PATH $arguments $process_name"
}

# ------------------------------------------------------------
# MAIN FUNCTIONS
# ------------------------------------------------------------
# Start moonlight with screen. It also turns ON the brightness.
start(){
    local brightness="sudo brightnessctl set 100%"
    local run="$(screen) $(moonlight)"
    $run
    $brightness
}
# Stop moonlight properly and forcefully. It also turns OFF the brightness.
stop(){
    local brightness="sudo brightnessctl set 0%"
    local disconnect="$MOONLIGHT_PATH quit $HOST"
    local kill="killall -9 moonlight"
    $brightness
    $disconnect
    $kill
}
# Prompts the user to choose between functions.
prompt=$1
case $prompt in 
    "start" | "START")
        start
        ;;
    "stop" | "STOP")
        stop
        ;;
    *)
        echo "mctl <start|stop>"
        ;;
esac