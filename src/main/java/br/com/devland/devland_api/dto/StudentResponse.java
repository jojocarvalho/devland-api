package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.StudentModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record StudentResponse(
        String studentId,
        String regmat,
        String name,
        String email,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate,
        GuardianResponse guardian
) {

    public StudentResponse(StudentModel entity) {
        this(
                entity.getStudentId(),
                entity.getRegmat(),
                entity.getName(),
                entity.getEmail(),
                entity.getBirthDate(),
                new GuardianResponse(entity.getGuardian())
        );
    }
}
