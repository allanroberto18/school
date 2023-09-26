package br.com.alr.course.infrastructure.student.resource.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequest(
    @NotBlank
    String name,

    @Email
    String email
) { }
