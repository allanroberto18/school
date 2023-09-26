package br.com.alr.course.configuration.controller;

import br.com.alr.course.configuration.exception.DuplicatedItemException;
import br.com.alr.course.configuration.exception.NotFoundException;
import br.com.alr.course.configuration.resource.response.ErrorListResponse;
import br.com.alr.course.configuration.resource.response.ErrorResponse;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdviceController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(
            LocalDateTime.now(),
            ex.getMessage()
        ),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(value = DuplicatedItemException.class)
  public ResponseEntity<ErrorResponse> duplicatedItemExceptionHandler(DuplicatedItemException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(
            LocalDateTime.now(),
            ex.getMessage()
        ),
        HttpStatus.NOT_FOUND
    );
  }
}
