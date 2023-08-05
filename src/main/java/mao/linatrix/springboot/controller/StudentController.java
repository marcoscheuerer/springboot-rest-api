package mao.linatrix.springboot.controller;

import mao.linatrix.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8181/students/1
    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable("id") int studentId) {
        return new Student(studentId, "Marco", "Engel");
    }

    // http://localhost:8181/students/666/Marco/Teufel
    @GetMapping("students/{studentId}/{firstName}/{lastName}")
    public Student getStudentById(@PathVariable int studentId,
                                  @PathVariable("firstName") String studentFirstName,
                                  @PathVariable("lastName") String studentLastName) {
        return new Student(studentId, studentFirstName, studentLastName);
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8181/students/query1?id=1
    @GetMapping("students/query1")
    public Student getStudentByIdParam(@RequestParam("id") int studentId) {
        return new Student(studentId, "Marco", "Scheuerer");
    }

    // http://localhost:8181/students/query2?id=2&fname=Lena&lname=Engel
    @GetMapping("students/query2")
    public Student getStudentByAllParam(@RequestParam("id") int studentId,
                                        @RequestParam("fname") String studentFirstName,
                                        @RequestParam("lname") String studentLastName) {
        return new Student(studentId, studentFirstName, studentLastName);
    }

}
