/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here

package com.example.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.school.model.StudentRowMapper;

@Service
public class StudentH2Service implements StudentRepository {
  @Autowired
  private JdbcTemplate db;

  @Override
  public void delStudent(int studentId) {
    db.update("delete from Student where studentId = ?", studentId);
  }

  @Override
  public ArrayList<Student> getStudents() {
    List<Student> StudentCollections = db.query("SELECT * from Student", new StudentRowMapper());
    ArrayList<Student> arr = new ArrayList<>(StudentCollections);
    return arr;
  }

  @Override
  public Student addStudent(Student Tod) {
    db.update("insert into Student(studentName, gender, standard) values (?,?,?)",
        Tod.getStudentName(), Tod.getGender(), Tod.getStandard());

    Student savedTod = db.queryForObject(
        "select * from Student where  studentName = ? and gender = ? and standard = ?",
        new StudentRowMapper(), Tod.getStudentName(), Tod.getGender(), Tod.getStandard());

    return savedTod;

  }

  @Override
  public Student getStudentbyId(int studentId) {
    try {
      Student getId = db.queryForObject("SELECT * from Student WHERE studentId = ?", new StudentRowMapper(), studentId);
      return getId;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override

  public Student updateStudent(int studentId, Student so) {

    if (so.getStudentName() != null) {
      db.update("UPDATE Student SET studentName = ? WHERE studentId = ?", so.getStudentName(), studentId);
    }
    if (so.getGender() != null) {
      db.update("UPDATE Student SET gender = ? WHERE studentId = ?", so.getGender(), studentId);
    }
    if (so.getStandard() != 0) {
      db.update("UPDATE Student SET standard = ? WHERE studentId = ?", so.getStandard(), studentId);
    }

    return getStudentbyId(studentId);

  }

  public String addManyStudent(ArrayList<Student> stud) {
    int count = 0;
    for (Student sw : stud) {
      int rowsAff = db.update("insert into Student (studentName, gender, standard) VALUES(?,?,?)", sw.getStudentName(),
          sw.getGender(), sw.getStandard());
      count += rowsAff;
    }
    
    return "Successfully added " + count + " students";

  }
}
