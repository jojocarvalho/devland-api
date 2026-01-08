package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.TeacherModel;

public record TeacherResponse(
        String teacherId,
        String regmat,
        String name,
        String phone,
        String email
){

    public TeacherResponse(TeacherModel entity) {
        this(
                entity.getTeacherId(),
                entity.getRegmat(),
                entity.getName(),
                entity.getPhone(),
                entity.getEmail()
        );
    }

}
