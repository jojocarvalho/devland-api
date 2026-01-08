package br.com.devland.devland_api.repository;

import br.com.devland.devland_api.model.GuardianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuardianRepository extends JpaRepository<GuardianModel, String> {

    Optional<GuardianModel> findByCpf(String cpf);

}
