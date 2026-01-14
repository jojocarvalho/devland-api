package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.FinalGradeRequest;
import br.com.devland.devland_api.dto.FinalGradeResponse;
import br.com.devland.devland_api.service.FinalGradeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;

@Tag(name = "Nota Final", description = "Endpoints para gerenciar notas finais")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/final-grade")
public class FinalGradeController {

    @Autowired
    FinalGradeService finalGradeService;

    private final Logger logger = getLogger(FinalGradeController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FinalGradeResponse> getAllFinalGrades() {
        logger.info("Listing all final grades");

        return finalGradeService.getAllFinalGrades();
    }

    @GetMapping("/{finalGradeId}")
    @ResponseStatus(HttpStatus.OK)
    public FinalGradeResponse getFinalGradeById(@PathVariable String finalGradeId) {
        logger.info("Getting final grade by id {}", finalGradeId);

        return finalGradeService.getFinalGradeById(finalGradeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FinalGradeResponse> createFinalGrade(@RequestBody FinalGradeRequest finalGradeRequest, UriComponentsBuilder uriBuilder){
        logger.info("Creating a new final grade");

        var finalGradeResponse  = finalGradeService.createFinalGrade(finalGradeRequest);

        var uri = uriBuilder
                .path("/courses/{id}")
                .buildAndExpand(finalGradeResponse.finalGradeId())
                .toUri();

        return ResponseEntity.created(uri).body(finalGradeResponse);
    }

    @PutMapping("/{finalGradeId}")
    @ResponseStatus(HttpStatus.OK)
    public FinalGradeResponse updateFinalGrade(@PathVariable String finalGradeId, @RequestBody FinalGradeRequest finalGradeRequest) {
        logger.info("Updating final grade {}", finalGradeId);

        return finalGradeService.updateFinalGrade(finalGradeId, finalGradeRequest);
    }


    @DeleteMapping("/{finalGradeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFinalGrade(@PathVariable String finalGradeId) {
        logger.info("Deleting final grade {}", finalGradeId);
        finalGradeService.deleteFinalGrade(finalGradeId);
    }



}
