package com.gerenciador.tarefas.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gerenciador.tarefas.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class FieldsValidationHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidations(MethodArgumentNotValidException ex) {


        List<Map<String, String>>  errorsList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("field", getPropertyName(error));
                    errors.put("description", error.getDefaultMessage());

                    return errors;

                })
                .toList();
        ErrorResponse response = ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .errors(errorsList)
                .build();


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String getPropertyName(final FieldError error){

        if (error.contains(ConstraintViolation.class)) {

            try {
                final ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
                final Field field;

                field = violation.getRootBeanClass().getDeclaredField(error.getField());

                final JsonProperty annotation = field.getAnnotation(JsonProperty.class);

                if (annotation != null && annotation.value() != null && !annotation.value().isEmpty()) {
                    return annotation.value();
                }

            } catch (Exception e) {
            }
        }
        return error.getField();
    }

}
