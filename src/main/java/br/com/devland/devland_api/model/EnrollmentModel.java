package br.com.devland.devland_api.model;

import br.com.devland.devland_api.enums.EnrollmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
@Getter
@Setter
public class EnrollmentModel {

    @Id
    private String enrollmentId;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "status", nullable = false)
    private EnrollmentStatus status;

    @Column(name = "final_grade")
    private Integer finalGrade;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentModel student;

    @ManyToOne
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClassModel schoolClass;

}

