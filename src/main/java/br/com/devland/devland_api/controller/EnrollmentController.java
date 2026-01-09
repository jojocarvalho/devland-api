package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.EnrollmentRequest;
import br.com.devland.devland_api.dto.EnrollmentResponse;
import br.com.devland.devland_api.service.EnrollmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
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

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;

@Tag(name = "Matriculas", description = "Endpoints para gerenciar matriculas de alunos em cursos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    private final Logger logger = getLogger(EnrollmentController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EnrollmentResponse> getAllEnrollments(){
        logger.info("Listing all enrollments");

        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{enrollmentId}")
    @ResponseStatus(HttpStatus.OK)
    public EnrollmentResponse getEnrollmentById(@PathVariable String enrollmentId) {
        logger.info("Getting enrollment by id {}", enrollmentId);

        return enrollmentService.getEnrollmentById(enrollmentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EnrollmentResponse> createEnrollment(@RequestBody EnrollmentRequest enrollmentRequest, UriComponentsBuilder uriBuilder) {
        logger.info("Creating a new enrollment");

        var enrollmentResponse  = enrollmentService.createEnrollment(enrollmentRequest);

        var uri = uriBuilder
                .path("/courses/{id}")
                .buildAndExpand(enrollmentResponse.enrollmentId())
                .toUri();

        return ResponseEntity.created(uri).body(enrollmentResponse);
    }

    @PutMapping("/{enrollmentId}")
    @ResponseStatus(HttpStatus.OK)
    public EnrollmentResponse updateEnrollment(
            @PathVariable String enrollmentId,
            @RequestBody EnrollmentRequest enrollmentRequest
    ) {
        logger.info("Updating enrollment with id {}", enrollmentId);

        return enrollmentService.updateEnrollment(enrollmentId, enrollmentRequest);
    }

    @DeleteMapping("/{enrollmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnrollment(@PathVariable String enrollmentId) {
        logger.info("Deleting enrollment with id {}", enrollmentId);

        enrollmentService.deleteEnrollment(enrollmentId);
    }
}
