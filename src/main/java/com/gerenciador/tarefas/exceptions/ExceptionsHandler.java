package com.gerenciador.tarefas.exceptions;

import com.gerenciador.tarefas.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotAllowedToExcludeException.class)
    public ResponseEntity<ErrorResponse> notAllowedToExcludeExceptionHandler(NotAllowedToExcludeException ex){


        Map<String, String> response  = new HashMap<>();
        response.put("code", ErrorsEnum.EXCLUDE_NOT_ALLOWED.toString());
        response.put("message", "It is not allowed to exclude a task with a status other than CREATED");


        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotAllowedToChangeStatusException.class)
    public ResponseEntity<ErrorResponse> notAllowedToChangeStatusExceptionHandler(NotAllowedToChangeStatusException ex){


        Map<String, String> response  = new HashMap<>();
        response.put("code", ErrorsEnum.EXCLUDE_NOT_ALLOWED.toString());
        response.put("message", ex.getMessage());


        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(ExistingTaskException.class)
    public ResponseEntity<ErrorResponse> existingTaskExceptionHandler(ExistingTaskException ex){


        Map<String, String> response  = new HashMap<>();
        response.put("code", ErrorsEnum.EXCLUDE_NOT_ALLOWED.toString());
        response.put("message", ex.getMessage());


        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
