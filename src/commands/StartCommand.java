package commands;

import services.MoonlightService;
import services.UsbipService;

public class StartCommand {
    private final UsbipService usbipService;
    private final MoonlightService moonlightService;
    
    public StartCommand(
        UsbipService usbipService,
        MoonlightService moonlightService
    ) {
        this.usbipService = usbipService;
        this.moonlightService = moonlightService;
    }

    public void run(int state){
        switch (state) {
            case 1:
                usbipService.detachAll();
                usbipService.attachAll();
                moonlightService.startStreaming();
                break;
            case 2:
                moonlightService.startStreaming();
                break;
            case 3:
                usbipService.detachAll();
                usbipService.attachAll();
                break;
            default:
                System.out.println("Invalid state: " + state);
                break;
        }
    }
}