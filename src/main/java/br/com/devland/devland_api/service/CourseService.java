package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.CourseRequest;
import br.com.devland.devland_api.dto.CourseResponse;
import br.com.devland.devland_api.exceptions.CourseNotFoundException;
import br.com.devland.devland_api.model.CourseModel;
import br.com.devland.devland_api.repository.CourseRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    private final Logger logger = getLogger(CourseService.class);

    public List<CourseResponse> getAllCourses() {
        logger.info("Listing all courses");

        return courseRepository.findAll()
                .stream()
                .map(CourseResponse::new)
                .toList();
    }

    public CourseResponse getCourseById(String courseId) {
        logger.info("Getting course by id {}", courseId);

        CourseModel courseModel = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course was not found"));

        return new CourseResponse(courseModel);
    }

    public CourseResponse createCourse(CourseRequest courseRequest) {
        logger.info("Creating course {}", courseRequest);

        CourseModel savedCourse = courseRepository.save(courseRequest.toEntity());

        return new CourseResponse(savedCourse);

    }

    public CourseResponse updateCourse(String courseId, CourseRequest courseRequest) {
        logger.info("Updating course {}", courseId);

        CourseModel existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course was not found"));

        existingCourse.setName(courseRequest.name());
        existingCourse.setDescription(courseRequest.description());
        existingCourse.setDurationHours(courseRequest.durationHours());
        existingCourse.setNumberOfClasses(courseRequest.numberOfClasses());

        CourseModel updatedCourse = courseRepository.save(existingCourse);

        return new CourseResponse(updatedCourse);

    }

    public void deleteCourse(String courseId) {
        logger.info("Deleting course {}", courseId);

        courseRepository.deleteById(courseId);
    }
}
