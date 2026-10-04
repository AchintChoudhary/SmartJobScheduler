package com.scheduler.util;

import java.util.Scanner;

/*
 * Centralized input handling.
 * The application does not create Scanner objects everywhere.
 */
public final class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    private InputHelper() {
    }

    public static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static int readPositiveInt(String message) {
        while (true) {
            int value = readInt(message);

            if (value > 0) {
                return value;
            }

            System.out.println("Value must be greater than 0.");
        }
    }

    public static int readPriority(String message) {
        while (true) {
            int priority = readInt(message);

            if (priority >= 1 && priority <= 5) {
                return priority;
            }

            System.out.println("Priority must be between 1 and 5.");
        }
    }

    public static String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    public static void close() {
        scanner.close();
    }
}
