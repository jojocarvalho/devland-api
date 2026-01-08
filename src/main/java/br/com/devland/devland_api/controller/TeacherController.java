package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.TeacherRequest;
import br.com.devland.devland_api.dto.TeacherResponse;
import br.com.devland.devland_api.service.TeacherService;
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
@CrossOrigin
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    private final Logger logger = getLogger(TeacherController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherResponse> getAllTeachers() {
        logger.info("Listing all teachers");
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherResponse getTeacherById(@PathVariable String teacherId) {
        logger.info("Getting teacher by id {}", teacherId);
        return teacherService.getTeacherById(teacherId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest, UriComponentsBuilder uriBuilder) {
        logger.info("Creating a new teacher");

        var teacherResponse = teacherService.createTeacher(teacherRequest);

        var uri = uriBuilder
                .path("/teachers/{id}")
                .buildAndExpand(teacherResponse.teacherId())
                .toUri();

        return ResponseEntity.created(uri).body(teacherResponse);
    }

    @PutMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponse updateTeacher(
            @PathVariable String teacherId,
            @RequestBody TeacherRequest teacherRequest
    ) {
        logger.info("Updating teacher with id {}", teacherId);
        return teacherService.updateTeacher(teacherId, teacherRequest);
    }

    @DeleteMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeacher(@PathVariable String teacherId) {
        logger.info("Deleting teacher with id {}", teacherId);
        return teacherService.deleteTeacher(teacherId);
    }


}
