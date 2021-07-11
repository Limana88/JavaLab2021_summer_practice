package ru.itis.models;

public abstract class Human {

    private String first_name;
    private String last_name;
    private Integer id;

    public Human(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Human(Integer id, String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", id=" + id;
    }
}
