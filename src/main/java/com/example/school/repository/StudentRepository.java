// Write your code here

package com.example.school.repository;

import java.util.*;

import com.example.school.model.Student;

public interface StudentRepository {
    ArrayList<Student> getStudents();

    Student getStudentbyId(int studentId);

    Student addStudent(Student Tod);

    String addManyStudent(ArrayList<Student> stud);

    Student updateStudent(int studentId, Student so);

    void delStudent(int studentId);
}