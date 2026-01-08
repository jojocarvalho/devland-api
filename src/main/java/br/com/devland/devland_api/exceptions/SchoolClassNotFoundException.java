package br.com.devland.devland_api.exceptions;

public class SchoolClassNotFoundException extends RuntimeException {
    public SchoolClassNotFoundException(String message) {
        super(message);
    }
}
