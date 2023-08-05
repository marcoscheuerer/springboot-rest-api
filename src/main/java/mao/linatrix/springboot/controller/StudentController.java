package mao.linatrix.springboot.controller;

import mao.linatrix.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    // HTTP GET Request
    // http://localhost:8181/student
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(1, "Ramshe", "Fadatare");

        return student;
    }

}
