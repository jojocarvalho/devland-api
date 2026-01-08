package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.SchoolClassRequest;
import br.com.devland.devland_api.dto.SchoolClassResponse;
import br.com.devland.devland_api.service.SchoolClassService;
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
@RequestMapping("/school-classes")
public class SchoolClassController {

    @Autowired
    SchoolClassService schoolClassService;

    private final Logger logger = getLogger(SchoolClassController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolClassResponse> getAllSchoolClasses(){
        logger.info("Listing all school classes");

        return schoolClassService.getAllSchoolClasses();
    }


    @GetMapping("/{schoolClassId}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolClassResponse getSchoolClassById(@PathVariable String schoolClassId) {
        logger.info("Getting school class by id {}", schoolClassId);

        return schoolClassService.getSchoolClassById(schoolClassId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SchoolClassResponse> createSchoolClass(@RequestBody SchoolClassRequest schoolClassRequest, UriComponentsBuilder uriBuilder) {
        logger.info("Creating a new school class");

        var schoolClassResponse  = schoolClassService.createSchoolClass(schoolClassRequest);

        var uri = uriBuilder
                .path("/school-classes/{id}")
                .buildAndExpand(schoolClassResponse.schoolClassId())
                .toUri();

        return ResponseEntity.created(uri).body(schoolClassResponse);
    }

    @PutMapping("/{schoolClassId}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolClassResponse updateSchoolClass(
            @PathVariable String schoolClassId,
            @RequestBody SchoolClassRequest schoolClassRequest
    ) {
        logger.info("Updating school class with id {}", schoolClassId);

        return schoolClassService.updateSchoolClass(schoolClassId, schoolClassRequest);
    }

    // TODO: Não é possível deletar aulas por enquanto
    @DeleteMapping("/{schoolClassId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchoolClass(@PathVariable String schoolClassId) {
        logger.info("Deleting school class with id {}", schoolClassId);

        schoolClassService.deleteSchoolClass(schoolClassId);
    }



}
