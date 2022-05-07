package ru.surantaev.jwt_security.api;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.surantaev.jwt_security.entity.models.Student;
import ru.surantaev.jwt_security.service.StudentService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Student> findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student){

        if (student.getId() != null && student.getId() != 0) {
            return new ResponseEntity("параметр: id ДОЛЖЕН быть нулевым", HttpStatus.NOT_ACCEPTABLE);
        }
        if (student.getFirstName() == null || student.getFirstName().trim().length() == 0) {
            return new ResponseEntity("назначенный параметр: name", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Student student) {

        if (student.getId() == null && student.getId() == 0) {
            return new ResponseEntity("параметр: ID не ДОЛЖЕН быть нулевым", HttpStatus.NOT_ACCEPTABLE);
        }
        if (student.getFirstName() == null || student.getFirstName().trim().length() == 0) {
            return new ResponseEntity("назначенный параметр: name", HttpStatus.NOT_ACCEPTABLE);
        }
        studentService.updateStudent(student);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable ("id") Long id) {
        try {
            studentService.delete(id);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id " + id + " не найден", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/name/{name}")
    public List<Student> findStudentsByFirstName(@PathVariable String name) {
        List<Student> students = studentService.findStudentsByFirstName(name);
        return students;
    }

    @GetMapping("/count")
    public Long countStudent(){
        Long count = studentService.countStudent();
        return count;
    }
}
