package tech.ciesla.budgetbook.common.api;

import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.ciesla.budgetbook.common.api.exception.EntityNotFoundException;
import tech.ciesla.budgetbook.common.api.exception.UniqueAttributeException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();

        body.put("errors", errors);

        var apiError = new ApiError(
                "c1db33fe-f3e7-4de7-87df-5fa459a6842d",
                LocalDateTime.now(ZoneOffset.UTC.normalized()).toString(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                String.join(",", errors),
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected static ResponseEntity<ApiError> entityNotFoundException(
            @NonNull EntityNotFoundException ex,
            HttpServletRequest request) {
        var apiError = new ApiError(
                ex.getId().toString(),
                LocalDateTime.now(ZoneOffset.UTC.normalized()).toString(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueAttributeException.class)
    protected static ResponseEntity<ApiError> uniqueAttributeException(
            @NonNull UniqueAttributeException ex,
            HttpServletRequest request) {
        var apiError = new ApiError(
                ex.getId().toString(),
                LocalDateTime.now(ZoneOffset.UTC.normalized()).toString(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected static ResponseEntity<ApiError> illegalArgumentException(
            @NonNull IllegalArgumentException ex,
            HttpServletRequest request) {
        var apiError = new ApiError(
                "de8b97e3-1d56-4670-a1b7-f776f258a8a3",
                LocalDateTime.now(ZoneOffset.UTC.normalized()).toString(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    protected static ResponseEntity<ApiError> propertyReferenceException(
            @NonNull PropertyReferenceException ex,
            HttpServletRequest request) {
        var apiError = new ApiError(
                "3cb47024-1bb8-4e39-95c7-fc2530f6bcbe",
                LocalDateTime.now(ZoneOffset.UTC.normalized()).toString(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
