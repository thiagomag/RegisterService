package br.com.thiagomagdalena.pacientesservico.api.handler.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {

    protected final Map<String, Object> errors;

    public ValidationException(String message, Map<String, Object> errors) {
        super(message);
        this.errors = errors;
    }

}
