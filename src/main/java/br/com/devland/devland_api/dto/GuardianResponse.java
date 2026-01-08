package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.GuardianModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public record GuardianResponse (
        String guardianId,
        String name,
        String phone,
        String email,
        String cpf,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate,
        String address
) {

    public GuardianResponse(GuardianModel entity) {
        this(
                entity.getGuardianId(),
                entity.getName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getBirthDate(),
                entity.getAddress()
        );
    }

}
