package com.example.test123;

public class Student {
    Long rollno;
    String name;
    Long marks;

    public Long getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public Long getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "\t { " +
                "\t = " + rollno +
                ", \t = '" + name + '\'' +
                ", \t = " + marks +
                "}";
    }

    public Student(Long rollno, String name, Long marks) {
        this.rollno = rollno;
        this.name = name;
        this.marks = marks;
    }
}
