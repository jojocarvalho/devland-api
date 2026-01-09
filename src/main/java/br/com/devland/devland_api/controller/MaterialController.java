package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.MaterialRequest;
import br.com.devland.devland_api.dto.MaterialResponse;
import br.com.devland.devland_api.service.MaterialService;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Materiais", description = "Endpoints para gerenciar materiais de cursos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    private final Logger logger = getLogger(MaterialController.class);


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialResponse> getAllMaterials(){
        logger.info("Listing all materials");

        return materialService.getAllMaterials();
    }

    @GetMapping("/{materialId}")
    @ResponseStatus(HttpStatus.OK)
    public MaterialResponse getMaterialById(@PathVariable String materialId) {
        logger.info("Getting material by id {}", materialId);
        return materialService.getMaterialById(materialId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MaterialResponse> createMaterial(@RequestBody MaterialRequest materialRequest, UriComponentsBuilder uriBuilder){
        logger.info("Creating a new material");

        var materialResponse  = materialService.createMaterial(materialRequest);

        var uri = uriBuilder
                .path("/materials/{id}")
                .buildAndExpand(materialResponse.materialId())
                .toUri();

        return ResponseEntity.created(uri).body(materialResponse);

    }

    @PutMapping("/{materialId}")
    @ResponseStatus(HttpStatus.OK)
    public MaterialResponse updateMaterial(
            @PathVariable String materialId,
            @RequestBody MaterialRequest materialRequest
    ) {
        logger.info("Updating material with id {}", materialId);
        return materialService.updateMaterial(materialId, materialRequest);
    }

    @DeleteMapping("/{materialId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable String materialId) {
        logger.info("Deleting material with id {}", materialId);
        materialService.deleteMaterial(materialId);
    }


}
