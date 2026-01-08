package br.com.devland.devland_api.service;

import br.com.devland.devland_api.model.AdminModel;
import br.com.devland.devland_api.model.StudentModel;
import br.com.devland.devland_api.repository.AdminRepository;
import br.com.devland.devland_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<StudentModel> student =  studentRepository.findByRegmat(username);

        Optional<AdminModel> admin = adminRepository.findByUsername(username);

        if (student.isPresent()) {
            return student.get();
        }

        if (admin.isPresent()) {
            return admin.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}
