package br.com.devland.devland_api.repository;

import br.com.devland.devland_api.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, String> {
}
