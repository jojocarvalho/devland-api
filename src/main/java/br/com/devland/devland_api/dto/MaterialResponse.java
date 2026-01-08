package br.com.devland.devland_api.dto;


import br.com.devland.devland_api.model.MaterialModel;

public record MaterialResponse(
        String materialId,
        String title,
        String fileType,
        String fileUrl,
        boolean isPublic,
        CourseModuleResponse courseModule
) {

    public MaterialResponse(MaterialModel entity) {
        this(
                entity.getMaterialId(),
                entity.getTitle(),
                entity.getFileType().name(),
                entity.getFileUrl(),
                entity.isPublic(),
                new CourseModuleResponse(entity.getCourseModule())
        );
    }
}
