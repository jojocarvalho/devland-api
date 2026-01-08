package br.com.devland.devland_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
public class CourseModel {

    @Id
    private String courseId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "number_of_classes", nullable = false)
    private int numberOfClasses;

    @Column(name = "duration_hours", nullable = false)
    private int durationHours;

}