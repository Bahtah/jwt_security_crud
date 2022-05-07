package ru.surantaev.jwt_security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.surantaev.jwt_security.entity.models.Company;
import ru.surantaev.jwt_security.repo.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
