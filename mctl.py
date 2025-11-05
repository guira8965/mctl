import sys, subprocess
from ruamel.yaml import YAML
# author: guira8965 (github)

# ------------------------------------------------------------
# Load Config & Global Variables
# ------------------------------------------------------------
CONFIG_PATH = "config.yaml"

yaml = YAML()
yaml.preserve_quotes = True

def read_config():    
    try: 
        with open(CONFIG_PATH, "r", encoding="utf-8") as file:
            return yaml.load(file)
    except Exception as e:
        sys.exit(f"Error reading {CONFIG_PATH}: {e}")
config = read_config()

def write_config(input):
    try:
        with open(CONFIG_PATH, "w", encoding="utf-8") as file:
            return yaml.dump(input, file)
    except Exception as e:
        sys.exit(f"Error writing to {CONFIG_PATH}: {e}")

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

if config["moonlight"]["performance_overlay"]:
    performance_overlay = "--performance-overlay"
else:
    performance_overlay = "--no-performance-overlay"

if config["moonlight"]["vsync"]:
    vsync = "--vsync"
else:
    vsync = "--no-vsync"

if config["moonlight"]["frame_pacing"]:
    frame_pacing = "--frame-pacing"
else:
    frame_pacing = "--no-frame-pacing"

if config["moonlight"]["game_optimization"]:
    game_optimization = "--game-optimization"
else:
    game_optimization = "--no-game-optimization"

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
def modify(type, entity, attribute, prompt):
    if type == "default":
        config[f'{entity}'][f'{attribute}'] = input(f"{prompt} ").strip()
        write_config(config)
    elif type == "array":
        while True:
            try:
                amount = int(input("Enter number of elements: ").strip())
                break
            except ValueError :
                print("Invalid input. Please enter a number.")
        new_elements = []
        for i in range(amount):
            element = input(f"Enter element #{i+1}: ").strip()
            new_elements.append(element)
        config[f'{entity}'][f'{attribute}'] = new_elements
        write_config(config)
    elif type == "conditional":
        if config[f'{entity}'][f'{attribute}'] == True:
            config[f'{entity}'][f'{attribute}'] = False
        else:
            config[f'{entity}'][f'{attribute}'] = True
        write_config(config)
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
        f"{vsync} {frame_pacing} {game_optimization} {performance_overlay} {moonlight_host} {moonlight_app}"
    )
def start_usbip():
    print(f"Starting USB/IP on {usbip_host}")
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
    ssh(f"systemctl restart usbip-bind@1bcf:2b90.service")
def start():
    clear()
    print(f"--- mctl/start ---")
    print(f"[1] Start All")
    print(f"[2] Start Moonlight")
    print(f"[3] Start USB/IP")
    print(f"[0] Exit")
    choice = input(f"Select an option: ").strip()
    match choice:
        case "1":
            start_usbip()
            start_moonlight()
            input(f"Press Enter to return...")
            start()
        case "2":
            start_moonlight()
            input(f"Press Enter to return...")
            start()
        case "3":
            start_usbip()
            input(f"Press Enter to return...")
            start()
        case "0":
            menu()
        case _:
            start()
def stop():
    clear()
    print(f"--- mctl/stop ---")
    print(f"[1] Stop All")
    print(f"[2] Stop Moonlight")
    print(f"[3] Stop USB/IP")
    print(f"[0] Exit")
    choice = input(f"Select an option: ").strip()
    match choice:
        case "1":
            stop_usbip()
            stop_moonlight()
            input(f"Press Enter to return...")
            stop()
        case "2":
            stop_moonlight()
            input(f"Press Enter to return...")
            stop()
        case "3":
            stop_usbip()
            input(f"Press Enter to return...")
            stop()
        case "0":
            menu()
        case _:
            stop()
def configure():
    while True:
        clear()
        print(f"--- mctl/configure ---")
        print(f"[1] Windows Settings")
        print(f"[2] Moonlight Settings")
        print(f"[3] Linux Settings")
        print(f"[0] Exit")
        choice = input(f"Select an option: ").strip()
        match choice:
            case "1":
                while True:
                    clear()
                    print(f"--- mctl/configure/windows ---")
                    print(f"[1] SSH Host ({config['windows']['ssh_host']})")
                    print(f"[2] SSH Port ({config['windows']['ssh_port']})")
                    print(f"[3] USB/IP Host ({config['windows']['usbip_host']})")
                    print(f"[4] USB/IP Bus ID ({config['windows']['usbip_bus_id']})")
                    print(f"[0] Exit")
                    choice = input(f"Select an option: ").strip()
                    match choice:
                        case "1":
                            modify("default", "windows", "ssh_host", "Enter SSH Host (e.g. user@192.168.0.1):")
                        case "2":
                            modify("default", "windows", "ssh_port", "Enter SSH Port (e.g. 22): ")
                        case "3":
                            modify("default", "windows", "usbip_host", "Enter USB/IP Host (e.g. 192.168.0.1):")
                        case "4":
                            modify("array", "windows", "usbip_bus_id", "Enter number of USB/IP Bus IDs: (e.g. 2-5):")
                        case "0":
                            configure()
                        case _:
                            continue
            case "2":
                while True:
                    clear()
                    print(f"--- mctl/configure/moonlight ---")
                    print(f"[1] Moonlight Host ({config['moonlight']['moonlight_host']})")
                    print(f"[2] Moonlight App ({config['moonlight']['moonlight_app']})")
                    print(f"[3] Resolution ({config['moonlight']['resolution']})")
                    print(f"[4] FPS ({config['moonlight']['fps']})")
                    print(f"[5] Bitrate ({config['moonlight']['bitrate']})")
                    print(f"[6] Packet Size ({config['moonlight']['packet_size']})")
                    print(f"[7] Display Mode ({config['moonlight']['display_mode']})")
                    print(f"[8] Video Encoder ({config['moonlight']['video_encoder']})")
                    print(f"[9] Video Codec ({config['moonlight']['video_codec']})")
                    print(f"[10] Performance Overlay ({config['moonlight']['performance_overlay']})")
                    print(f"[11] VSync ({config['moonlight']['vsync']})")
                    print(f"[12] Frame Pacing ({config['moonlight']['frame_pacing']})")
                    print(f"[13] Game Optimization ({config['moonlight']['game_optimization']})")
                    print(f"[0] Exit")
                    choice = input(f"Select an option: ").strip()
                    match choice:
                        case "1":
                            modify("default", "moonlight", "moonlight_host", "Enter Moonlight Host (e.g. 192.168.0.1):")
                        case "2":
                            modify("default", "moonlight", "moonlight_app", "Enter Moonlight App (e.g. 22):")
                        case "3":
                            modify("default", "moonlight", "resolution", "Enter Resolution (e.g. 1920x1080):")
                        case "4":
                            modify("default", "moonlight", "fps", "Enter FPS (e.g. 60):")
                        case "5":
                            modify("default", "moonlight", "bitrate", "Enter Bitrate (e.g. 100000 [in KBps]):")
                        case "6":
                            modify("default", "moonlight", "packet_size", "Enter Packet Size (e.g. 1492 [in MTU]):")
                        case "7":
                            modify("default", "moonlight", "display_mode", "Enter Display Mode (borderless/fullscreen/windowed):")
                        case "8":
                            modify("default", "moonlight", "video_encoder", "Enter Video Encoder (auto/hardware/software):")
                        case "9":
                            modify("default", "moonlight", "video_coder", "Enter Video Codec (auto/AV1/H.264/HEVC):")
                        case "10":
                            modify("conditional", "moonlight", "performance_overlay", None)
                        case "11":
                            modify("conditional", "moonlight", "vsync", None)
                        case "12":
                            modify("conditional", "moonlight", "frame_pacing", None)
                        case "13":
                            modify("conditional", "moonlight", "game_optimization", None)
                        case "0":
                            configure()
                        case _:
                            continue
            case "3":
                while True:
                    clear()
                    print(f"--- mctl/configure/linux ---")
                    print(f"[1] Moonlight Path ({config['linux']['MOONLIGHT_PATH']})")
                    print(f"[2] Screen Path ({config['linux']['SCREEN_PATH']})")
                    print(f"[3] Display Number ({config['linux']['DISPLAY_NUMBER']})")
                    print(f"[0] Exit")
                    choice = input(f"Select an option: ").strip()
                    match choice:
                        case "1":
                            modify("default", "linux", "MOONLIGHT_PATH", "Enter Moonlight Path (e.g. /usr/bin/moonlight):")
                        case "2":
                            modify("default", "linux", "SCREEN_PATH", "Enter Screen Path (e.g. 22):")
                        case "3":
                            modify("default", "linux", "DISPLAY_NUMBER", "Enter Display Number (e.g. 0):")
                        case "0":
                            configure()
                        case _:
                            continue
            case "0":
                menu()
            case _:
                continue
def status():
    ssh(f"screen -ls")
    input(f"Press Enter to return...")
    menu()
def menu():
    while True:
        clear()
        print(f"--- mctl ---")
        print(f"[1] Start")
        print(f"[2] Stop")
        print(f"[3] Configure")
        print(f"[4] Status")
        print(f"[0] Exit")
        choice = input(f"Select an option: ").strip()
        match choice:
            case "1":
                start()
            case "2":
                stop()
            case "3":
                configure()
            case "4":
                status()
            case "0":
                while True:
                    choice = input(f"Are you sure you want to exit? (Y/n): ").strip().lower() or "y"
                    if choice == "n":
                        menu()
                    elif choice == "y":
                        print("Exiting program...")
                        sys.exit(0)
                    else:
                        print("Invalid input. Please try again.")
            case _:
                continue

# ------------------------------------------------------------
# Entry function
# ------------------------------------------------------------
if __name__ == "__main__":
    try:
        configure()
    except KeyboardInterrupt:
        clear()
        print("Exiting mctl.")
        sys.exit(0)