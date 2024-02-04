package com.lebanking.api.application.exceptionhandler;

import com.lebanking.api.common.exception.InsufficientFundsException;
import com.lebanking.api.common.exception.ResourceNotFoundException;
import com.lebanking.api.common.exception.UnableSendEmailException;
import com.lebanking.api.common.exception.WebhookSiteException;
import feign.RetryableException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
            headers, HttpStatusCode status, WebRequest request) {

        List<Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fields.add(new Field(name, message));
        }

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente!",
                fields
        );

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
                                                               HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                "Faltando variável de caminho obrigatória: " + ex.getVariableName(),
                null
        );

        return handleExceptionInternal(ex, problem, headers, status, request);
    }



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Object> handleInsufficientFundsException(InsufficientFundsException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(WebhookSiteException.class)
    public ResponseEntity<Object> WebhookSiteException(WebhookSiteException ex, WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(500);

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Object> RetryableException(RetryableException ex, WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(500);

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UnableSendEmailException.class)
    public ResponseEntity<Object> UnableSendEmailException(UnableSendEmailException ex, WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(500);

        Problem problem = new Problem(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }



}
