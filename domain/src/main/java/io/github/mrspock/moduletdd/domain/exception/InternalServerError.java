package io.github.mrspock.moduletdd.domain.exception;

public class InternalServerError extends RuntimeException {

    public InternalServerError() {
        super();
    }

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }
}
