package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.FinalGradeRequest;
import br.com.devland.devland_api.dto.FinalGradeResponse;
import br.com.devland.devland_api.enums.StudentEvaluation;
import br.com.devland.devland_api.model.FinalGradeModel;
import br.com.devland.devland_api.repository.CourseRepository;
import br.com.devland.devland_api.repository.FinalGradeRepository;
import br.com.devland.devland_api.repository.StudentRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class FinalGradeService {

    @Autowired
    private FinalGradeRepository finalGradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    private final Logger logger = getLogger(FinalGradeService.class);


    public List<FinalGradeResponse> getAllFinalGrades() {
        logger.info("Listing all final grades");

        return finalGradeRepository.findAll()
                .stream()
                .map(FinalGradeResponse::new)
                .toList();
    }

    public FinalGradeResponse getFinalGradeById(String finalGradeId) {
        logger.info("Getting final grade by id {}", finalGradeId);

        var finalGradeModel = finalGradeRepository.findById(finalGradeId)
                .orElseThrow(() -> new RuntimeException("Final grade was not found"));

        return new FinalGradeResponse(finalGradeModel);
    }

    public FinalGradeResponse createFinalGrade(FinalGradeRequest finalGradeRequest) {
        logger.info("Creating final grade {}", finalGradeRequest);

        var student = studentRepository.findById(finalGradeRequest.studentId())
                .orElseThrow(() -> new RuntimeException("Student was not found"));

        var course = courseRepository.findById(finalGradeRequest.courseId())
                .orElseThrow(() -> new RuntimeException("Course was not found"));

        FinalGradeModel finalGradeModel = finalGradeRequest.toEntity();

        finalGradeModel.setStudent(student);
        finalGradeModel.setCourse(course);
        finalGradeModel.setFinalAverage(0);
        finalGradeModel.setStatus(StudentEvaluation.IN_PROGRESS);
        finalGradeModel.setUpdatedAt(LocalDateTime.now());
        var savedFinalGrade = finalGradeRepository.save(finalGradeRequest.toEntity());

        return new FinalGradeResponse(savedFinalGrade);
    }

    public FinalGradeResponse updateFinalGrade(String finalGradeId, FinalGradeRequest finalGradeRequest){
        logger.info("Updating final grade {}", finalGradeId);

        FinalGradeModel existingFinalGrade = finalGradeRepository.findById(finalGradeId)
                .orElseThrow(() -> new RuntimeException("Final grade was not found"));

        existingFinalGrade.setFinalAverage(finalGradeRequest.finalAverage());
        existingFinalGrade.setStatus(finalGradeRequest.status());
        existingFinalGrade.setUpdatedAt(LocalDateTime.now());

        FinalGradeModel updateFinalGrade = finalGradeRepository.save(existingFinalGrade);

        return new FinalGradeResponse(updateFinalGrade);
    }


    public void deleteFinalGrade(String finalGradeId) {
        logger.info("Deleting final grade {}", finalGradeId);

        FinalGradeModel existingFinalGrade = finalGradeRepository.findById(finalGradeId)
                .orElseThrow(() -> new RuntimeException("Final grade was not found"));

        finalGradeRepository.delete(existingFinalGrade);
    }


}
