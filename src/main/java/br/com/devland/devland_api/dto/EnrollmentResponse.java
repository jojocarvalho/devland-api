package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.EnrollmentModel;

public record EnrollmentResponse(
        String enrollmentId,
        String enrollmentDate,
        String status,
        Integer finalGrade,
        String studentId,
        String schoolClassId
) {

    public EnrollmentResponse(EnrollmentModel entity) {
        this(
                entity.getEnrollmentId(),
                entity.getEnrollmentDate().toString(),
                entity.getStatus().toString(),
                entity.getFinalGrade(),
                entity.getStudent().getStudentId(),
                entity.getSchoolClass().getSchoolClassId()
        );
    }
}
