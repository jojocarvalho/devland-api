package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.SchoolClassRequest;
import br.com.devland.devland_api.dto.SchoolClassResponse;
import br.com.devland.devland_api.exceptions.CourseNotFoundException;
import br.com.devland.devland_api.exceptions.SchoolClassNotFoundException;
import br.com.devland.devland_api.exceptions.TeacherNotFoundException;
import br.com.devland.devland_api.model.CourseModel;
import br.com.devland.devland_api.model.SchoolClassModel;
import br.com.devland.devland_api.model.TeacherModel;
import br.com.devland.devland_api.repository.CourseRepository;
import br.com.devland.devland_api.repository.SchoolClassRepository;
import br.com.devland.devland_api.repository.TeacherRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;


@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    private final Logger logger = getLogger(SchoolClassService.class);

    public List<SchoolClassResponse> getAllSchoolClasses() {
        logger.info("Listing all school classes");

        return schoolClassRepository.findAll()
                .stream()
                .map(SchoolClassResponse::new)
                .toList();
    }

    public SchoolClassResponse getSchoolClassById(String schoolClassId) {
        logger.info("Getting school class by id {}", schoolClassId);

        var schoolClassModel = schoolClassRepository.findById(schoolClassId)
                .orElseThrow(() -> new RuntimeException("School class was not found"));

        return new SchoolClassResponse(schoolClassModel);
    }

    public SchoolClassResponse createSchoolClass(SchoolClassRequest schoolClassRequest) {
        logger.info("Creating a new school class");

        CourseModel course = courseRepository.findById(schoolClassRequest.course())
                .orElseThrow(() -> new CourseNotFoundException("Course was not found"));

        TeacherModel teacher = teacherRepository.findById(schoolClassRequest.teacher())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher was not found"));

        SchoolClassModel schoolClass = schoolClassRequest.toEntity();

        schoolClass.setName(schoolClassRequest.name());
        schoolClass.setStartDate(schoolClassRequest.startDate());
        schoolClass.setEndDate(schoolClassRequest.endDate());
        schoolClass.setStatus(schoolClassRequest.status());
        schoolClass.setCourseId(course);
        schoolClass.setTeacher(teacher);

        schoolClassRepository.save(schoolClass);

        return new SchoolClassResponse(schoolClass);
    }

    public SchoolClassResponse updateSchoolClass(String schoolClassId, SchoolClassRequest schoolClassRequest) {
        logger.info("Updating school class with id {}", schoolClassId);

        CourseModel course = courseRepository.findById(schoolClassRequest.course())
                .orElseThrow(() -> new CourseNotFoundException("Course was not found"));

        TeacherModel teacher = teacherRepository.findById(schoolClassRequest.teacher())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher was not found"));

        SchoolClassModel existingSchoolClass = schoolClassRepository.findById(schoolClassId)
                .orElseThrow(() -> new SchoolClassNotFoundException("School class was not found"));

        existingSchoolClass.setName(schoolClassRequest.name());
        existingSchoolClass.setStartDate(schoolClassRequest.startDate());
        existingSchoolClass.setEndDate(schoolClassRequest.endDate());
        existingSchoolClass.setStatus(schoolClassRequest.status());
        existingSchoolClass.setCourseId(course);
        existingSchoolClass.setTeacher(teacher);

        SchoolClassModel updatedSchoolClass = schoolClassRepository.save(existingSchoolClass);

        return new SchoolClassResponse(updatedSchoolClass);
    }

    public void deleteSchoolClass(String schoolClassId) {
        logger.info("Deleting school class with id {}", schoolClassId);

        SchoolClassModel existingSchoolClass = schoolClassRepository.findById(schoolClassId)
                .orElseThrow(() -> new SchoolClassNotFoundException("School class was not found"));

        schoolClassRepository.deleteById(schoolClassId);
    }


}
