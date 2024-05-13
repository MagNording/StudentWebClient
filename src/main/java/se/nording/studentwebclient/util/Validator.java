package se.nording.studentwebclient.util;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Validator implements Validationable {

    private final Scanner scanner;

    public Validator() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getValidString() {
        String userInput;
        boolean isUserInputInvalid;
        do {
            userInput = scanner.nextLine();
            if (!userInput.matches("[-a-zA-ZåäöÅÄÖ0-9@._ ]+")) {
                System.out.println("Incorrect format, you cannot use special characters!");
                isUserInputInvalid = true;
            } else if (userInput.isEmpty()) {
                System.out.println("Entry cannot be blank..");
                isUserInputInvalid = true;
            } else {
                isUserInputInvalid = false;
            }
        } while (isUserInputInvalid);

        return userInput;
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        String userInput;
        boolean isUserInputInvalid;
        do {
            userInput = scanner.nextLine();
            if (!userInput.matches("[-a-zA-ZåäöÅÄÖ0-9@._ ]+")) {
                System.out.println("Incorrect format, you cannot use special characters!");
                isUserInputInvalid = true;
            } else if (userInput.isEmpty()) {
                System.out.println("Entry cannot be blank.");
                isUserInputInvalid = true;
            } else {
                isUserInputInvalid = false;
            }
        } while (isUserInputInvalid);

        return userInput;
    }


    @Override
    public String getAnyString() {
        return scanner.nextLine();
    }

    public Long getValidLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    public int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    // Validate Integer input within a specific range (minValue and maxValue inclusive)
    public int getValidIntegerInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Invalid input! Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public String getValidEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String email = scanner.nextLine().trim();
            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            } else {
                System.out.println("Invalid email. Please try again.");
            }
        }
    }

    public String getValidPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String phone = scanner.nextLine().trim();
            if (phone.matches("^\\+?[0-9\\-\\s]+$")) {
                return phone;
            } else {
                System.out.println("Invalid phone number. Please try again.");
            }
        }
    }
}
