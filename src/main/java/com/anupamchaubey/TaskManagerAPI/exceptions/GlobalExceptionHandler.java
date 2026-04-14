package com.anupamchaubey.TaskManagerAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

class ErrorResponse{
    public String message;
    public int status;
    public ErrorResponse(String message, int status){
        this.message = message;
        this.status = status;
    }
}
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoTaskWithThisIdExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoTaskWithThisIdExistsException(NoTaskWithThisIdExistsException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoUserWithGivenIdExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoUserWithGivenIdExistsException(NoUserWithGivenIdExistsException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error-> errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
