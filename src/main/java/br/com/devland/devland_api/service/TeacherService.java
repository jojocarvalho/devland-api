package br.com.devland.devland_api.service;

import br.com.devland.devland_api.dto.TeacherRequest;
import br.com.devland.devland_api.dto.TeacherResponse;
import br.com.devland.devland_api.exceptions.TeacherNotFoundException;
import br.com.devland.devland_api.model.SchoolClassModel;
import br.com.devland.devland_api.model.TeacherModel;
import br.com.devland.devland_api.repository.SchoolClassRepository;
import br.com.devland.devland_api.repository.TeacherRepository;
import br.com.devland.devland_api.utils.RegMatGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private RegMatGenerator regMatGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = Logger.getLogger(TeacherService.class.getName());

    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherResponse::new)
                .toList();
    }

    public TeacherResponse getTeacherById(String teacherId) {
        var teacherModel = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher was not found"));
        return new TeacherResponse(teacherModel);
    }

    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        logger.info("Creating a new teacher");

        TeacherModel teacher = teacherRequest.toEntity();

        String regmat =  regMatGenerator.generateRegMatTeacher();

        teacher.setRegmat(regmat);
        teacher.setPassword(passwordEncoder.encode(regmat));

        teacherRepository.save(teacher);

        return new TeacherResponse(teacher);

    }

    public TeacherResponse updateTeacher(String teacherId, TeacherRequest teacherRequest) {
        logger.info("Updating teacher with id " + teacherId);

        TeacherModel existingTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher was not found"));

        existingTeacher.setName(teacherRequest.name());
        existingTeacher.setPhone(teacherRequest.phone());
        existingTeacher.setEmail(teacherRequest.email());
        existingTeacher.setPassword(passwordEncoder.encode(teacherRequest.password()));

        teacherRepository.save(existingTeacher);

        return new TeacherResponse(existingTeacher);
    }

    public String deleteTeacher(String teacherId) {
        logger.info("Deleting teacher with id " + teacherId);

        TeacherModel teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher was not found"));

        List<SchoolClassModel> classes = schoolClassRepository.findByTeacher(teacher);

        for (SchoolClassModel schoolClass : classes) {
            schoolClass.setTeacher(null);
            schoolClassRepository.save(schoolClass);
        }

        teacherRepository.deleteById(teacherId);

        return "O professor(a): " + teacher.getName() + " com o Id: " + teacherId + " foi deletado com sucesso.";
    }


}
