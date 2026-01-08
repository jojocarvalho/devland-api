package br.com.devland.devland_api.service;


import br.com.devland.devland_api.dto.EnrollmentRequest;
import br.com.devland.devland_api.dto.EnrollmentResponse;
import br.com.devland.devland_api.exceptions.EnrollmentNotFoundException;
import br.com.devland.devland_api.exceptions.SchoolClassNotFoundException;
import br.com.devland.devland_api.exceptions.StudentNotFoundException;
import br.com.devland.devland_api.repository.EnrollmentRepository;
import br.com.devland.devland_api.repository.SchoolClassRepository;
import br.com.devland.devland_api.repository.StudentRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;


@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    private final Logger logger = getLogger(EnrollmentService.class);

    public List<EnrollmentResponse> getAllEnrollments() {
        logger.info("Listing all enrollments");

        return enrollmentRepository.findAll()
                .stream()
                .map(EnrollmentResponse::new)
                .toList();
    }

    public EnrollmentResponse getEnrollmentById(String enrollmentId) {
        logger.info("Getting enrollment by id {}", enrollmentId);

        var enrollmentModel = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment was not found"));

        return new EnrollmentResponse(enrollmentModel);
    }

    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
        logger.info("Creating a new enrollment");

        var student = studentRepository.findById(enrollmentRequest.studentId())
                .orElseThrow(() -> new StudentNotFoundException("Student was not found"));

        var schoolClass = schoolClassRepository.findById(enrollmentRequest.schoolClassId())
                .orElseThrow(() -> new SchoolClassNotFoundException("School class was not found"));

        var enrollment = enrollmentRequest.toEntity();

        enrollment.setEnrollmentDate(enrollmentRequest.enrollmentDate());
        enrollment.setStatus(enrollmentRequest.status());
        enrollment.setFinalGrade(enrollmentRequest.finalGrade());
        enrollment.setStudent(student);
        enrollment.setSchoolClass(schoolClass);

        enrollmentRepository.save(enrollment);

        return new EnrollmentResponse(enrollment);
    }

    public EnrollmentResponse updateEnrollment(String enrollmentId, EnrollmentRequest enrollmentRequest) {
        logger.info("Updating enrollment with id {}", enrollmentId);

        var enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment was not found"));

        var student = studentRepository.findById(enrollmentRequest.studentId())
                .orElseThrow(() -> new StudentNotFoundException("Student was not found"));

        var schoolClass = schoolClassRepository.findById(enrollmentRequest.schoolClassId())
                .orElseThrow(() -> new SchoolClassNotFoundException("School class was not found"));

        enrollment.setEnrollmentDate(enrollmentRequest.enrollmentDate());
        enrollment.setStatus(enrollmentRequest.status());
        enrollment.setFinalGrade(enrollmentRequest.finalGrade());
        enrollment.setStudent(student);
        enrollment.setSchoolClass(schoolClass);

        enrollmentRepository.save(enrollment);

        return new EnrollmentResponse(enrollment);
    }

    public void deleteEnrollment(String enrollmentId) {
        logger.info("Deleting enrollment with id {}", enrollmentId);

        var enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment was not found"));

        enrollmentRepository.delete(enrollment);
    }
}
