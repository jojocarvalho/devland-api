package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.FinalGradeModel;

public record FinalGradeResponse(
        String finalGradeId,
        StudentResponse studentId,
        CourseResponse courseId,
        int finalAverage,
        String status,
        String updatedAt
) {

    public FinalGradeResponse(FinalGradeModel entity) {
        this(
                entity.getFinalGradeId(),
                new StudentResponse(entity.getStudent()),
                new CourseResponse(entity.getCourse()),
                entity.getFinalAverage(),
                entity.getStatus().name(),
                entity.getUpdatedAt().toString()
        );
    }
}
