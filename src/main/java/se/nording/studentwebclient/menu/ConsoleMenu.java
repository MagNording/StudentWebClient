package se.nording.studentwebclient.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import se.nording.studentwebclient.client.StudentClient;
import se.nording.studentwebclient.model.Student;
import se.nording.studentwebclient.util.InputValidator;

import java.util.List;

@Component
public class ConsoleMenu {

    private final StudentClient studentClient;
    private final SearchMenu searchMenu;
    private final UpdateMenu updateMenu;

    @Autowired
    public ConsoleMenu(StudentClient studentClient, SearchMenu searchMenu, UpdateMenu updateMenu) {
        this.studentClient = studentClient;
        this.searchMenu = searchMenu;
        this.updateMenu = updateMenu;
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\n0. Exit");
            System.out.println("1. Get Student by ID");
            System.out.println("2. List All Students");
            System.out.println("3. Create Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Search Students");
            int choice = InputValidator.getValidIntegerInput(
                    "Enter your choice: ", 0, 8);


            switch (choice) {
                case 0 -> {
                    System.out.print("Exiting...");
                    return;
                }
                case 1 -> getStudentById();
                case 2 -> listAllStudents();
                case 3 -> createStudent();
                case 4 -> updateMenu.show();
                case 5 -> deleteStudent();
                case 6 -> searchMenu.show();
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void getStudentById() {
        Long id = InputValidator.getValidLong("Enter student ID: ");
        try {
            Student student = studentClient.getStudentById(id);
            if (student != null) {
                System.out.println("Student: " + student);
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void createStudent() {
        String firstName = InputValidator.getValidString("Enter first name: ");
        String lastName = InputValidator.getValidString("Enter last name: ");
        String email = InputValidator.getValidEmail("Enter email: ");
        String phone = InputValidator.getValidPhone("Enter phone number: ");
        Student student = new Student(null, firstName, lastName, email, phone);

        try {
            Student createdStudent = studentClient.createStudent(student);
            if (createdStudent != null) {
                System.out.println("Created: " + createdStudent);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        Long id = InputValidator.getValidLong("Enter student ID: ");
        try {
            studentClient.deleteStudent(id);
            System.out.println("Deleted student with ID: " + id);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void listAllStudents() {
        List<Student> students = studentClient.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(student -> System.out.println("Student: " + student));
        }
    }
}
