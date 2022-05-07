package ru.surantaev.jwt_security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surantaev.jwt_security.entity.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}