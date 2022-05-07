package ru.surantaev.jwt_security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surantaev.jwt_security.entity.models.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByFirstName(String name);
}