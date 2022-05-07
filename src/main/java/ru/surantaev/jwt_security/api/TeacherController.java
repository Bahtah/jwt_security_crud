package ru.surantaev.jwt_security.api;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.surantaev.jwt_security.entity.models.Teacher;
import ru.surantaev.jwt_security.service.TeacherService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAll() {
        return teacherService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Teacher> findById(@PathVariable Long id) {
        return teacherService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @PostMapping("/save")
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher){

        if (teacher.getId() != null && teacher.getId() != 0) {
            return new ResponseEntity("параметр: ID ДОЛЖЕН быть нулевым", HttpStatus.NOT_ACCEPTABLE);
        }
        if (teacher.getFirstName() == null || teacher.getFirstName().trim().length() == 0) {
            return new ResponseEntity("назначенный параметр: name", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(teacherService.saveTeacher(teacher));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Teacher teacher) {

        if (teacher.getId() == null && teacher.getId() == 0) {
            return new ResponseEntity("параметр: ID не ДОЛЖЕН быть ноль", HttpStatus.NOT_ACCEPTABLE);
        }
        if (teacher.getFirstName() == null || teacher.getFirstName().trim().length() == 0) {
            return new ResponseEntity("назначенный параметр: name", HttpStatus.NOT_ACCEPTABLE);
        }
        teacherService.updateTeacher(teacher);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable ("id") Long id) {
        try {
            teacherService.delete(id);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id " + id + " не найден", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public List<Teacher> getStudentByName(@PathVariable String name) {
        return null;
    }

    /*@GetMapping("/search/{id}")
    public List<Student> getStudentsTeacher(@PathVariable ("id") Long id){
        List<Student> students = teacherService.getAllStudentsTeacher(id);
        return students;
    }*/


}
