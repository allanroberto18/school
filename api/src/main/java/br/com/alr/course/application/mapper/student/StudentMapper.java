package br.com.alr.course.application.mapper.student;

import br.com.alr.course.domain.student.model.Student;
import br.com.alr.course.infrastructure.student.resource.request.StudentRequest;
import br.com.alr.course.infrastructure.student.resource.response.StudentResponse;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

  public StudentResponse mapToResponse(Student entity) {
    return new StudentResponse(
        entity.getId(),
        entity.getName(),
        entity.getEmail()
    );
  }

  public Student mapToEntity(StudentRequest request) {
    return Student.builder()
        .name(request.name())
        .email(request.email())
        .build();
  }

  public Student mapToEntity(Student entity, StudentRequest request) {
    entity.setName(request.name());
    entity.setEmail(request.email());

    return entity;
  }
}
