package br.com.thiagomagdalena.pacientesservico.usecase.exception;

import br.com.thiagomagdalena.pacientesservico.api.handler.exception.NotFoundException;

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
