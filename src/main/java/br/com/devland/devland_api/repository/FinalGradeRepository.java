package br.com.devland.devland_api.repository;


import br.com.devland.devland_api.model.FinalGradeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinalGradeRepository extends JpaRepository<FinalGradeModel, String> {
}
