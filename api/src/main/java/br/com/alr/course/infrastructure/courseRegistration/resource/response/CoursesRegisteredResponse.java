package br.com.alr.course.infrastructure.courseRegistration.resource.response;

import br.com.alr.course.infrastructure.course.resource.response.CourseResponse;
import java.util.List;

public record CoursesRegisteredResponse(
    Integer studentId,
    List<CourseResponse> courses
) { }
