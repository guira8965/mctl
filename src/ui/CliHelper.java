package ui;

import java.util.Scanner;
import java.util.function.Consumer;

public class CliHelper {
    private final Scanner scanner = new Scanner(System.in);

    public void clear(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void pause() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    public void pauseAndClear() {
        pause();
        clear();
    }

    public void notifyInvalidOption() {
        System.out.println("Invalid option. Try again.");
        pause();
    }

    public String promptString(String prompt, String defaultValue) {
        while (true) {
            System.out.print(prompt.strip() + ": ");
            String input = scanner.nextLine().strip();

            if (input.isBlank()) {
                if (defaultValue != null) {
                    return defaultValue;
                } else {
                    System.out.println("Invalid input. Try again");
                    continue;
                }
            }

            return input;
        }
    }

    public boolean promptBool(String prompt) {
        while (true) {
            System.out.print(prompt.strip() + ": ");
            String input = scanner.nextLine().strip().toLowerCase();
            
            switch (input) {
                case "true" -> {return true;}
                case "false" -> {return false;}
                default -> {
                    if (input.isBlank()) continue;
                    System.out.println("Invalid input. Enter true or false.");
                }
            }
        }
    }

    public int promptInt(String prompt, String defaultValue) {
        while (true) {
            System.out.print(prompt.strip() + ": ");
            String input = scanner.nextLine().strip();

            if (input.isBlank()) {
                if (defaultValue != null) {
                    return Integer.parseInt(defaultValue);
                } else {
                    continue;
                }
            }

            try {
                int value = Integer.valueOf(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public int promptIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt.strip() + ": ");
            String input = scanner.nextLine().strip();

            if (input.isBlank()) continue;

            try {
                int value = Integer.valueOf(input);

                if (value < min || value > max) {
                    System.out.println("Invalid option.");
                    System.out.println("Please input a number between " + min + " and " + max);
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public String[] promptStringArray(String prompt, Runnable clearFunction, Consumer<String> addFunction) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine().strip();
        
        String[] array = input.split("\\s*,\\s*");
        
        clearFunction.run();

        for (String a : array) {
            addFunction.accept(a);
        }
        
        return array;
    }

    public boolean promptExit() {        
        while (true) {
            System.out.print("Are you sure you want to exit? (Y/n): ");
            String input = scanner.nextLine().strip().toLowerCase();

            switch (input) {
                case "", "y"-> {return false;}
                case "n"  -> {return true;}
                default -> {System.out.println("Invalid input. Enter Y or N.");}
            }
        }
    }
}
