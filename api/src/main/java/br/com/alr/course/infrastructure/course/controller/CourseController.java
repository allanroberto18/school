package br.com.alr.course.infrastructure.course.controller;

import br.com.alr.course.domain.course.service.CourseService;
import br.com.alr.course.infrastructure.course.resource.request.CourseRequest;
import br.com.alr.course.infrastructure.course.resource.response.CourseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @Operation(summary = "Course's List")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Course's list",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CourseResponse.class)
              )
          }
      )
  })
  @GetMapping
  public ResponseEntity<List<CourseResponse>> getCourses() {
    return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
  }

    @Operation(summary = "Available Courses")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Available Courses",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CourseResponse.class)
                            )
                    }
            )
    })
    @GetMapping(path = "/available")
    public ResponseEntity<List<CourseResponse>> getCoursesAvailable() {
        return new ResponseEntity<>(courseService.getCoursesAvailable(), HttpStatus.OK);
    }

  @Operation(summary = "Get course by Id")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Course selected",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CourseResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content)
  })
  @GetMapping("/{id}")
  public ResponseEntity<CourseResponse> getCourse(@PathVariable Integer id) {
    return new ResponseEntity<>(courseService.getById(id), HttpStatus.OK);
  }

  @Operation(summary = "Add new course")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Course added",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CourseResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content)
  })
  @PostMapping
  public ResponseEntity<CourseResponse> addCourse(@Valid @RequestBody CourseRequest request) {
    return new ResponseEntity<>(courseService.add(request), HttpStatus.CREATED);
  }

  @Operation(summary = "Update course")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Course updated",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CourseResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content)
  })
  @PutMapping("/{id}")
  public ResponseEntity<CourseResponse> updateCourse(
      @PathVariable Integer id,
      @Valid @RequestBody CourseRequest request
  ) {
    return new ResponseEntity<>(courseService.update(id, request), HttpStatus.OK);
  }

  @Operation(summary = "Delete course")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "202",
          description = "Course deleted",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CourseResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "404", description = "Course Not Found", content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    courseService.delete(id);

    return new ResponseEntity(null, HttpStatus.ACCEPTED);
  }
}
