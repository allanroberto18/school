package br.com.alr.course.domain.courseRegistration.exception;

import br.com.alr.course.configuration.exception.DuplicatedItemException;

public class DuplicatedCourseException extends DuplicatedItemException {

  public DuplicatedCourseException(Integer courseId, Integer studentId) {
    super(String.format("Student %s already has the course %s", studentId, courseId));
  }
}
