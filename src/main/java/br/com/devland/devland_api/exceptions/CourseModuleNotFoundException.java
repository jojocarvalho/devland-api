package br.com.devland.devland_api.exceptions;

public class CourseModuleNotFoundException extends RuntimeException {
    public CourseModuleNotFoundException(String message) {
        super(message);
    }
}
