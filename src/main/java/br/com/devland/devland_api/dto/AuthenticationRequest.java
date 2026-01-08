package br.com.devland.devland_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "O RegMat é obrigatório")
        @JsonProperty("username")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        String password
) {
}
