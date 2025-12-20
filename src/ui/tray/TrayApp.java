package ui.tray;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.List;

public class TrayApp {
    private final List<TrayTask> tasks;

    public TrayApp (List<TrayTask> tasks) {
        this.tasks = tasks;
    }

    public void run(String imagePath) {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported!");
            return;
        }
        
        try {
            Image image = ImageIO.read(new File(imagePath));

            PopupMenu mainMenu = new PopupMenu();

            for (TrayTask task : tasks) {
            Menu menu = new Menu(task.getMenuName());
            for (MenuItem item : task.getMenuItems()) {
                menu.add(item);
            }
                mainMenu.add(menu);
            }

            MenuItem exit = new MenuItem("Exit");
            exit.addActionListener(e -> System.exit(0));

            mainMenu.add(exit);

            TrayIcon trayIcon = new TrayIcon(image, "MCTL", mainMenu);
            trayIcon.setImageAutoSize(true);

            SystemTray.getSystemTray().add(trayIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}