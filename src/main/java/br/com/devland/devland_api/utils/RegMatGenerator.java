package br.com.devland.devland_api.utils;

import br.com.devland.devland_api.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RegMatGenerator {

    private final StudentRepository studentRepository;

    public RegMatGenerator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String generateRegMat() {
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String prefix = "REGMAT" + year;

        String lastRegMat = studentRepository.findLastRegMat(prefix);

        if (lastRegMat == null) {
            return prefix + "0001";
        } else {
            int lastNumber = Integer.parseInt(lastRegMat.substring(prefix.length()));
            int newNumber = lastNumber + 1;

            return prefix + String.format("%04d", newNumber);
        }
    }

    public String generateRegMatTeacher() {
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String prefix = "PROF" + year;

        String lastRegMat = studentRepository.findLastRegMat(prefix);

        if (lastRegMat == null) {
            return prefix + "0001";
        } else {
            int lastNumber = Integer.parseInt(lastRegMat.substring(prefix.length()));
            int newNumber = lastNumber + 1;

            return prefix + String.format("%04d", newNumber);
        }
    }
}
