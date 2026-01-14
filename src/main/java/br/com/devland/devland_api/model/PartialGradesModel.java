package br.com.devland.devland_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "partial_grades")
@Getter
@Setter
public class PartialGradesModel {

    @Id
    private String partialGradeId;

    @ManyToOne
    @JoinColumn(name = "course_module_id", nullable = false)
    private CourseModuleModel courseModule;

    @ManyToOne
    @JoinColumn(name = "final_grade_id", nullable = false)
    private FinalGradeModel finalGrade;

    @Column(name = "submission", nullable = false)
    private String submission;

    @Column(name = "grade", nullable = false)
    private int grade;

    @Column(name = "teacher_feedback", nullable = true)
    private String teacherFeedback;

    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate;

    @Column(name = "correction_date", nullable = true)
    private LocalDateTime correctionDate;
}
