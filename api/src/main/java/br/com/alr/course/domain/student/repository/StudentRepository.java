package br.com.alr.course.domain.student.repository;

import br.com.alr.course.domain.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> { }
