package br.com.devland.devland_api.repository;


import br.com.devland.devland_api.model.SchoolClassModel;
import br.com.devland.devland_api.model.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolClassRepository  extends JpaRepository<SchoolClassModel, String> {

    List<SchoolClassModel> findByTeacher(TeacherModel teacherId);
}
