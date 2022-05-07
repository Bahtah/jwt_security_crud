package ru.surantaev.jwt_security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surantaev.jwt_security.entity.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}