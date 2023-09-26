package br.com.alr.course.infrastructure.courseRegistration.controller;

import br.com.alr.course.domain.courseRegistration.service.CourseRegistrationService;
import br.com.alr.course.infrastructure.course.resource.response.CourseResponse;
import br.com.alr.course.infrastructure.courseRegistration.resource.request.CourseRegistrationRequest;
import br.com.alr.course.infrastructure.courseRegistration.resource.response.CoursesRegisteredResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/courses/registration")
public class CourseRegistrationController {

  private final CourseRegistrationService courseRegistrationService;

  public CourseRegistrationController(CourseRegistrationService courseRegistrationService) {
    this.courseRegistrationService = courseRegistrationService;
  }

  @Operation(summary = "Get courses from student")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Course listed from student",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CoursesRegisteredResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content),
      @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
      @ApiResponse(responseCode = "404", description = "Student Not Found", content = @Content)
  })
  @GetMapping(path = "/{studentId}")
  public ResponseEntity<CoursesRegisteredResponse> getCoursesByStudent(
      @PathVariable Integer studentId
  ) {
    return new ResponseEntity<>(
        courseRegistrationService.getCoursesByStudent(studentId),
        HttpStatus.OK
    );
  }

  @Operation(summary = "Add new course to the student")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Course added to the student",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CoursesRegisteredResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content),
      @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
      @ApiResponse(responseCode = "404", description = "Student Not Found", content = @Content)
  })
  @PostMapping(path = "/{studentId}")
  public ResponseEntity<CoursesRegisteredResponse> addCourseToStudent(
      @PathVariable Integer studentId,
      @Valid @RequestBody CourseRegistrationRequest request
  ) {
    return new ResponseEntity<>(
        courseRegistrationService.addCourseToStudent(studentId, request),
        HttpStatus.CREATED
    );
  }

  @Operation(summary = "Delete Course from student")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Course deleted from student",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CoursesRegisteredResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
      @ApiResponse(responseCode = "404", description = "Student Not Found", content = @Content)
  })
  @DeleteMapping (path = "/{studentId}/{courseId}")
  public ResponseEntity<CoursesRegisteredResponse> deleteCoursesFromStudent(
      @PathVariable Integer studentId,
      @PathVariable Integer courseId
  ) {
    return new ResponseEntity<>(
        courseRegistrationService.deleteCourseFromStudent(studentId, courseId),
        HttpStatus.ACCEPTED
    );
  }
}
