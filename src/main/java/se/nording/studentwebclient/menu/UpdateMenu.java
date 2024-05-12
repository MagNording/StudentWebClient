package se.nording.studentwebclient.menu;

import org.springframework.stereotype.Component;
import se.nording.studentwebclient.client.StudentClient;
import se.nording.studentwebclient.model.Student;

@Component
public class UpdateMenu {

    private final se.nording.studentwebclient.util.validator validator;
    private final StudentClient studentClient;


    public UpdateMenu(se.nording.studentwebclient.util.validator validator, StudentClient studentClient) {
        this.validator = validator;
        this.studentClient = studentClient;
    }

    public void show() {
        Long id = validator.getValidLong("Enter student ID to update: ");
        try {
            Student student = studentClient.getStudentById(id);
            if (student != null) {
                System.out.println("Updating student: " + student);
                showUpdateOptions(id, student);
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error fetching student: " + e.getMessage());
        }
    }


    private void updateStudent(Long id, Student student) {
        try {
            Student updatedStudent = studentClient.updateStudent(id, student);
            System.out.println("Updated: " + updatedStudent);
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    private void showUpdateOptions(Long id, Student student) {
        while (true) {
            System.out.println("\n0. Exit");
            System.out.println("1. Update First Name");
            System.out.println("2. Update Last Name");
            System.out.println("3. Update Email");
            System.out.println("4. Update Phone");
            int choice = validator.getValidIntegerInput("Enter your choice: ", 0, 4);

            switch (choice) {
                case 1 -> student.setFirstName(validator.readString("Enter new first " +
                        "name: "));
                case 2 -> student.setLastName(validator.readString("Enter new last name: "));
                case 3 -> {System.out.println("Enter new email: ");
                    student.setEmail(validator.getValidEmail());}
                case 4 -> student.setPhone(validator.getValidPhone("Enter new phone: "));
                case 0 -> {
                    updateStudent(id, student);
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

