package br.com.alr.course.application.course;

import br.com.alr.course.application.mapper.course.CourseMapper;
import br.com.alr.course.domain.course.model.Course;
import br.com.alr.course.domain.course.repository.CourseRepository;
import br.com.alr.course.domain.course.service.CourseService;
import br.com.alr.course.domain.provider.Provider;
import br.com.alr.course.infrastructure.course.resource.request.CourseRequest;
import br.com.alr.course.infrastructure.course.resource.response.CourseResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseRepository;
  private final Provider<Course> courseProvider;
  private final CourseMapper courseMapper;

  public CourseServiceImpl(
      CourseRepository courseRepository,
      Provider<Course> courseProvider,
      CourseMapper courseMapper
  ) {
    this.courseRepository = courseRepository;
    this.courseProvider = courseProvider;
    this.courseMapper = courseMapper;
  }

  @Override
  public List<CourseResponse> getAll() {
    return courseRepository.findAll()
        .stream()
        .map(courseMapper::mapToResponse)
        .collect(Collectors.toList());
  }
  @Override
  public List<CourseResponse> getCoursesAvailable() {
    return courseRepository.getCoursesByEnabledIsTrue()
        .stream()
        .map(courseMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public CourseResponse getById(Integer id) {
    var entity = courseProvider.findById(id);

    return courseMapper.mapToResponse(entity);
  }

  @Override
  public CourseResponse add(CourseRequest request) {
    var entity = courseMapper.mapToEntity(request);

    courseRepository.save(entity);

    return courseMapper.mapToResponse(entity);
  }

  @Override
  public CourseResponse update(Integer id, CourseRequest request) {
    var entity = courseProvider.findById(id);

    entity = courseMapper.mapToEntity(entity, request);

    courseRepository.save(entity);

    return courseMapper.mapToResponse(entity);
  }

  @Override
  public void delete(Integer id) {
    var entity = courseProvider.findById(id);

    courseRepository.delete(entity);
  }
}
