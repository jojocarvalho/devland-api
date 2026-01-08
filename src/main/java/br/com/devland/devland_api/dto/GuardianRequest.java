package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.GuardianModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;


public record GuardianRequest (

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "O CPF é obrigatório")
        @CPF(message = "Formato de CPF inválido")
        String cpf,

        @NotBlank(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve ser uma data passada")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonProperty("birth_date")
        LocalDate birthDate,

        @NotBlank(message = "O endereço é obrigatório")
        String address
) {

    public GuardianModel toEntity() {
        GuardianModel guardianModel = new GuardianModel();
        guardianModel.setGuardianId("GUARDIAN_" + UUID.randomUUID().toString().toUpperCase());
        guardianModel.setName(this.name);
        guardianModel.setPhone(this.phone);
        guardianModel.setEmail(this.email);
        guardianModel.setCpf(this.cpf);
        guardianModel.setBirthDate(this.birthDate);
        guardianModel.setAddress(this.address);
        return guardianModel;
    }

}
