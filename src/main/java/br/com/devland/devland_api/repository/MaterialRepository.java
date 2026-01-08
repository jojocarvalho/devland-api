package br.com.devland.devland_api.repository;

import br.com.devland.devland_api.model.MaterialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository  extends JpaRepository<MaterialModel, String> {
}

