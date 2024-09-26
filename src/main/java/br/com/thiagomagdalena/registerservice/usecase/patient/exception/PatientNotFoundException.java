package br.com.thiagomagdalena.registerservice.usecase.patient.exception;

import br.com.thiagomagdalena.registerservice.api.handler.exception.NotFoundException;

public class PatientNotFoundException extends NotFoundException {

    public PatientNotFoundException() {
    }

    public PatientNotFoundException(String message) {
        super(message);
    }

    public PatientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientNotFoundException(Throwable cause) {
        super(cause);
    }
}
