package br.com.alr.course.domain.courseRegistration.service;

import br.com.alr.course.infrastructure.courseRegistration.resource.request.CourseRegistrationRequest;
import br.com.alr.course.infrastructure.courseRegistration.resource.response.CoursesRegisteredResponse;

public interface CourseRegistrationService {
  CoursesRegisteredResponse getCoursesByStudent(Integer studentId);
  CoursesRegisteredResponse addCourseToStudent(Integer studentId, CourseRegistrationRequest request);
  CoursesRegisteredResponse deleteCourseFromStudent(Integer studentId, Integer courseId);
}
