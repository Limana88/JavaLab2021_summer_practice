package ru.itis.Repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.InterfacesRepo.LessonRepository;
import ru.itis.models.Course;
import ru.itis.models.Lesson;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonRepositoryImpl implements LessonRepository {

    //language=SQL
    private static final String SQL_SELECT_FIND_BY_ID = "select * from lesson where id = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into lesson(name, day_of_week, time) values (?, ? , ?)";

    //language=SQL
    private static final String SQL_SELECT_FIND_BY_NAME = "select * from lesson where name = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_LESSON_WITH_COURSE = "select *,  c.id as course_id from lesson l left join +" +
             " course c on c.id  = l.course_id order by l.id";

    private final JdbcTemplate jdbcTemplate;

    public LessonRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Lesson> lessonRawMapper = (row, rowNumber) -> {
        int id = row.getInt("id");
        String name = row.getString("name");
        String dayOfWeek = row.getString("day_of_week");
        String time = row.getString("time");

        Lesson lesson = new Lesson(id, name, dayOfWeek, time);
        return lesson;
    };

    @Override
    public Optional<Lesson> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_FIND_BY_ID, lessonRawMapper, id));
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, lesson.getName());
            statement.setString(2, String.valueOf(lesson.getDayOfWeek()));
            statement.setString(3,String.valueOf(lesson.getTime()));
            return statement;
        }, keyHolder);

        lesson.setId(keyHolder.getKey().intValue());
    }


    @Override
    public List<Lesson> findByName(String searchName) {
        return jdbcTemplate.query(SQL_SELECT_FIND_BY_NAME, lessonRawMapper, searchName);
    }

    private final ResultSetExtractor<List<Lesson>> lessonResultExtractor = (resultSet -> {
        List<Lesson> list = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String dayOfWeek = resultSet.getString("day_of_week");
            String time = resultSet.getString("time");

            Lesson lesson = new Lesson(id, name,dayOfWeek, time);

            int idCourse = resultSet.getInt("course_id");
            String nameCourse = resultSet.getString("c.name");
            String dateStart = resultSet.getString("date_start");
            String dateEnd = resultSet.getString("date_end");

            lesson.setCourse(new Course(idCourse, nameCourse, dateStart, dateEnd));
            list.add(lesson);
        }
        return list;
    });
    @Override
    public List<Lesson> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LESSON_WITH_COURSE, lessonResultExtractor);
    }
}
