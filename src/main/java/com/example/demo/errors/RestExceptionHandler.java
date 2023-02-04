package com.example.demo.errors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.errors.exception.ModelNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ModelNotFoundException.class)
  public ResponseEntity<?> handleUsuarioNotFoundException(ModelNotFoundException usuarioNotFoundException) {
    ErrorDetails errorDetails = ErrorDetails.builder()
        .message(usuarioNotFoundException.getMessage())
        .build();

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    ErrorDetails errorDetails = ErrorDetails.builder().message(ex.getMessage()).build();
    return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
    ErrorDetails errorDetails = ErrorDetails.builder()
        .message(e.getCause().getMessage())
        .build();
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
