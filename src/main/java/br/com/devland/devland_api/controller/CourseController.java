package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.CourseRequest;
import br.com.devland.devland_api.dto.CourseResponse;
import br.com.devland.devland_api.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;

@Tag(name = "Cursos", description = "Endpoints para gerenciar cursos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    private final Logger logger = getLogger(CourseController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> getAllCourses() {
        logger.info("Listing all courses");

        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse getCourseById(@PathVariable String courseId) {
        logger.info("Getting course by id {]", courseId);

        return courseService.getCourseById(courseId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest, UriComponentsBuilder uriBuilder){
        logger.info("Creating a new course");

        var courseResponse  = courseService.createCourse(courseRequest);

        var uri = uriBuilder
                .path("/courses/{id}")
                .buildAndExpand(courseResponse.courseId())
                .toUri();

        return ResponseEntity.created(uri).body(courseResponse);
    }


    @PutMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse updateCourse(
            @PathVariable String courseId,
            @RequestBody CourseRequest courseRequest
    ) {
        logger.info("Updating course with id {}", courseId);

        return courseService.updateCourse(courseId, courseRequest);
    }


    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable String courseId){
        logger.info("Deleting course with id {}", courseId);

        courseService.deleteCourse(courseId);
    }
}

