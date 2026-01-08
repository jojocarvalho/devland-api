package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.CourseModuleRequest;
import br.com.devland.devland_api.dto.CourseModuleResponse;
import br.com.devland.devland_api.exceptions.CourseModuleNotFoundException;
import br.com.devland.devland_api.exceptions.CourseNotFoundException;
import br.com.devland.devland_api.model.CourseModel;
import br.com.devland.devland_api.model.CourseModuleModel;
import br.com.devland.devland_api.repository.CourseModuleRepository;
import br.com.devland.devland_api.repository.CourseRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;

@Service
public class CourseModuleService {

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private CourseRepository courseRepository;

    private final Logger logger = getLogger(CourseModuleService.class);


    public List<CourseModuleResponse> getAllCourseModules() {
        logger.info("Listing all course modules");

        return courseModuleRepository.findAll()
                .stream()
                .map(CourseModuleResponse::new)
                .toList();
    }


    public CourseModuleResponse getCourseModuleById(String courseModuleId) {
        logger.info("Getting course module by id {}", courseModuleId);

        CourseModuleModel courseModuleModel = courseModuleRepository.findById(courseModuleId)
                .orElseThrow(() -> new CourseNotFoundException("Course was not found"));

        return new CourseModuleResponse(courseModuleModel);
    }

    public CourseModuleResponse createCourseModule(CourseModuleRequest courseModuleRequest) {
        logger.info("Creating a new course module");

        CourseModel course = courseRepository.findById(courseModuleRequest.courseId())
                .orElseThrow(() -> new CourseNotFoundException("Course was not found"));

        CourseModuleModel courseModule = courseModuleRequest.toEntity();

        courseModule.setTitle(courseModuleRequest.title());
        courseModule.setOrderIndex(courseModuleRequest.orderIndex());
        courseModule.setCourse(course);


        courseModuleRepository.save(courseModule);

        return new CourseModuleResponse(courseModule);
    }

    public CourseModuleResponse updateCourseModule(String courseModuleId, CourseModuleRequest courseModuleRequest) {
        logger.info("Updating course module with id {}", courseModuleId);

        CourseModel course = courseRepository.findById(courseModuleRequest.courseId())
                .orElseThrow(() -> new CourseNotFoundException("Course was not found"));

        CourseModuleModel existingCourseModule = courseModuleRepository.findById(courseModuleId)
                .orElseThrow(() -> new CourseModuleNotFoundException("Course module was not found"));

        existingCourseModule.setTitle(courseModuleRequest.title());
        existingCourseModule.setOrderIndex(courseModuleRequest.orderIndex());
        existingCourseModule.setCourse(course);

        CourseModuleModel updatedCourseModule = courseModuleRepository.save(existingCourseModule);

        return new CourseModuleResponse(updatedCourseModule);
    }

    public void deleteCourseModule(String courseModuleId) {
        logger.info("Deleting course module with id {}", courseModuleId);

        CourseModuleModel existingCourseModule = courseModuleRepository.findById(courseModuleId)
                .orElseThrow(() -> new CourseModuleNotFoundException("Course module was not found"));

        courseModuleRepository.delete(existingCourseModule);
    }
}
