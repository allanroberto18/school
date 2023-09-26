package br.com.alr.course.configuration.resource.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorListResponse(
    LocalDateTime date,
    List<String> message
) {

}
