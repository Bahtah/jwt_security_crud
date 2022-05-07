package ru.surantaev.jwt_security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.surantaev.jwt_security.entity.models.Student;
import ru.surantaev.jwt_security.repo.GroupRepository;
import ru.surantaev.jwt_security.repo.StudentRepository;
import ru.surantaev.jwt_security.repo.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByFirstName(String name) {
        List<Student> students = studentRepository.findStudentsByFirstName(name);
        return students;
    }

    public Long countStudent(){
        Long count = studentRepository.count();
        return count;
    }

}
