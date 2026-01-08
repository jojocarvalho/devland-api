package br.com.devland.devland_api.dto;

import br.com.devland.devland_api.model.AdminModel;

public record AdminResponse(
        String adminId,
        String username,
        String name,
        String email
) {

    public AdminResponse(AdminModel entity) {
        this(
                entity.getAdminId(),
                entity.getUsername(),
                entity.getName(),
                entity.getEmail()
        );
    }
}