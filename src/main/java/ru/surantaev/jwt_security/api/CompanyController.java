package ru.surantaev.jwt_security.api;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.surantaev.jwt_security.entity.models.Company;
import ru.surantaev.jwt_security.service.CompanyService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<Company> getAll() {
        return companyService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Company> findById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @PostMapping("/save")
    public ResponseEntity<Company> save(@RequestBody Company company){

        if (company.getId() != null && company.getId() != 0) {
            return new ResponseEntity("параметр: ID ДОЛЖЕН быть нулевым", HttpStatus.NOT_ACCEPTABLE);
        }
        if (company.getCompanyName() == null || company.getCompanyName().trim().length() == 0) {
            return new ResponseEntity("назначенный параметр: name", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(companyService.saveCompany(company));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Company company) {

        if (company.getId() == null && company.getId() == 0) {
            return new ResponseEntity("параметр: ID не ДОЛЖЕН быть нулевым", HttpStatus.NOT_ACCEPTABLE);
        }
        if (company.getCompanyName() == null || company.getCompanyName().trim().length() == 0) {
            return new ResponseEntity("назначенный параметр: name", HttpStatus.NOT_ACCEPTABLE);
        }
        //companyService.updateCompany(company);
        //return new ResponseEntity(HttpStatus.OK);
        return ResponseEntity.ok(companyService.updateCompany(company));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable ("id") Long id) {
        try {
            companyService.delete(id);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id " + id + " не найден", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public List<Company> findAllByCourseContains(@PathVariable String name) {
        return null;
    }

}
