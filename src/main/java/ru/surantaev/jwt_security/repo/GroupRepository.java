package ru.surantaev.jwt_security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surantaev.jwt_security.entity.models.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}