package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.PartialGradesModel;

import java.time.LocalDateTime;

public record PartialGradesResponse(
        String partialGradeId,
        CourseModuleResponse courseModuleId,
        FinalGradeResponse finalGradeId,
        String submission,
        int grade,
        String teacherFeedback,
        LocalDateTime submissionDate,
        LocalDateTime correctionDate
) {
    public PartialGradesResponse(PartialGradesModel entity) {
        this(
                entity.getPartialGradeId(),
                new CourseModuleResponse(entity.getCourseModule()),
                new FinalGradeResponse(entity.getFinalGrade()),
                entity.getSubmission(),
                entity.getGrade(),
                entity.getTeacherFeedback(),
                entity.getSubmissionDate(),
                entity.getCorrectionDate()
        );
    }
}
