package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.StudentRequest;
import br.com.devland.devland_api.dto.StudentResponse;
import br.com.devland.devland_api.exceptions.GuardianNotFoundException;
import br.com.devland.devland_api.exceptions.StudentNotFoundException;
import br.com.devland.devland_api.model.GuardianModel;
import br.com.devland.devland_api.model.StudentModel;
import br.com.devland.devland_api.repository.GuardianRepository;
import br.com.devland.devland_api.repository.StudentRepository;
import br.com.devland.devland_api.utils.RegMatGenerator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private RegMatGenerator regMatGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = getLogger(StudentService.class.getName());

    public List<StudentResponse> getAllStudents() {
        logger.info("Listing all students");

        List<StudentModel> students = studentRepository.findAll();

        return students
                .stream()
                .map(StudentResponse::new)
                .toList();
    }

    public StudentResponse getStudentById(String studentId) {
        logger.info("Getting student by id " + studentId);

        StudentModel studentModel = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        return new StudentResponse(studentModel);
    }



    public StudentResponse createStudent(StudentRequest studentRequest) {
        logger.info("Creating a new student");

        GuardianModel guardian = guardianRepository.findByCpf(studentRequest.guardianCpf())
                .orElseThrow(() -> new GuardianNotFoundException("Guardian with CPF " + studentRequest.guardianCpf() + " was not found"));

        StudentModel student = studentRequest.toEntity();

        String regmat = regMatGenerator.generateRegMat();

        student.setGuardian(guardian);
        student.setRegmat(regmat);
        student.setPassword(passwordEncoder.encode(regmat));

        studentRepository.save(student);

        return new StudentResponse(student);
    }

    public StudentResponse updateStudent(String studentId, StudentRequest studentRequest) {
        logger.info("Updating student with id " + studentId);

        StudentModel existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        existingStudent.setName(studentRequest.name());
        existingStudent.setEmail(studentRequest.email());
        existingStudent.setBirthDate(studentRequest.birthDate());
        existingStudent.setPassword(passwordEncoder.encode(studentRequest.password()));

        studentRepository.save(existingStudent);

        return new StudentResponse(existingStudent);
    }

    public void deleteStudent(String studentId) {
        logger.info("Deleting student with id " + studentId);

        studentRepository.deleteById(studentId);
    }


}
