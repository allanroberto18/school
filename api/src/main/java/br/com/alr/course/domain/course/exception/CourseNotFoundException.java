package br.com.alr.course.domain.course.exception;

import br.com.alr.course.configuration.exception.NotFoundException;

public class CourseNotFoundException extends NotFoundException {

  public CourseNotFoundException(Integer id) {
    super(String.format("Course not found under id %s", id));
  }
}
