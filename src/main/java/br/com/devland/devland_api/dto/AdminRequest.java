package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.AdminModel;
import jakarta.validation.constraints.NotBlank;

public record AdminRequest(

        @NotBlank(message = "O nome de usuário é obrigatório")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        String password,

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O email é obrigatório")
        String email
) {

    public AdminModel toEntity() {
        AdminModel adminModel = new AdminModel();
        adminModel.setAdminId("ADMIN_" + java.util.UUID.randomUUID().toString().toUpperCase());
        adminModel.setUsername(this.username);
        adminModel.setPassword(this.password);
        adminModel.setName(this.name);
        adminModel.setEmail(this.email);
        return adminModel;
    }
}
