package br.com.alr.course.infrastructure.course.resource.response;

import br.com.alr.course.infrastructure.course.resource.enums.CourseStatus;

public record CourseResponse(
    Integer id,
    String name,
    CourseStatus status
) { }
