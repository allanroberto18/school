package br.com.alr.course.domain.course.repository;

import br.com.alr.course.domain.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> getCoursesByEnabledIsTrue();
}
