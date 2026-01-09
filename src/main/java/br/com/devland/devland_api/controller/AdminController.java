package br.com.devland.devland_api.controller;

import br.com.devland.devland_api.dto.AdminRequest;
import br.com.devland.devland_api.dto.AdminResponse;
import br.com.devland.devland_api.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Tag(name = "Administradores", description = "Endpoints para gerenciar administradores")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    private final Logger logger = getLogger(AdminController.class.getName());

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdminResponse> getAllAdmins(){
        logger.info("Listing all admins");
        return adminService.getAllAdmins();
    }

    @GetMapping("/{adminId}")
    @ResponseStatus(HttpStatus.OK)
    public AdminResponse getAdminById(@PathVariable String adminId) {
        logger.info("Getting admin by id " + adminId);
        return adminService.getAdminById(adminId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AdminResponse> createAdmin(@RequestBody AdminRequest adminRequest, UriComponentsBuilder uriBuilder){
        logger.info("Creating a new admin");

        var adminResponse = adminService.createAdmin(adminRequest);

        var uri = uriBuilder
                .path("/admins/{id}")
                .buildAndExpand(adminResponse.adminId())
                .toUri();

        return ResponseEntity.created(uri).body(adminResponse);
    }

    @PutMapping("/{adminId}")
    @ResponseStatus(HttpStatus.OK)
    public AdminResponse updateAdmin(
            @PathVariable String adminId,
            @RequestBody AdminRequest adminRequest
    ) {
        logger.info("Updating admin with id " + adminId);
        return adminService.updateAdmin(adminId, adminRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable String adminId){
        logger.info("Deleting admin with id " + adminId);

        adminService.deleteAdmin(adminId);
    }

}
