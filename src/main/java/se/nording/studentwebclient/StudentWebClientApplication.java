package se.nording.studentwebclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.nording.studentwebclient.menu.ConsoleMenu;

@SpringBootApplication
public class StudentWebClientApplication implements CommandLineRunner {

    private final ConsoleMenu consoleMenu;

    @Autowired
    public StudentWebClientApplication(ConsoleMenu consoleMenu) {
        this.consoleMenu = consoleMenu;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentWebClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        consoleMenu.mainMenu();
    }
}
