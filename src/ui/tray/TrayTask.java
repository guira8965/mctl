package ui.tray;

import java.awt.MenuItem;

public interface TrayTask {
    String getMenuName();
    MenuItem[] getMenuItems();
}
