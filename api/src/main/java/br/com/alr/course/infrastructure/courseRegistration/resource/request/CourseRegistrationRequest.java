package br.com.alr.course.infrastructure.courseRegistration.resource.request;

import jakarta.validation.constraints.NotNull;

public record CourseRegistrationRequest(
    @NotNull
    Integer courseId
) { }
