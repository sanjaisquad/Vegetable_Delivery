package com.student.student;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name="student")
@Table(name="student",
       uniqueConstraints = {
        @UniqueConstraint(name="student_email_unique",columnNames="Emaill")
       })
public class Student {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",

            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private int ID;
    @Column(
            name="name",
            nullable = false
    )
    private String Name;
    @Column(
            name="DOB",
            nullable = false
    )
    private LocalDate DoB;
    @Column(
            name="Emaill",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    public Student(int ID, String name, LocalDate doB, String email) {
        this.ID = ID;
        Name = name;
        DoB = doB;
        this.email = email;
    }

    public Student(String name, LocalDate doB, String email) {
        Name = name;
        DoB = doB;
        this.email= email;
    }
    public Student(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate doB) {
        DoB = doB;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
