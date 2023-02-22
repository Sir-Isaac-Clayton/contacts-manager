package service;

import java.util.Scanner;

public class Input implements AutoCloseable {

    private final Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getString(String... prompt) {
        if( prompt.length > 0)
            System.out.format(prompt[0]);
        return scanner.nextLine();
    }

    public boolean yesNo() {
        return yesNo("%nWould you like to continue? [y/n] ");
    }

    public boolean yesNo(String prompt) {
        System.out.format(prompt);
        String response = getString();
        return "y".toLowerCase().equals(response) ||
                "yes".toLowerCase().equals(response) ||
                "sure".toLowerCase().equals(response) ||
                "ok".toLowerCase().equals(response) ||
                "yeah".toLowerCase().equals(response) ||
                "affirmative".toLowerCase().equals(response);
    }

    public int getInteger(int min, int max) {
        System.out.format("Enter a whole number between %d and %d: ", min, max);
        String nextLine = scanner.nextLine();
        nextLine = nextLine.isEmpty() ? String.valueOf(min-1): nextLine;
        int userInput = Integer.parseInt(nextLine);
        if (userInput < min || userInput > max) {
            System.out.format("Invalid input. Please enter a number between %d and %d.%n", min, max);
            return getInteger(min, max);
        }
        return userInput;
    }

    public double getDouble(String... prompt) {
        String msg;
        if (prompt.length > 0) {
            msg = prompt[0];
        } else {
            msg = "Enter a decimal value: ";
        }
        return getDouble(Double.MIN_VALUE, Double.MAX_VALUE, msg);
    }

    public double getDouble(double min, double max, String... prompt) {
        String output;
        if (prompt.length > 0) {
            output = prompt[0];
        } else {
            output = String.format("Enter a number between %s and %s: ", min, max);
        }
        System.out.format(output);
        double userInput = Double.parseDouble(getString());
        if (userInput < min || userInput > max) {
            System.out.format(output);
            return getDouble(min, max);
        }
        return userInput;
    }

    @Override
    public void close() {
        scanner.close();
    }
}
