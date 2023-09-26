package br.com.alr.course.infrastructure.student.controller;

import br.com.alr.course.domain.student.service.StudentService;
import br.com.alr.course.infrastructure.course.resource.response.CourseResponse;
import br.com.alr.course.infrastructure.student.resource.request.StudentRequest;
import br.com.alr.course.infrastructure.student.resource.response.StudentListResponse;
import br.com.alr.course.infrastructure.student.resource.response.StudentResponse;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @Operation(summary = "Student's List")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Student's list",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = StudentListResponse.class)
              )
          }
      )
  })
  @GetMapping
  public ResponseEntity<List<StudentResponse>> getStudents() {
    return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
  }

  @Operation(summary = "Get student by Id")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Student selected",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = StudentResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "404", description = "Student Not Found", content = @Content)
  })
  @GetMapping("/{id}")
  public ResponseEntity<StudentResponse> getStudent(@PathVariable Integer id) {
    return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
  }

  @Operation(summary = "Add new student")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Student added",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = StudentResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content)
  })
  @PostMapping
  public ResponseEntity<StudentResponse> addStudents(@Valid @RequestBody StudentRequest request) {
    return new ResponseEntity<>(studentService.add(request), HttpStatus.CREATED);
  }

  @Operation(summary = "Update student")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Student updated",
          content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = StudentResponse.class)
              )
          }
      ),
      @ApiResponse(responseCode = "404", description = "Student Not Found", content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content)
  })
  @PutMapping("/{id}")
  public ResponseEntity<StudentResponse> updateStudents(
      @PathVariable Integer id,
      @Valid @RequestBody StudentRequest request
  ) {
    return new ResponseEntity<>(studentService.update(id, request), HttpStatus.OK);
  }

  @Operation(summary = "Delete student")
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
      @ApiResponse(responseCode = "404", description = "Student Not Found", content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    studentService.delete(id);

    return new ResponseEntity(null, HttpStatus.ACCEPTED);
  }
}
