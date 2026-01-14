//package br.com.devland.devland_api.controller;
//
//import br.com.devland.devland_api.dto.PartialGradesRequest;
//import br.com.devland.devland_api.dto.PartialGradesResponse;
//import br.com.devland.devland_api.service.PartialGradesService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.List;
//
//import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;
//
//@Tag(name = "Nota Parcial", description = "Endpoints para gerenciar notas parciais")
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/partial-grades")
//public class PartialGradesController {
//
//    @Autowired
//    PartialGradesService partialGradesService;
//
//    private final Logger logger = getLogger(FinalGradeController.class);
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<PartialGradesResponse> getAllPartialGrades() {
//        logger.info("Listing all final grades");
//
//        return partialGradesService.getAllPartialGrades();
//    }
//
//    @GetMapping("/{partialGradeId}")
//    @ResponseStatus(HttpStatus.OK)
//    public PartialGradesResponse getFinalGradeById(@PathVariable String partialGradeId) {
//        logger.info("Getting final grade by id {}", partialGradeId);
//
//        return partialGradesService.getPartialGradeById(partialGradeId);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<PartialGradesResponse> createFinalGrade(@RequestBody PartialGradesRequest partialGradesRequest, UriComponentsBuilder uriBuilder) {
//        logger.info("Creating a new final grade");
//
//        var partialGradesResponse = partialGradesService.createPartialGrade(partialGradesRequest);
//
//        var uri = uriBuilder
//                .path("/courses/{id}")
//                .buildAndExpand(partialGradesResponse.finalGradeId())
//                .toUri();
//
//        return ResponseEntity.created(uri).body(partialGradesResponse);
//    }
//
//    @PutMapping("/{partialGradeId}")
//    @ResponseStatus(HttpStatus.OK)
//    public PartialGradesResponse updateFinalGrade(@PathVariable String partialGradeId, @RequestBody PartialGradesRequest partialGradesRequest) {
//        logger.info("Updating final grade {}", partialGradeId);
//        return partialGradesService.updatePartialGrade(partialGradeId, partialGradesRequest);
//    }
//
//    @DeleteMapping("/{partialGradeId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePartialGrade(@PathVariable String partialGradeId) {
//        logger.info("Deleting partial grade with id {}", partialGradeId);
//        partialGradesService.deletePartialGrade(partialGradeId);
//    }
//
//}
