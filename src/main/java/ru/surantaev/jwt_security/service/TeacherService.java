package ru.surantaev.jwt_security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.surantaev.jwt_security.entity.models.Teacher;
import ru.surantaev.jwt_security.repo.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    /*public List<Student> getAllStudentsTeacher(Long id){
        List<Student> studentList = teacherRepository.findStudent(id);
        return studentList;
    }*/

}
