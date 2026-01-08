package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.GuardianRequest;
import br.com.devland.devland_api.dto.GuardianResponse;
import br.com.devland.devland_api.exceptions.GuardianNotFoundException;
import br.com.devland.devland_api.model.GuardianModel;
import br.com.devland.devland_api.repository.GuardianRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.devland.devland_api.configuration.LoggerConfiguration.getLogger;


@Service
public class GuardianService {

    @Autowired
    GuardianRepository guardianRepository;

    private final Logger logger = getLogger(GuardianService.class);

    public List<GuardianResponse> getAllGuardians() {
        logger.info("Listing all guardians");

        List<GuardianModel> guardians = guardianRepository.findAll();

        return guardians
                .stream()
                .map(GuardianResponse::new)
                .toList();
    }


    public GuardianResponse getGuardianById(String guardianId) {
        logger.info("Getting guardian by id {}", guardianId);

        GuardianModel guardianModel = guardianRepository.findById(guardianId)
                .orElseThrow(() -> new GuardianNotFoundException("Guardian was not found"));

        return new GuardianResponse(guardianModel);
    }

    public GuardianResponse createGuardian(GuardianRequest guardianRequest){
        logger.info("Creating a new guardian");

        GuardianModel savedGuardian = guardianRepository.save(guardianRequest.toEntity());

        return new GuardianResponse(savedGuardian);
    }

    public GuardianResponse updateGuardian(String guardianId, GuardianRequest guardianRequest){
        logger.info("Updating guardian with id {}", guardianId);

        GuardianModel existingGuardian = guardianRepository.findById(guardianId)
                .orElseThrow(() -> new GuardianNotFoundException("Guardian was not found"));

        existingGuardian.setName(guardianRequest.name());
        existingGuardian.setPhone(guardianRequest.phone());
        existingGuardian.setEmail(guardianRequest.email());
        existingGuardian.setCpf(guardianRequest.cpf());
        existingGuardian.setBirthDate(guardianRequest.birthDate());
        existingGuardian.setAddress(guardianRequest.address());

        GuardianModel updatedGuardian = guardianRepository.save(existingGuardian);

        return new GuardianResponse(updatedGuardian);
    }

    public void deleteGuardian(String guardianId) {
        logger.info("Deleting guardian with id {}", guardianId);

        guardianRepository.deleteById(guardianId);
    }
}
