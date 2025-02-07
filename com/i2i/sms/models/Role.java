package com.i2i.sms.models;

import java.util.Set;

public class Role {
    private int id;
    private String name;
    private Set<Student> students;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nRole ").append(id).append(": ").append(name);
        return stringBuilder.toString();
    }
}
