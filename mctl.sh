#!/bin/bash
# author: guira8965 (github)
# PREREQUISITES: x11, openbox + picom, moonlight

# ------------------------------------------------------------
# DEFAULTS
# ------------------------------------------------------------
MOONLIGHT_PATH="/usr/bin/moonlight"
SCREEN_PATH="/usr/bin/screen"
DISPLAY_NUMBER="DISPLAY=:0"

host="192.168.2.2"
app="gui"

# ------------------------------------------------------------
# CONFIGURATION
# ------------------------------------------------------------
# MOONLIGHT CONFIG
exec_moonlight(){
    local resolution="--resolution 1366x768"
    local fps="--fps 60"
    local bitrate="--bitrate 100000" # KBps
    local packet_size="--packet-size 9000" # MTU
    local display_mode="--display-mode fullscreen" # borderless/fullscreen/windowed
    local video_encoder="--video-decoder hardware" # auto/hardware/software
    local video_codec="--video-codec auto" # AV1/H.264/HEVC/auto
    local performance_overlay="--no-performance-overlay" # --performance-overlay/--no-performance-overlay 
    local other_options="--vsync --no-frame-pacing --no-game-optimization" 
    local parameters="$resolution $fps $bitrate $packet_size $display_mode $video_encoder $video_codec $performance_overlay $other_options"
    echo "$MOONLIGHT_PATH stream $parameters $host $app" # Usage: moonlight [options] stream <host> "<app>"
}
# SCREEN CONFIG
exec_screen() {
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
    local run="$(exec_screen) $(exec_moonlight)"
    $brightness
    $run
}
# Stop moonlight properly and forcefully. It also turns OFF the brightness.
stop(){
    local brightness="sudo brightnessctl set 0%"
    local disconnect="$MOONLIGHT_PATH quit $host"
    local kill="killall -9 moonlight"
    $brightness
    $disconnect
    $kill
}
status(){
    screen -ls
}
# Prompts the user to choose between functions.
prompt=$1
case $prompt in 
    "start")
        export $DISPLAY_NUMBER
        start
        ;;
    "stop")
        export $DISPLAY_NUMBER
        stop
        ;;
    "status")
        status
        ;;
    *)
        echo "mctl <start|stop>"
        ;;
esac
