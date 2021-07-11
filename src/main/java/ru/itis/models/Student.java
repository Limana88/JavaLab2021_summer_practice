package ru.itis.models;

public class Student extends Human {

    private Integer groupNumber;


    public Student(String first_name, String last_name, Integer groupNumber) {
        super(first_name, last_name);
        this.groupNumber = groupNumber;
    }


    public Student(Integer id, String first_name, String last_name, Integer groupNumber) {
        super(id, first_name, last_name);
        this.groupNumber = groupNumber;
    }
    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Student{" + super.toString() +
                 "groupNumber=" + groupNumber +
                '}';
    }
}
