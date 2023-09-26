package br.com.alr.course.configuration.resource.response;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
    LocalDateTime date,
    String message
) {

}
