import yaml, sys, subprocess
# author: guira8965 (github)

# ------------------------------------------------------------
# Load Config
# ------------------------------------------------------------
CONFIG_PATH = "config.yaml"
def load_config():
    with open(CONFIG_PATH, 'r') as file:
        return yaml.safe_load(file)
try: 
    config = load_config()
except FileNotFoundError:
    sys.exit("Error: config.yaml not found.")

ssh_host            = config["windows"]["ssh_host"]
ssh_port            = config["windows"]["ssh_port"]
usbip_host          = config["windows"]["usbip_host"]
usbip_bus_id        = config["windows"]["usbip_bus_id"]
MOONLIGHT_PATH      = config["linux"]["MOONLIGHT_PATH"]
SCREEN_PATH         = config["linux"]["SCREEN_PATH"]
DISPLAY_NUMBER      = config["linux"]["DISPLAY_NUMBER"]
moonlight_host      = config["moonlight"]["moonlight_host"]
moonlight_app       = config["moonlight"]["moonlight_app"]
resolution          = config["moonlight"]["resolution"]
fps                 = config["moonlight"]["fps"]
bitrate             = config["moonlight"]["bitrate"]
packet_size         = config["moonlight"]["packet_size"]
display_mode        = config["moonlight"]["display_mode"]
video_encoder       = config["moonlight"]["video_encoder"]
video_codec         = config["moonlight"]["video_codec"]
performance_overlay = config["moonlight"]["performance_overlay"]
other_options       = config["moonlight"]["other_options"]

# ------------------------------------------------------------
# Helper functions
# ------------------------------------------------------------
def run(cmd):
    subprocess.run(cmd, shell=True, check=False)
def clear():
    subprocess.run("cls", shell=True)
def ssh(cmd):
    run(f"ssh {ssh_host} -p {ssh_port} {cmd}")
def screen(name, cmd):
    ssh(f"{SCREEN_PATH} -dmS {name} bash -c {cmd}")
def moonlight(name, cmd):
    screen(name, f"'export DISPLAY=:{DISPLAY_NUMBER}; {MOONLIGHT_PATH} {cmd}'")

# ------------------------------------------------------------
# Main Functions
# ------------------------------------------------------------
def start_moonlight():
    print(f"Starting Moonlight on {ssh_host} -p {ssh_port}")
    ssh(f"sudo brightnessctl set 100%")
    moonlight(
        "start_moonlight",
        f"stream --resolution {resolution} --fps {fps} --bitrate {bitrate} --packet-size {packet_size} "
        f"--display-mode {display_mode} --video-decoder {video_encoder} --video-codec {video_codec} "
        f"{other_options} {performance_overlay} {moonlight_host} {moonlight_app}"
    )
def start_usbip():
    print(f"Starting Webcam on {usbip_host}")
    run("usbip detach -a")
    for x in usbip_bus_id:
        run(f"usbip attach -r {usbip_host} -b {x}")
def stop_moonlight():
    print(f"Stopping Moonlight on {ssh_host} -p {ssh_port}")
    ssh(f"sudo brightnessctl set 0%")
    moonlight(
        "stop_moonlight",
        f"quit {moonlight_host}"
    )
    ssh(f"killall -9 moonlight")
def stop_usbip():
    print(f"Stopping USB/IP on {usbip_host}")
    run(f"usbip detach -a")
def start():
    clear()
    print(f"--- mctl ---")
    print(f"[A] Start All")
    print(f"[1] Start Moonlight")
    print(f"[2] Start USB/IP")
    print(f"[X] Exit")
    choice = input(f"Select an option: ").strip().lower()
    match choice:
        case "a":
            start_usbip()
            start_moonlight()
            input(f"Press Enter to return...")
            start()
        case "1":
            start_moonlight()
            input(f"Press Enter to return...")
            start()
        case "2":
            start_usbip()
            input(f"Press Enter to return...")
            start()
        case "x":
            menu()
        case _:
            start()
def stop():
    clear()
    print(f"--- mctl ---")
    print (f"[A] Stop All")
    print (f"[1] Stop Moonlight")
    print (f"[2] Stop USB/IP")
    print (f"[X] Exit")
    choice = input(f"Select an option: ").strip().lower()
    match choice:
        case "a":
            stop_usbip()
            stop_moonlight()
            input(f"Press Enter to return...")
            stop()
        case "1":
            stop_moonlight()
            input(f"Press Enter to return...")
            stop()
        case "2":
            stop_usbip()
            input(f"Press Enter to return...")
            stop()
        case "x":
            menu()
        case _:
            stop()
def status():
    ssh(f"screen -ls")
    input(f"Press Enter to return...")
    menu()
def menu():
    clear()
    print(f"--- mctl ---")
    print(f"[1] Start")
    print(f"[2] Stop")
    print(f"[3] Status")
    print(f"[X] Exit")
    choice = input(f"Select an option: ").strip().lower()
    match choice:
        case "1":
            start()
        case "2":
            stop()
        case "3":
            status()
        case "x":
            choice = input(f"Are you sure you want to exit? (y/n): ").strip().lower()
            if choice == "n":
                menu()
            else:
                sys.exit(0)
        case _:
            menu()
# ------------------------------------------------------------
# Entry function
# ------------------------------------------------------------
if __name__ == "__main__":
    try:
        menu()
    except KeyboardInterrupt:
        clear()
        print("Exiting mctl.")
        sys.exit(0)