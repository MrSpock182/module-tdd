package io.github.mrspock.moduletdd.domain.exception;

public class TimeoutException extends RuntimeException {

    public TimeoutException() {
        super();
    }

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
