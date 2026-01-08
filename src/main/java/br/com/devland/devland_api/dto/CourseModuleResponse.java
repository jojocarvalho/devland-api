package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.CourseModuleModel;

public record CourseModuleResponse(
        String moduleId,
        String title,
        int orderIndex,
        CourseResponse course
) {

    public CourseModuleResponse(CourseModuleModel entity) {
        this(
                entity.getModuleId(),
                entity.getTitle(),
                entity.getOrderIndex(),
                new CourseResponse(entity.getCourse())
        );
    }
}
