package br.com.alr.course.application.courseRegistration;

import br.com.alr.course.application.mapper.course.CourseMapper;
import br.com.alr.course.domain.course.model.Course;
import br.com.alr.course.domain.courseRegistration.exception.DuplicatedCourseException;
import br.com.alr.course.domain.courseRegistration.model.CourseRegistration;
import br.com.alr.course.domain.courseRegistration.repository.CourseRegistrationRepository;
import br.com.alr.course.domain.courseRegistration.service.CourseRegistrationService;
import br.com.alr.course.domain.provider.Provider;
import br.com.alr.course.domain.student.model.Student;
import br.com.alr.course.infrastructure.courseRegistration.resource.request.CourseRegistrationRequest;
import br.com.alr.course.infrastructure.courseRegistration.resource.response.CoursesRegisteredResponse;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

  private final Provider<Course> courseProvider;
  private final CourseMapper courseMapper;
  private final Provider<Student> studentProvider;
  private final CourseRegistrationRepository courseRegistrationRepository;

  public CourseRegistrationServiceImpl(
      Provider<Course> courseProvider,
      CourseMapper courseMapper,
      Provider<Student> studentProvider,
      CourseRegistrationRepository courseRegistrationRepository
  ) {
    this.courseProvider = courseProvider;
    this.courseMapper = courseMapper;
    this.studentProvider = studentProvider;
    this.courseRegistrationRepository = courseRegistrationRepository;
  }

  @Override
  public CoursesRegisteredResponse getCoursesByStudent(Integer studentId) {
    var courseRegistrationList = courseRegistrationRepository.findByStudentId(studentId);

    var coursesMapped = courseRegistrationList.stream()
        .map(it -> courseMapper.mapToResponse(it.getCourse()))
        .collect(Collectors.toList());

    return new CoursesRegisteredResponse(studentId, coursesMapped);
  }

  @Override
  public CoursesRegisteredResponse addCourseToStudent(
      Integer studentId,
      CourseRegistrationRequest request
  ) {
    var student = studentProvider.findById(studentId);
    var course = courseProvider.findById(request.courseId());
    var courseRegistration = courseRegistrationRepository.findByStudentIdAndCourseId(
        student.getId(),
        course.getId()
    );

    if (courseRegistration.isPresent()) {
      throw new DuplicatedCourseException(course.getId(), student.getId());
    }

    var registration = CourseRegistration
        .builder()
        .course(course)
        .student(student)
        .registeredAt(LocalDateTime.now())
        .build();

    courseRegistrationRepository.save(registration);

    var coursesRegistered = courseRegistrationRepository.findByStudentId(studentId);

    return new CoursesRegisteredResponse(
        student.getId(),
        coursesRegistered.stream()
            .map(it -> courseMapper.mapToResponse(it.getCourse()))
            .collect(Collectors.toUnmodifiableList())
    );
  }

  @Override
  public CoursesRegisteredResponse deleteCourseFromStudent(Integer studentId, Integer courseId) {
    var student = studentProvider.findById(studentId);
    var course = courseProvider.findById(courseId);
    var courseRegistration = courseRegistrationRepository.findByStudentIdAndCourseId(
        student.getId(), course.getId()
    );

    courseRegistration.ifPresent(courseRegistrationRepository::delete);

    var coursesRegistered = courseRegistrationRepository.findByStudentId(studentId);

    return new CoursesRegisteredResponse(
        student.getId(),
        coursesRegistered.stream()
            .map(it -> courseMapper.mapToResponse(it.getCourse()))
            .collect(Collectors.toUnmodifiableList())
    );
  }
}
