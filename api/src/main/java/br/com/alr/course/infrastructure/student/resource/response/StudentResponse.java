package br.com.alr.course.infrastructure.student.resource.response;

public record StudentResponse(
    Integer id,
    String name,
    String email
) { }
