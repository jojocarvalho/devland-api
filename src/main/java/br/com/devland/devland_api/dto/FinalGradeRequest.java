package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.enums.StudentEvaluation;
import br.com.devland.devland_api.model.FinalGradeModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record FinalGradeRequest(

        @NotBlank(message = "student_id cannot be blank")
        @JsonProperty("student_id")
        String studentId,

        @NotBlank(message = "course_id cannot be blank")
        @JsonProperty("course_id")
        String courseId,

        @JsonProperty("final_average")
        int finalAverage,

        StudentEvaluation status,

        @NotBlank(message = "updated_at cannot be blank")
        @JsonProperty("updated_at")
        LocalDateTime updatedAt
) {

        public FinalGradeModel toEntity() {
            FinalGradeModel finalGradeModel = new FinalGradeModel();
            finalGradeModel.setFinalGradeId("FINAL_GRADE_" + java.util.UUID.randomUUID().toString().toUpperCase());
            finalGradeModel.setFinalAverage(this.finalAverage);
            finalGradeModel.setStatus(this.status);
            finalGradeModel.setUpdatedAt(this.updatedAt);
            return finalGradeModel;
        }
}
