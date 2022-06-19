package dev.spring.p03springintro;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${developer.name}")
    private String developerName;

    /*@Value("${developer.name1:Ali}")
    private String developerName2;*/

    @Value("${developer.email}")
    private String developerMail;

    @Value("${developer.phone}")
    private String developerPhone;




    List<Student> students = new ArrayList<>();

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StringResponse sayHello(@RequestParam(required = false, defaultValue = "Guest") String name, @PathParam("year") int year) {
        logger.debug("Developer name : {} , E-Mail : {}",developerName,developerMail);
        return new StringResponse("Hello " + developerName + ", Phone : " + developerPhone + " from " + year);
    }

    //Error
    /*@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public StringResponse sayHello(@RequestParam(required = false, defaultValue = "Guest") String name, @PathParam("year") int year) {
        return new StringResponse("Hello " + name + " from " + year);
    }*/

    @GetMapping("/hello/{name}/{age}")
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "There is an error during response (Created by Tolga)")
    public String sayMyName(@PathVariable String name, @PathVariable int age) {

        return "Hello " + name + ", " + age;
    }

    @GetMapping("/greeting/{id}")
    public ResponseEntity<String> greeting(@PathVariable int id) {
        if (id > 100) {
            //return ResponseEntity.badRequest().body("Id cannot be greater than 99");

            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Id cannot be greater than 99");
            return new ResponseEntity<>("Id cannot be greater than 99", HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("myCustomHeader", "TestHeader");

        return ResponseEntity.ok().headers(headers).body("Id : " + id);
        /*return new ResponseEntity<>("Id : " + id, headers, HttpStatus.CREATED);*/
    }

    //Error
    /*@GetMapping("/greeting/{id}")
    public ResponseEntity<String> greeting(@RequestHeader("Cookie") String cookie, @PathVariable int id) {
        if (id > 100) {
            //return ResponseEntity.badRequest().body("Id cannot be greater than 99");

            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Id cannot be greater than 99");
            return new ResponseEntity<>("Id cannot be greater than 99", HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("myCustomHeader", "TestHeader");

        return ResponseEntity.ok().headers(headers).body("Id : " + id + ", cookie : " + cookie);
        //return new ResponseEntity<>("Id : " + id + ", cookie : " + cookie, headers, HttpStatus.CREATED);
    }*/

    //Error
    @GetMapping("/custom")
    public void customHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookie = request.getHeader("Cookie");

        response.setHeader("customHeader", "Tolga");
        response.setHeader("cookie", cookie);
        response.setStatus(201);
        response.getWriter().println("Hello world");

    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {


        students.add(new Student(1, "Tolga E", "Ankara"));
        students.add(new Student(2, "Aleyna Kütük", "Denizli"));
        students.add(new Student(3, "Emre Oğuz", "İstanbul"));

        return ResponseEntity.ok().body(students);
    }

    /*@PostMapping("/students")
    public ResponseEntity<List<Student>> addStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.ok().body(students);
    }*/

    @PostMapping("/students")
    public ResponseEntity<List<Student>> addStudent(@RequestBody List<Student> studentList) {
        studentList.stream().forEach(student -> students.add(student));

        return ResponseEntity.ok().body(students);
    }

    @GetMapping("*")
    public ResponseEntity<String> fallBAckMethod() {
        return new ResponseEntity<>("There is no endpoint like that", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/calculate/{operation}")
    public String calculate(@PathVariable String operation, @PathParam("n1") int n1, @PathParam("n2") int n2) {
        if (operation.equals("sum")){
            return "Output --> " + n1 + " + " + n2 + " = " + (n1+n2);
        } else if (operation.equals("diff")){
            return "Output --> " + n1 + " -* " + n2 + " = " + (n1-n2);
        } else {
            return "Error";
        }
    }
}
