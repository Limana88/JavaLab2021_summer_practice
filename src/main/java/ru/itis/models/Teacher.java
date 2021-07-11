package ru.itis.models;

public class Teacher extends Human{
    private Integer experience;
    private Course course;

    public Teacher(String first_name, String last_name, Integer experience) {
        super(first_name, last_name);
        this.experience = experience;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                "experience=" + experience +
                "Course=" + course +
                '}';
    }
}
