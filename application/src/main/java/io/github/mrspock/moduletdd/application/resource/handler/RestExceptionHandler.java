package io.github.mrspock.moduletdd.application.resource.handler;

import io.github.mrspock.moduletdd.application.resource.dto.ErrorResponse;
import io.github.mrspock.moduletdd.domain.exception.BadRequestException;
import io.github.mrspock.moduletdd.domain.exception.InternalServerError;
import io.github.mrspock.moduletdd.domain.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerError.class)
    public ErrorResponse handleInternalServerError(
            final InternalServerError exception, final HttpServletRequest request) {
        return new ErrorResponse(LocalDateTime.now(),
                request.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "There was an application error");
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundError(
            final NotFoundException exception, final HttpServletRequest request) {
        return new ErrorResponse(LocalDateTime.now(),
                request.getServletPath(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestError(
            final BadRequestException exception, final HttpServletRequest request) {
        return new ErrorResponse(LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exception.getMessage());
    }

}
