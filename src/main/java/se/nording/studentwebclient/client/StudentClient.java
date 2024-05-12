package se.nording.studentwebclient.client;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import se.nording.studentwebclient.model.Student;
import se.nording.studentwebclient.util.WebClientHelper;

import java.util.List;
import java.util.Optional;

@Component
public class StudentClient {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(StudentClient.class);
    private final WebClient webClient;

    @Autowired
    public StudentClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    // Display a student
    public Student getStudentById(Long id) {
        return WebClientHelper.handleRequest(() ->
                webClient
                    .get()
                    .uri("/students/{id}", id)
                    .retrieve()
                    .bodyToMono(Student.class)
                    .block()
        );
    }

    // Display all students
    public List<Student> getAllStudents() {
        Flux<Student> studentFlux = webClient
                .get()
                .uri("/students")
                .retrieve()
                .bodyToFlux(Student.class);

        return studentFlux.collectList().block();
    }

    // Add new student
    public Student createStudent(Student student) {
        return WebClientHelper.handleRequest(() ->
                webClient
                        .post()
                        .uri("/students")
                        .bodyValue(student)
                        .retrieve()
                        .bodyToMono(Student.class)
                        .block()
        );
    }

    // Edit a student
    public Student updateStudent(Long id, Student student) {
        return WebClientHelper.handleRequest(() ->
                webClient
                .put()
                .uri("/students/{id}", id)
                .bodyValue(student)
                .retrieve()
                .bodyToMono(Student.class)
                .block()
        );
    }

    // Remove a student
    public void deleteStudent(Long id) {
        webClient
                .delete()
                .uri("/students/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    // Search students by first name
    public List<Student> searchByFirstName(String firstName) {
        Flux<Student> studentFlux = webClient
                .get()
                .uri("/students/search/byFirstName/{firstName}", firstName)
                .retrieve()
                .bodyToFlux(Student.class);

        return studentFlux.collectList().block();
    }

    // Search students by last name
    public List<Student> searchByLastName(String lastName) {
        Flux<Student> studentFlux = webClient
                .get()
                .uri("/students/search/byLastName/{lastName}", lastName)
                .retrieve()
                .bodyToFlux(Student.class);

        return studentFlux.collectList().block();
    }

    // Search students by email
    public Optional<Student> searchByEmail(String email) {
        return WebClientHelper.handleRequest(() ->
                webClient
                    .get()
                    .uri("/students/search/byEmail/{email}", email)
                    .retrieve()
                    .bodyToMono(Student.class)
                    .blockOptional());
    }
}
