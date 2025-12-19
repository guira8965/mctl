package ui;

import java.util.Scanner;

public class CliHelper {
    private final Scanner scanner = new Scanner(System.in);

    public void clear(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void promptEnterKey() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}
