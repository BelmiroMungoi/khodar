package com.bbm.khodar.exception.handler;

import com.bbm.khodar.exception.BadRequestException;
import com.bbm.khodar.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class KhodarExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<StandardError.ValidationError> validationErrors = new ArrayList<>();

        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            validationErrors.add(new StandardError.ValidationError(name, msg));
        }

        StandardError errorResponse = new StandardError();
        errorResponse.setCode(httpStatus.value());
        errorResponse.setStatus(httpStatus);
        errorResponse.setTitle("Erro de validacão! Um ou mais campos estão inválidos!");
        errorResponse.setTime(OffsetDateTime.now());
        errorResponse.setPath(request.getContextPath());
        errorResponse.setFields(validationErrors);

        return super.handleExceptionInternal(ex, errorResponse, headers, httpStatus, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError errorResponse = new StandardError();
        errorResponse.setCode(status.value());
        errorResponse.setStatus(status);
        errorResponse.setTitle(ex.getMessage());
        errorResponse.setTime(OffsetDateTime.now());
        errorResponse.setPath(request.getRequestURI());

        return ResponseEntity.badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleBadRequestException(EntityNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError errorResponse = new StandardError();
        errorResponse.setCode(status.value());
        errorResponse.setStatus(status);
        errorResponse.setTitle(ex.getMessage());
        errorResponse.setTime(OffsetDateTime.now());
        errorResponse.setPath(request.getRequestURI());

        return ResponseEntity.badRequest()
                .body(errorResponse);
    }
}
