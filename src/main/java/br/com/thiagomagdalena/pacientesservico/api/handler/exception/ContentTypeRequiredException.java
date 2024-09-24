package br.com.thiagomagdalena.pacientesservico.api.handler.exception;

public class ContentTypeRequiredException extends RuntimeException {

    public ContentTypeRequiredException() {
    }

    public ContentTypeRequiredException(String message) {
        super(message);
    }

    public ContentTypeRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentTypeRequiredException(Throwable cause) {
        super(cause);
    }

}
