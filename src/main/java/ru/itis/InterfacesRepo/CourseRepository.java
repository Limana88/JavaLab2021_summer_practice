package ru.itis.InterfacesRepo;

import ru.itis.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    void save(Course course);
    long update(String date, String time, Integer id);
    void delete(Integer id);
    Optional<Course> findById(Integer id);
    List<Course> findByName(String searchName);
    List<Course> findAll();
}
