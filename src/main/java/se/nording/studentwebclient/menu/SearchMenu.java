package se.nording.studentwebclient.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.nording.studentwebclient.client.StudentClient;
import se.nording.studentwebclient.model.Student;
import se.nording.studentwebclient.util.InputValidator;

import java.util.List;

@Component
public class SearchMenu {

    private final StudentClient studentClient;

    @Autowired
    public SearchMenu(StudentClient studentClient) {
        this.studentClient = studentClient;
    }

    public void show() {
        while (true) {
            System.out.println("\n0. Exit");
            System.out.println("1. Search by First Name");
            System.out.println("2. Search by Last Name");
            System.out.println("3. Search by Email");
            int choice = InputValidator.getValidIntegerInput("Enter your choice: ", 0, 3);

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> searchByFirstName();
                case 2 -> searchByLastName();
                case 3 -> searchByEmail();
                default -> System.out.println("Invalid choice Please try again");
            }
        }
    }

    private void searchByFirstName() {
        String firstName = InputValidator.getValidString("Enter first name to search: ");
        try {
            List<Student> students = studentClient.searchByFirstName(firstName);
            if (students != null && !students.isEmpty()) {
                students.forEach(System.out::println);
            } else {
                System.out.println("No students found with first name: " + firstName);
            }
        } catch (Exception e) {
            System.err.println("Error searching students by first name: " + e.getMessage());
        }
    }

    private void searchByLastName() {
        String lastName = InputValidator.getValidString("Enter last name to search: ");
        try {
            List<Student> students = studentClient.searchByLastName(lastName);
            if (students != null && !students.isEmpty()) {
                students.forEach(System.out::println);
            } else {
                System.out.println("No students found with last name: " + lastName);
            }
        } catch (Exception e) {
            System.err.println("Error searching students by last name: " + e.getMessage());
        }
    }

    private void searchByEmail() {
        String email = InputValidator.getValidEmail("Enter email to search: ");
        try {
            List<Student> students = studentClient.searchByEmail(email);
            if (students != null && !students.isEmpty()) {
                students.forEach(System.out::println);
            } else {
                System.out.println("No students found with email: " + email);
            }
        } catch (Exception e) {
            System.err.println("Error searching students by email: " + e.getMessage());
        }
    }
}
