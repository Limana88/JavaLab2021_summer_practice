package ru.itis.InterfacesRepo;

import ru.itis.models.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {

    Optional<Lesson> findById(Integer id);
    void save(Lesson lesson);
    List<Lesson> findByName(String searchName);
    List<Lesson> findAll();

}
