package br.com.alr.course.domain.student.service;

import br.com.alr.course.infrastructure.student.resource.request.StudentRequest;
import br.com.alr.course.infrastructure.student.resource.response.StudentResponse;

import java.util.List;

public interface StudentService {
  List<StudentResponse> getAll();
  StudentResponse getById(Integer id);
  StudentResponse add(StudentRequest request);
  StudentResponse update(Integer id, StudentRequest request);
  void delete(Integer id);
}
