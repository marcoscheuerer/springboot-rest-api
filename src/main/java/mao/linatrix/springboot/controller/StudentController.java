package mao.linatrix.springboot.controller;

import mao.linatrix.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // HTTP GET Request
    // http://localhost:8181/student
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(1, "Mampfred", "Essich");

        return student;
    }

    // http://localhost:8181/students
    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Lassmi", "Randa"));
        students.add(new Student(2, "Dennsie", "Willja"));
        students.add(new Student(3, "Steve", "Earkel"));
        students.add(new Student(4, "Sandra", "Bullseye"));
        students.add(new Student(5, "Hulk", "Hogan"));

        return students;
    }

}
