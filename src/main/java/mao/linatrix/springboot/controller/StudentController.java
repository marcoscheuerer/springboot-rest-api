package mao.linatrix.springboot.controller;

import jakarta.annotation.PostConstruct;
import mao.linatrix.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students;

    @GetMapping("clear_list")
    @PostConstruct
    public ResponseEntity<String> loadData() {
        students = new ArrayList<>();
        students.add(new Student(1, "Lassmi", "Randa"));
        students.add(new Student(2, "Dennsie", "Willja"));
        students.add(new Student(3, "Steve", "Earkel"));
        students.add(new Student(4, "Sandra", "Bullseye"));
        students.add(new Student(5, "Hulk", "Hogan"));

        //return new ResponseEntity<>("List reloaded!", HttpStatus.OK);
        return ResponseEntity.ok().header("custom-header", "reloaded").body("List reloaded!");
    }

    // HTTP GET Request
    // http://localhost:8181/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Mampfred", "Essich");

        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok(student);
    }

    // http://localhost:8181/students
    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents() {
        //return new ResponseEntity<>(students, HttpStatus.OK);
        return ResponseEntity.ok(students);
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8181/students/1
    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int studentId) {
        //return new ResponseEntity<>(new Student(studentId, "Marco", "Engel"), HttpStatus.OK);
        return ResponseEntity.ok(new Student(studentId, "Marco", "Engel"));
    }

    // http://localhost:8181/students/666/Marco/Teufel
    @GetMapping("students/{studentId}/{firstName}/{lastName}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId,
                                  @PathVariable("firstName") String studentFirstName,
                                  @PathVariable("lastName") String studentLastName) {
        //return new ResponseEntity<>(new Student(studentId, studentFirstName, studentLastName), HttpStatus.OK);
        Student student = new Student(studentId, studentFirstName, studentLastName);
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8181/students/query1?id=1
    @GetMapping("students/query1")
    public ResponseEntity<Student> getStudentByIdParam(@RequestParam("id") int studentId) {
        //return new ResponseEntity<>(students.get(studentId), HttpStatus.OK);
        return ResponseEntity.ok(students.get(studentId));
    }

    // http://localhost:8181/students/query2?id=2&fname=Lena&lname=Engel
    @GetMapping("students/query2")
    public ResponseEntity<Student> getStudentByAllParam(@RequestParam("id") int studentId,
                                        @RequestParam("fname") String studentFirstName,
                                        @RequestParam("lname") String studentLastName) {
        //return new ResponseEntity<>(new Student(studentId, studentFirstName, studentLastName), HttpStatus.OK);
        return ResponseEntity.ok(new Student(studentId, studentFirstName, studentLastName));
    }

    // Spring Boot REST API that handles HTTP POST Request - creating new resource
    // @PostMapping (repsonsible for mapping HTTP POST request onto
    //               specific handler method)
    // @RequestBody (responsible for retrieving the HTTP request body and
    //               automatically converting it (e.g. JSON) to the Java object)
    @PostMapping("students/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println("Student ID       : " + student.getId());
        System.out.println("Student Firstname: " + student.getFirstName());
        System.out.println("Student Lastname : " + student.getLastName());

        //return new ResponseEntity<>(student, HttpStatus.CREATED);
        return ResponseEntity.created(URI.create("students/create")).build();
    }

    // Spring Boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("students/{id}/update")
    public ResponseEntity<List<Student>> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        student.setId(studentId);

        students.set(studentId - 1, student);

        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        //return new ResponseEntity<>(students, HttpStatus.CREATED);
        return ResponseEntity.created(URI.create(String.format("students/%d/update", studentId))).build();
    }

    // Spring Boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("students/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        int id = students.get(studentId - 1).getId();
        String lastName = students.get(studentId - 1).getLastName();
        String firstName = students.get(studentId -1).getFirstName();

        students.remove(studentId - 1);

        String result = "Student at List Pos. " + studentId + " (ID " + id + ": " + lastName + ", " + firstName + ") deleted successfully";

        //return new ResponseEntity<>(result, HttpStatus.OK);
        return ResponseEntity.ok(result);
    }

}
