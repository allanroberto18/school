package br.com.alr.course.domain.student.exception;

import br.com.alr.course.configuration.exception.NotFoundException;

public class StudentNotFoundException extends NotFoundException {

  public StudentNotFoundException(Integer id) {
    super(String.format("Student not found under id %s", id));
  }
}
