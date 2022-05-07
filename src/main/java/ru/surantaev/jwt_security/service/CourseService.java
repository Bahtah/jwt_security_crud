package ru.surantaev.jwt_security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.surantaev.jwt_security.entity.models.Course;
import ru.surantaev.jwt_security.repo.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
