package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.PartialGradesModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PartialGradesRequest(

        @NotBlank(message = "course_module_id cannot be blank")
        @JsonProperty("course_module_id")
        String courseModuleId,

        @NotBlank(message = "final_grade_id cannot be blank")
        @JsonProperty("final_grade_id")
        String finalGradeId,

        @NotBlank(message = "submission cannot be blank")
        @JsonProperty("submission")
        String submission,

        int grade,

        @JsonProperty("teacher_feedback")
        String teacherFeedback,

        @JsonProperty("submission_date")
        LocalDateTime submissionDate,

        @JsonProperty("correction_date")
        LocalDateTime correctionDate

) {

    public PartialGradesModel toEntity() {
        PartialGradesModel partialGradesModel = new PartialGradesModel();
        partialGradesModel.setPartialGradeId("PARTIAL_GRADE_" + java.util.UUID.randomUUID().toString().toUpperCase());
        partialGradesModel.setSubmission(this.submission);
        partialGradesModel.setGrade(this.grade);
        partialGradesModel.setTeacherFeedback(this.teacherFeedback);
        partialGradesModel.setSubmissionDate(this.submissionDate);
        partialGradesModel.setCorrectionDate(this.correctionDate);
        return partialGradesModel;
    }

}
