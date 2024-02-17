package com.tdksoft.batch.student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue
    private UUID studentId;

    private String firstName;

    private  String lastName;

    private String age;

    public Student(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
