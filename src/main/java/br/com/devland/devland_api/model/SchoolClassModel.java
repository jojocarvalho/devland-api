package br.com.devland.devland_api.model;

import br.com.devland.devland_api.enums.SchoolClassStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "school_class")
@Getter
@Setter
public class SchoolClassModel {

    @Id
    private String schoolClassId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "status", nullable = false)
    private SchoolClassStatus status;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    private CourseModel courseId;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    private TeacherModel teacher;

}