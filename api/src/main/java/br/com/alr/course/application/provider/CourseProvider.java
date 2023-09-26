package br.com.alr.course.application.provider;

import br.com.alr.course.configuration.exception.NotFoundException;
import br.com.alr.course.domain.course.exception.CourseNotFoundException;
import br.com.alr.course.domain.course.model.Course;
import br.com.alr.course.domain.course.repository.CourseRepository;
import br.com.alr.course.domain.provider.Provider;
import org.springframework.stereotype.Component;

@Component
public class CourseProvider implements Provider<Course> {

  private final CourseRepository courseRepository;

  public CourseProvider(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Override
  public Course findById(Integer id) throws NotFoundException {
    return courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException(id));
  }
}
