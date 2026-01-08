package br.com.devland.devland_api.repository;

import br.com.devland.devland_api.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminModel, String> {

    Optional<AdminModel> findByUsername(String username);
}
