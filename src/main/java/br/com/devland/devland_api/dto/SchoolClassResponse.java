package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.SchoolClassModel;

public record SchoolClassResponse(
        String schoolClassId,
        String name,
        String startDate,
        String endDate,
        String status
) {
    public SchoolClassResponse(SchoolClassModel entity) {
        this(
                entity.getSchoolClassId(),
                entity.getName(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStatus().name()
        );
    }
}
