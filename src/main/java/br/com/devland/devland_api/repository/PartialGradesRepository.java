package br.com.devland.devland_api.repository;

import br.com.devland.devland_api.model.AdminModel;
import br.com.devland.devland_api.model.PartialGradesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartialGradesRepository extends JpaRepository<PartialGradesModel, String> {


    List<PartialGradesModel> findAllByFinalGrade_FinalGradeId(String finalGradeId);
}
