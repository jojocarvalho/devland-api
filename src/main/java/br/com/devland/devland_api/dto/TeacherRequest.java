package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.TeacherModel;
import jakarta.validation.constraints.NotBlank;

public record TeacherRequest(

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @NotBlank(message = "O email é obrigatório")
        String email,

        String password

) {

    public TeacherModel toEntity() {
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setTeacherId("TEACHER_" + java.util.UUID.randomUUID().toString().toUpperCase());
        teacherModel.setName(this.name);
        teacherModel.setPhone(this.phone);
        teacherModel.setEmail(this.email);
        return teacherModel;
    }
}
