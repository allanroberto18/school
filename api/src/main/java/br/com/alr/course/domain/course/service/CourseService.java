package br.com.alr.course.domain.course.service;

import br.com.alr.course.infrastructure.course.resource.request.CourseRequest;
import br.com.alr.course.infrastructure.course.resource.response.CourseResponse;
import java.util.List;

public interface CourseService {
  List<CourseResponse> getAll();
  List<CourseResponse> getCoursesAvailable();
  CourseResponse getById(Integer id);
  CourseResponse add(CourseRequest request);
  CourseResponse update(Integer id, CourseRequest request);
  void delete(Integer id);
}
