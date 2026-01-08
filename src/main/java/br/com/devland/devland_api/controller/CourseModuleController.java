package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.CourseModuleRequest;
import br.com.devland.devland_api.dto.CourseModuleResponse;
import br.com.devland.devland_api.service.CourseModuleService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/modules")
public class CourseModuleController {

    @Autowired
    CourseModuleService courseModuleService;

    private final Logger logger = getLogger(CourseModuleController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseModuleResponse> getAllCourseModules(){
        logger.info("Listing all course modules");

        return courseModuleService.getAllCourseModules();
    }

    @GetMapping("/{courseModuleId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseModuleResponse getCourseModuleById(@PathVariable String courseModuleId) {
        logger.info("Getting course module by id {}", courseModuleId);

        return courseModuleService.getCourseModuleById(courseModuleId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseModuleResponse> createCourseModule(@RequestBody CourseModuleRequest courseModuleRequest, UriComponentsBuilder uriBuilder){
        logger.info("Creating a new course module");

        var courseModuleResponse  = courseModuleService.createCourseModule(courseModuleRequest);

        var uri = uriBuilder
                .path("/courses/{id}")
                .buildAndExpand(courseModuleResponse.moduleId())
                .toUri();

        return ResponseEntity.created(uri).body(courseModuleResponse);
    }

    @PutMapping("/{courseModuleId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseModuleResponse updateCourseModule(
            @PathVariable String courseModuleId,
            @RequestBody CourseModuleRequest courseModuleRequest
    ) {
        logger.info("Updating course module with id {}", courseModuleId);

        return courseModuleService.updateCourseModule(courseModuleId, courseModuleRequest);
    }

    //    TODO: não é possível deletar módulos por enquanto
    @DeleteMapping("/{courseModuleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseModule(@PathVariable String courseModuleId){
        logger.info("Deleting course module with id {}", courseModuleId);

        courseModuleService.deleteCourseModule(courseModuleId);
    }


}
