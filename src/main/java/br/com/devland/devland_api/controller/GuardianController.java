package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.GuardianRequest;
import br.com.devland.devland_api.dto.GuardianResponse;
import br.com.devland.devland_api.service.GuardianService;
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
@RequestMapping("/guardians")
public class GuardianController {

    @Autowired
    GuardianService guardianService;

    private final Logger logger = getLogger(GuardianController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GuardianResponse> getAllGuardians(){
        logger.info("Listing all guardians");

        return guardianService.getAllGuardians();
    }

    @GetMapping("/{guardianId}")
    @ResponseStatus(HttpStatus.OK)
    public GuardianResponse getGuardianById(@PathVariable String guardianId) {
        logger.info("Getting guardian by id {}", guardianId);
        return guardianService.getGuardianById(guardianId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GuardianResponse> createGuardian(@RequestBody GuardianRequest guardianRequest, UriComponentsBuilder uriComponentsBuilder){
        logger.info("Creating a new guardian");

        var guardianResponse  = guardianService.createGuardian(guardianRequest);

        var uri = uriComponentsBuilder
                .path("/guardians/{id}")
                .buildAndExpand(guardianResponse.guardianId())
                .toUri();

        return ResponseEntity.created(uri).body(guardianResponse);
    }

    @PutMapping("/{guardianId}")
    @ResponseStatus(HttpStatus.OK)
    public GuardianResponse updateGuardian(
            @PathVariable String guardianId,
            @RequestBody GuardianRequest guardianRequest
    ) {
        logger.info("Updating guardian with id {}", guardianId);
        return guardianService.updateGuardian(guardianId, guardianRequest);
    }

    @DeleteMapping("/{guardianId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuardian(@PathVariable String guardianId){
        logger.info("Deleting guardian with id {}", guardianId);
        guardianService.deleteGuardian(guardianId);
    }
}
