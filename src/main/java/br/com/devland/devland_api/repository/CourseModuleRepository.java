package br.com.devland.devland_api.repository;


import br.com.devland.devland_api.model.CourseModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseModuleRepository extends JpaRepository<CourseModuleModel, String> {
}

