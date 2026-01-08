package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.CourseModel;

public record CourseResponse(
        String courseId,
        String name,
        String description,
        int numberOfClasses,
        int durationHours
) {

    public CourseResponse(CourseModel entity) {
        this(
                entity.getCourseId(),
                entity.getName(),
                entity.getDescription(),
                entity.getNumberOfClasses(),
                entity.getDurationHours()
        );
    }

}
