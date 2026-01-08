package br.com.devland.devland_api.service;


import br.com.devland.devland_api.dto.AdminRequest;
import br.com.devland.devland_api.dto.AdminResponse;
import br.com.devland.devland_api.model.AdminModel;
import br.com.devland.devland_api.repository.AdminRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = getLogger(AdminService.class.getName());

    public List<AdminResponse> getAllAdmins() {
        logger.info("Listing all admins");

        return adminRepository.findAll()
                .stream()
                .map(AdminResponse::new)
                .toList();
    }

    public AdminResponse getAdminById(String adminId) {
        logger.info("Getting admin by id " + adminId);

        AdminModel adminModel = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return new AdminResponse(adminModel);
    }

    public AdminResponse createAdmin(AdminRequest adminRequest) {
        logger.info("Creating a new admin");

        AdminModel admin = adminRequest.toEntity();

        admin.setUsername(adminRequest.username());
        admin.setPassword(passwordEncoder.encode(adminRequest.password()));
        admin.setName(adminRequest.name());
        admin.setEmail(adminRequest.email());


        var savedAdmin = adminRepository.save(admin);


        return new AdminResponse(savedAdmin);
    }

    public AdminResponse updateAdmin(String adminId, AdminRequest adminRequest) {
        logger.info("Updating admin with id " + adminId);

        AdminModel existingAdmin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        existingAdmin.setUsername(adminRequest.username());
        existingAdmin.setPassword(passwordEncoder.encode(adminRequest.password()));
        existingAdmin.setName(adminRequest.name());
        existingAdmin.setEmail(adminRequest.email());

        var updatedAdmin = adminRepository.save(existingAdmin);

        return new AdminResponse(updatedAdmin);
    }

    public void deleteAdmin(String adminId) {
        logger.info("Deleting admin with id " + adminId);

        AdminModel existingAdmin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        adminRepository.delete(existingAdmin);
    }
}
