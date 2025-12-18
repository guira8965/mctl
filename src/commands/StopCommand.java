package commands;

import services.MoonlightService;
import services.UsbipService;

public class StopCommand {
    private final UsbipService usbipService;
    private final MoonlightService moonlightService;
    
    public StopCommand(
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
                moonlightService.stopStreaming();
                break;
            case 2:
                moonlightService.stopStreaming();
                break;
            case 3:
                usbipService.detachAll();
                break;
            default:
                System.out.println("Invalid state: " + state);
                break;
        }
    }
}