package br.com.alr.course.infrastructure.course.resource.request;

import br.com.alr.course.infrastructure.course.resource.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
    @NotBlank
    String name,

    @NotNull
    CourseStatus status
) { }
