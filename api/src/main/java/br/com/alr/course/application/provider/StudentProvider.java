package br.com.alr.course.application.provider;

import br.com.alr.course.configuration.exception.NotFoundException;
import br.com.alr.course.domain.courseRegistration.exception.DuplicatedCourseException;
import br.com.alr.course.domain.provider.Provider;
import br.com.alr.course.domain.student.exception.StudentNotFoundException;
import br.com.alr.course.domain.student.model.Student;
import br.com.alr.course.domain.student.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class StudentProvider implements Provider<Student> {

  private final StudentRepository studentRepository;

  public StudentProvider(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public Student findById(Integer id) throws NotFoundException {
    return studentRepository.findById(id)
        .orElseThrow(() -> new StudentNotFoundException(id));
  }
}
