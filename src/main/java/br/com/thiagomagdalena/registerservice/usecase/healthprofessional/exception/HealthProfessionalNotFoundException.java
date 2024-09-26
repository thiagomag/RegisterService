package br.com.thiagomagdalena.registerservice.usecase.healthprofessional.exception;

import br.com.thiagomagdalena.registerservice.api.handler.exception.NotFoundException;

public class HealthProfessionalNotFoundException extends NotFoundException {

    public HealthProfessionalNotFoundException() {
    }

    public HealthProfessionalNotFoundException(String message) {
        super(message);
    }

    public HealthProfessionalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HealthProfessionalNotFoundException(Throwable cause) {
        super(cause);
    }
}
