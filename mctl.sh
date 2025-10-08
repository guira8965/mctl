#!/bin/bash

MOONLIGHT="/usr/bin/moonlight"
SCREEN="/usr/bin/screen"

# ------------------------------------------------------------
# CONFIGURATION
# ------------------------------------------------------------

# MOONLIGHT CONFIG
moonlight(){
    local IP_ADDRESS="192.168.2.2"
    local RESOLUTION="1366x768"
    local BIRATE="600000" # KBps
}

# SCREEN CONFIG
screen(){
    local PROCESS_NAME="moonlight"
    local ARGUMENTS="-dmS"
    
}



# ------------------------------------------------------------
# MAIN FUNCTIONS
# ------------------------------------------------------------
start(){
    $SCREEN $MOONLIGHT 
}

stop(){

}