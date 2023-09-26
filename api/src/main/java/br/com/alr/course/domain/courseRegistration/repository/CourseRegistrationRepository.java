package br.com.alr.course.domain.courseRegistration.repository;

import br.com.alr.course.domain.courseRegistration.model.CourseRegistration;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Integer> {
  List<CourseRegistration> findByStudentId(Integer studentId);
  Optional<CourseRegistration> findByStudentIdAndCourseId(Integer studentId, Integer courseId);
}
