package commands;

import services.MoonlightService;
import services.UsbipService;
import services.BrightnessCtlService;

public class StopCommand {
    private final UsbipService usbipService;
    private final MoonlightService moonlightService;
    private final BrightnessCtlService brightnessCtlService;
    
    public StopCommand(
        UsbipService usbipService,
        MoonlightService moonlightService,
        BrightnessCtlService brightnessCtlService
    ) {
        this.usbipService = usbipService;
        this.moonlightService = moonlightService;
        this.brightnessCtlService = brightnessCtlService;
    }

    public void run(int state){
        switch (state) {
            case 1:
                usbipService.detachAll();
                moonlightService.stopStreaming();
                brightnessCtlService.off();
                break;
            case 2:
                moonlightService.stopStreaming();
                brightnessCtlService.off();
                break;
            case 3:
                usbipService.detachAll();
                break;
            default:
                return;
        }
    }
}