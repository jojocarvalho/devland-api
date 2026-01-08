package br.com.devland.devland_api.exceptions;

public class GuardianNotFoundException extends RuntimeException {
    public GuardianNotFoundException(String message) {
        super(message);
    }
}
