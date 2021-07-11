package ru.itis.Repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.InterfacesRepo.CourseRepository;
import ru.itis.models.Course;
import ru.itis.models.Student;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.*;

public class CourseRepositoryImpl implements CourseRepository {

    //language=SQL
    private  static final String SQL_SELECT_FIND_BY_ID = "select * from course where id = ?";

    //language=SQL
    private static final String SQL_SELECT_FIND_BY_NAME_COURSE = "select * from course where course.name = ?";

    //language=SQL
    private static final String SQL_INSERT ="insert into course (name, date_start, date_end) values (?, ? , ?)";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from course where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_COURSE_BY_DATE_START = "update course set date_start = ? where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_COURSE_BY_DATE_END = "update course set date_end = ? where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_COURSES_WITH_STUDENTS = "select *,  s.id as student_id from course c left join student s on c.id  = s.course_id order by c.id";

    private  JdbcTemplate jdbcTemplate;

    public CourseRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Course> courseRowMapper = (row, rowNumber )-> {
        int id = row.getInt("id");
        String name = row.getString("name");
        String dataStart = row.getString("date_start");
        String dateEnd = row.getString("date_end");
        Course course = new Course(id, name, dataStart, dateEnd);
        course.setStudentList(new ArrayList<>());
        return course;
    };


    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, course.getName());
            statement.setString(2, String.valueOf(course.getDateStart()));
            statement.setString(3,String.valueOf(course.getDateEnd()));
            return statement;
        }, keyHolder);

        course.setId(keyHolder.getKey().intValue());
    }

    public long update(String date, String time, Integer id) {
        long countChangedRows = 0;
        if (time.toLowerCase().equals("start")) 
            countChangedRows = jdbcTemplate.update(SQL_UPDATE_COURSE_BY_DATE_START, date, id);
        else
            if (time.toLowerCase().equals("end"))
                countChangedRows = jdbcTemplate.update(SQL_UPDATE_COURSE_BY_DATE_END, date, id);
        return countChangedRows;
    }

    public void delete(Integer id) {
        try {
            jdbcTemplate.update(SQL_DELETE_BY_ID, id);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }



    public Optional<Course> findById(Integer id) {
       try{
           return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_FIND_BY_ID, courseRowMapper, id));
       } catch (EmptyResultDataAccessException e) {
           return Optional.empty();
       }

    }

    public List<Course> findByName(String searchName) {
        return jdbcTemplate.query(SQL_SELECT_FIND_BY_NAME_COURSE, courseRowMapper, searchName.toLowerCase());
    }


    private final ResultSetExtractor<List<Course>> courseResultSetExtractor = resultSet -> {

        Map<Integer, Course> map = new LinkedHashMap<>();
        Course course;
        while(resultSet.next()) {

            int id = resultSet.getInt("id");
            course = map.get(id);

            if (course == null) {
                String name = resultSet.getString("name");
                String dataStart = resultSet.getString("date_start");
                String dateEnd = resultSet.getString("date_end");
                course = new Course(id, name, dataStart, dateEnd);
                course.setStudentList(new ArrayList<>());
                map.put(id, course);
            }

            int studentId = resultSet.getInt("student_id");
            if (studentId > 0) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int groupNumber = resultSet.getInt("group_number");

                Student student = new Student(studentId, firstName, lastName, groupNumber);

                course.getStudentList().add(student);
            }
        }
        return new ArrayList<>(map.values());
    };
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_COURSES_WITH_STUDENTS, courseResultSetExtractor);
    }
}
