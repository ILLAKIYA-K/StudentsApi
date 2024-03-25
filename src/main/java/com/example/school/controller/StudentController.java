/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

package com.example.school.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;

@RestController
public class StudentController {
    @Autowired
    public StudentH2Service service;

    @DeleteMapping("/students/{studentId}")
    public void delStudent(@PathVariable("studentId") int studentId) {
        service.delStudent(studentId);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student so) {
        return service.updateStudent(studentId, so);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student Tod) {
        return service.addStudent(Tod);
    }

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return service.getStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentbyId(@PathVariable("studentId") int studentId) {
        return service.getStudentbyId(studentId);
    }

    @PostMapping("/students/bulk")
    String addManyStudent(@RequestBody ArrayList<Student> stud) {
        return service.addManyStudent(stud);
    }

}
