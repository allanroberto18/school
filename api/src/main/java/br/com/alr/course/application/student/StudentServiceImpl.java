package br.com.alr.course.application.student;

import br.com.alr.course.application.mapper.student.StudentMapper;
import br.com.alr.course.domain.provider.Provider;
import br.com.alr.course.domain.student.model.Student;
import br.com.alr.course.domain.student.repository.StudentRepository;
import br.com.alr.course.domain.student.service.StudentService;
import br.com.alr.course.infrastructure.student.resource.request.StudentRequest;
import br.com.alr.course.infrastructure.student.resource.response.StudentListResponse;
import br.com.alr.course.infrastructure.student.resource.response.StudentResponse;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
  private final StudentRepository studentRepository;
  private final Provider<Student> studentProvider;
  private final StudentMapper studentMapper;

  public StudentServiceImpl(
      StudentRepository studentRepository,
      Provider<Student> studentProvider,
      StudentMapper studentMapper
  ) {
    this.studentRepository = studentRepository;
    this.studentProvider = studentProvider;
    this.studentMapper = studentMapper;
  }

  @Override
  public List<StudentResponse> getAll() {
    return studentRepository.findAll()
        .stream()
        .map(studentMapper::mapToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public StudentResponse getById(Integer id) {
    var entity = studentProvider.findById(id);

    return studentMapper.mapToResponse(entity);
  }

  @Override
  public StudentResponse add(StudentRequest request) {
    var entity = studentMapper.mapToEntity(request);

    studentRepository.save(entity);

    return studentMapper.mapToResponse(entity);
  }

  @Override
  public StudentResponse update(Integer id, StudentRequest request) {
    var entity = studentProvider.findById(id);

    entity = studentMapper.mapToEntity(entity, request);

    studentRepository.save(entity);

    return studentMapper.mapToResponse(entity);
  }

  @Override
  public void delete(Integer id) {
    var entity = studentProvider.findById(id);

    studentRepository.delete(entity);
  }
}
