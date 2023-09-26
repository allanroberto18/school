package br.com.alr.course.infrastructure.student.resource.request;

import jakarta.validation.constraints.NotNull;

public record StudentCourseRequest(
    @NotNull
    Integer course
) { }
