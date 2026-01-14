package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.PartialGradesRequest;
import br.com.devland.devland_api.dto.PartialGradesResponse;
import br.com.devland.devland_api.model.PartialGradesModel;
import br.com.devland.devland_api.repository.FinalGradeRepository;
import br.com.devland.devland_api.repository.PartialGradesRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;

@Service
public class PartialGradesService {

    @Autowired
    private PartialGradesRepository partialGradesRepository;

    @Autowired
    private FinalGradeRepository finalGradeRepository;

    private final Logger logger = getLogger(PartialGradesService.class);


    public List<PartialGradesResponse> getAllPartialGrades() {
        logger.info("Listing all partial grades");

        return partialGradesRepository.findAll()
                .stream()
                .map(PartialGradesResponse::new)
                .toList();
    }

    public List<PartialGradesResponse> getAllPartialGradesByFinalGradeId(String finalGradeId) {
        logger.info("Listing all partial grades for final grade id {}", finalGradeId);

        return partialGradesRepository.findAllByFinalGrade_FinalGradeId(finalGradeId)
                .stream()
                .map(PartialGradesResponse::new)
                .toList();
    }


    public PartialGradesResponse getPartialGradeById(String partialGradeId) {
        logger.info("Getting partial grade by id {}", partialGradeId);

        var partialGradesModel = partialGradesRepository.findById(partialGradeId)
                .orElseThrow(() -> new RuntimeException("Partial grade was not found"));

        return new PartialGradesResponse(partialGradesModel);
    }

    public PartialGradesResponse createPartialGrade(PartialGradesRequest partialGradesRequest) {
        logger.info("Creating partial grade {}", partialGradesRequest);

        var finalGrade = finalGradeRepository.findById(partialGradesRequest.finalGradeId())
                .orElseThrow(() -> new RuntimeException("Final grade ID is required"));

        PartialGradesModel partialGradesModel = new PartialGradesModel();
        partialGradesModel.setFinalGrade(finalGrade);
        partialGradesModel.setGrade(partialGradesRequest.grade());


        PartialGradesModel savedPartialGrade = partialGradesRepository.save(partialGradesRequest.toEntity());

        return new PartialGradesResponse(savedPartialGrade);

    }

    public PartialGradesResponse updatePartialGrade(String partialGradeId, PartialGradesRequest partialGradesRequest) {
        logger.info("Updating partial grade {}", partialGradeId);

        PartialGradesModel existingPartialGrade = partialGradesRepository.findById(partialGradeId)
                .orElseThrow(() -> new RuntimeException("Partial grade was not found"));

        existingPartialGrade.setSubmission(partialGradesRequest.submission());
        existingPartialGrade.setGrade(partialGradesRequest.grade());
        existingPartialGrade.setTeacherFeedback(partialGradesRequest.teacherFeedback());
        existingPartialGrade.setSubmissionDate(partialGradesRequest.submissionDate());
        existingPartialGrade.setCorrectionDate(partialGradesRequest.correctionDate());

        PartialGradesModel updatedPartialGrade = partialGradesRepository.save(existingPartialGrade);

        return new PartialGradesResponse(updatedPartialGrade);
    }

    public void deletePartialGrade(String partialGradeId) {
        logger.info("Deleting partial grade {}", partialGradeId);

        partialGradesRepository.deleteById(partialGradeId);
    }


}
