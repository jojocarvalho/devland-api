package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.MaterialRequest;
import br.com.devland.devland_api.dto.MaterialResponse;
import br.com.devland.devland_api.exceptions.CourseModuleNotFoundException;
import br.com.devland.devland_api.exceptions.MaterialNotFoundException;
import br.com.devland.devland_api.model.CourseModuleModel;
import br.com.devland.devland_api.model.MaterialModel;
import br.com.devland.devland_api.repository.CourseModuleRepository;
import br.com.devland.devland_api.repository.MaterialRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    private final Logger logger = getLogger(MaterialService.class.getName());

    public List<MaterialResponse> getAllMaterials() {
        logger.info("Listing all materials");

        return materialRepository.findAll()
                .stream()
                .map(MaterialResponse::new)
                .toList();
    }

    public MaterialResponse getMaterialById(String materialId) {
        logger.info("Getting material by id " + materialId);

        var materialModel = materialRepository.findById(materialId)
                .orElseThrow(() -> new MaterialNotFoundException("Material was not found"));

        return new MaterialResponse(materialModel);
    }

    public MaterialResponse createMaterial(MaterialRequest materialRequest) {
        logger.info("Creating a new material");

        CourseModuleModel courseModule = courseModuleRepository.findById(materialRequest.courseModuleId())
                .orElseThrow(() -> new CourseModuleNotFoundException("Course module was not found"));

        MaterialModel material = materialRequest.toEntity();

        material.setTitle(materialRequest.title());
        material.setFileType(materialRequest.fileType());
        material.setFileUrl(materialRequest.fileUrl());
        material.setPublic(materialRequest.isPublic());
        material.setCourseModule(courseModule);

        materialRepository.save(material);

        return new MaterialResponse(material);
    }

    public MaterialResponse updateMaterial(String materialId, MaterialRequest materialRequest) {
        logger.info("Updating material with id " + materialId);

        MaterialModel existingMaterial = materialRepository.findById(materialId)
                .orElseThrow(() -> new MaterialNotFoundException("Material was not found"));

        CourseModuleModel courseModule = courseModuleRepository.findById(materialRequest.courseModuleId())
                .orElseThrow(() -> new CourseModuleNotFoundException("Course module was not found"));

        existingMaterial.setTitle(materialRequest.title());
        existingMaterial.setFileType(materialRequest.fileType());
        existingMaterial.setFileUrl(materialRequest.fileUrl());
        existingMaterial.setPublic(materialRequest.isPublic());
        existingMaterial.setCourseModule(courseModule);

        return new MaterialResponse(existingMaterial);
    }

    public void deleteMaterial(String materialId) {
        logger.info("Deleting material with id " + materialId);

        materialRepository.deleteById(materialId);
    }
}
