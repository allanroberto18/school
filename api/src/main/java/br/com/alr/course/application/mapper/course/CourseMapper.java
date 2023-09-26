package br.com.alr.course.application.mapper.course;

import br.com.alr.course.domain.course.model.Course;
import br.com.alr.course.infrastructure.course.resource.request.CourseRequest;
import br.com.alr.course.infrastructure.course.resource.response.CourseResponse;
import br.com.alr.course.infrastructure.course.resource.enums.CourseStatus;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

  public CourseResponse mapToResponse(Course entity) {
    return new CourseResponse(
        entity.getId(),
        entity.getName(),
        entity.getEnabled() ? CourseStatus.ENABLED : CourseStatus.DISABLED
    );
  }

  public Course mapToEntity(CourseRequest request) {
    return Course.builder()
        .name(request.name())
        .enabled(request.status().equals(CourseStatus.ENABLED))
        .build();
  }

  public Course mapToEntity(Course entity, CourseRequest request) {
    entity.setEnabled(request.status().equals(CourseStatus.ENABLED));
    entity.setName(request.name());

    return entity;
  }
}
