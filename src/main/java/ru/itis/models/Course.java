package ru.itis.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Course {
    private Integer id;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private List<Student> studentList;
    private Teacher teacher;

    private static  String [] patterns = new String[] {
            "dd-MM-yy",
            "dd-MM-yyyy",
            "MM-dd-yyyy",
            "yyyy-MM-dd",
            "yyyyMMdd",
            "yyyy-MM",
            "EEE, dd MMM yyyy"
    };
    private static String patterForWriteIntroDB = "dd-MM-yyyy";

    public Course(Integer id, String name, String dateStart, String dateEnd) {
        this.id = id;
        this.name = name;
        this.dateStart = parseDate(dateStart);
        this.dateEnd = parseDate(dateEnd);
    }

    public Course(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String name, String dateStart,String dateEnd) {
        this.name = name;
        this.dateStart = parseDate(dateStart);
        this.dateEnd = parseDate(dateEnd);
    }

    public Integer getId() {
        return id;
    }

    private static Date parseDate(String currentDate) {
        Date date = null;

        try {
            for (String pattern : patterns) {
                date = new SimpleDateFormat(pattern).parse(currentDate);
                break;
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateStart() {
        return new SimpleDateFormat(patterForWriteIntroDB).format(dateStart);
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return new SimpleDateFormat(patterForWriteIntroDB).format(dateEnd);
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateStart=" + getDateStart() +
                ", dateEnd=" + getDateEnd() +
                ", studentList=" + studentList +
                ", teacher=" + teacher +
                '}';
    }
}
