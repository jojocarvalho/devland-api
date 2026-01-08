package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.enums.EnrollmentStatus;
import br.com.devland.devland_api.model.EnrollmentModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EnrollmentRequest(

        @NotBlank(message = "Data de matrícula é obrigatória")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonProperty("enrollment_date")
        LocalDate enrollmentDate,

        @NotBlank(message = "Status da matrícula é obrigatório")
        EnrollmentStatus status,

        @JsonProperty("final_grade")
        Integer finalGrade,

        @NotBlank(message = "Registro de matrícula do aluno é obrigatório")
        @JsonProperty("student_id")
        String studentId,

        @NotBlank(message = "Turma em que o aluno será matriculado é obrigatória")
        @JsonProperty("school_class_id")
        String schoolClassId
) {

    public EnrollmentModel toEntity(){
        EnrollmentModel enrollmentModel = new EnrollmentModel();
        enrollmentModel.setEnrollmentId("ENROLLMENT_" + java.util.UUID.randomUUID().toString().toUpperCase());
        enrollmentModel.setEnrollmentDate(this.enrollmentDate);
        enrollmentModel.setStatus(this.status);
        enrollmentModel.setFinalGrade(this.finalGrade);

        return enrollmentModel;
    }
}
