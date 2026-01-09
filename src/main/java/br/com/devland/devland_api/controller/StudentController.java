package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.StudentRequest;
import br.com.devland.devland_api.dto.StudentResponse;
import br.com.devland.devland_api.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Tag(name = "Alunos", description = "Endpoints para gerenciar alunos")
@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    private final Logger logger = getLogger(StudentController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getAllStudents() {
        logger.info("Listing all students");
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse getStudentById(@PathVariable String studentId) {
        logger.info("Getting student by id {}", studentId);
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest, UriComponentsBuilder uriBuilder) {
        logger.info("Creating a new student");

        var studentResponse = studentService.createStudent(studentRequest);

        var uri = uriBuilder
                .path("/students/{id}")
                .buildAndExpand(studentResponse.studentId())
                .toUri();

        return ResponseEntity.created(uri).body(studentResponse);
    }

    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse updateStudent(
            @PathVariable String studentId,
            @RequestBody StudentRequest studentRequest
    ) {
        logger.info("Updating student with id {}", studentId);
        return studentService.updateStudent(studentId, studentRequest);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable String studentId) {
        logger.info("Deleting student with id {}", studentId);
        studentService.deleteStudent(studentId);
    }

}
