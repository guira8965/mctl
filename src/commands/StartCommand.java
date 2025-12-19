package commands;

import services.MoonlightService;
import services.UsbipService;
import services.BrightnessCtlService;

public class StartCommand {
    private final UsbipService usbipService;
    private final MoonlightService moonlightService;
    private final BrightnessCtlService brightnessCtlService;
    
    public StartCommand(
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
                usbipService.attachAll();
                moonlightService.startStreaming();
                brightnessCtlService.on();
                break;
            case 2:
                moonlightService.startStreaming();
                brightnessCtlService.on();
                break;
            case 3:
                usbipService.detachAll();
                usbipService.attachAll();
                break;
            default:
                return;
        }
    }
}