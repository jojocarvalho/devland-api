package br.com.devland.devland_api.model;

import br.com.devland.devland_api.enums.StudentEvaluation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "final_grade")
@Getter
@Setter
public class FinalGradeModel {

    @Id
    private String finalGradeId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentModel student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseModel course;

    @Column(name = "final_average", nullable = false)
    private int finalAverage;

    @Column(name = "status", nullable = false)
    private StudentEvaluation status;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
