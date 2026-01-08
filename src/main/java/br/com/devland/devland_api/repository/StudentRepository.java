package br.com.devland.devland_api.repository;

import br.com.devland.devland_api.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository  extends JpaRepository<StudentModel, String> {

    @Query(value = "SELECT regmat FROM student WHERE regmat LIKE CONCAT(:prefix, '%') ORDER BY regmat DESC LIMIT 1", nativeQuery = true)
    String findLastRegMat(@Param("prefix") String prefix);

    Optional<StudentModel> findByRegmat(String regmat);

}
