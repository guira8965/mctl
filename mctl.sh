#!/bin/bash

MOONLIGHT="/usr/bin/moonlight"
SCREEN="/usr/bin/screen"

# ------------------------------------------------------------
# CONFIGURATION
# ------------------------------------------------------------
# MOONLIGHT CONFIG
moonlight (){
    local IP_ADDRESS="192.168.2.2"
    local RESOLUTION="1366x768"
    local BITRATE="600000" # KBps
    echo $MOONLIGHT $IP_ADDRESS $RESOLUTION $BITRATE 
}

# SCREEN CONFIG
screen () {
    PROCESS_NAME="moonlight"
    ARGUMENTS="-dmS"
    echo $ARGUMENTS $PROCESS_NAME  
}

# ------------------------------------------------------------
# MAIN FUNCTIONS
# ------------------------------------------------------------

start(){
    local run=$(moonlight) $(screen)
    run
}

start