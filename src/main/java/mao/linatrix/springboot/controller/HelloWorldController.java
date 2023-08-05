package mao.linatrix.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {

    // HTTP GET Request
    // http://localhost:8080/hello

    @GetMapping("hello")
    public String sayHello() {
        return "Hello World!";
    }

}
