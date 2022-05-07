package ru.surantaev.jwt_security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.surantaev.jwt_security.entity.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}