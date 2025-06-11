package com.alex.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirstController
{
    public FirstController(StudentRepository repository) {
        this.repository = repository;
    }


    @GetMapping("goodbye")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayGoodbye()
    {
        return "Goodbye from FirstController";

    }

    @GetMapping("hello/{user-name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String helloUserName(@PathVariable("user-name") String userName)
    {
        return "Hello "+userName;
    }

    @GetMapping("goodbye/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String goodbyeUserName(@PathVariable("username")  String username)
    {
        return "Goodbye "+username;
    }

    @GetMapping("/whatup")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String whatup(@RequestParam("username") String userName)
    {
        return "WHAT IS UP  "+userName;
    }

    private final StudentRepository repository;

    @PostMapping("/students")
    public Student post(@RequestBody Student studentObj)
    {
        return repository.save(studentObj);
    }


    @PostMapping("/student/{id}")
    public Student findStudent(@PathVariable Integer id)
    {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/allStudents")
    public List<Student> getAllStudents()
    {
        return repository.findAll();
    }

    public record NameRequest(String name) {}


    @PostMapping("student-name")
    public List<Student> findbyname(@RequestBody NameRequest request)
    {
        return repository.findAllByStudentNameContaining(request.name());
    }

    @PostMapping("add/Students")
    public List<Student> add(@RequestBody List<Student> students)
    {
        return repository.saveAll(students);
    }


    @Autowired
    ApplicantService applicantService;
    @GetMapping("/Applicant")
    public List<Applicant> getallApplicant()
    {
        return applicantService.getAllApplicants();
    }

    @PostMapping("/Applicant")
    public Applicant addApplicant(@RequestBody Applicant applicant)
    {
        return applicantService.addApplicant(applicant);
    }

    @GetMapping("/applicant-partialname")
    public List<Applicant> getByPartialName(@RequestParam("partialName") String partialName)
    {
        return applicantService.findByPartialName(partialName);
    }
}



