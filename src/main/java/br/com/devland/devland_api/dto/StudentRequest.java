package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.StudentModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record StudentRequest(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve ser uma data passada")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonProperty("birth_date")
        LocalDate birthDate,

        @NotBlank(message = "O CPF do responsável é obrigatório")
        @JsonProperty("guardian_cpf")
        String guardianCpf,

        String password
) {

    public StudentModel toEntity() {
        StudentModel studentModel = new StudentModel();
        studentModel.setStudentId("STUDENT_" + java.util.UUID.randomUUID().toString().toUpperCase());
        studentModel.setName(this.name);
        studentModel.setEmail(this.email);
        studentModel.setBirthDate(this.birthDate);
        return studentModel;
    }



}
