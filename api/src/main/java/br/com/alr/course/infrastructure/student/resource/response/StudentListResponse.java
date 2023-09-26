package br.com.alr.course.infrastructure.student.resource.response;

import java.util.List;

public record StudentListResponse(List<StudentResponse> students) { }
