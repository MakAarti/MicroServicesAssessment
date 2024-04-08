package com.management.student.controller;

import com.management.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Long> addStudent(@RequestBody StudentRequest studentRequest) {
        return new ResponseEntity<>(
                studentService.addStudent(studentRequest),
                HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> findAll() {
        System.out.println("User.server.port: " + environment.getProperty("local.server.port"));
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable("id") Long id) {
        System.out.println("User.server.port: " + environment.getProperty("local.server.port"));
        Optional<User> user = userService.get(id);
        if (user.isPresent())
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/site/{siteId}")
    public List<User> findBySite(@PathVariable("siteId") Long siteId) {
        System.out.println("User.server.port: " + environment.getProperty("local.server.port"));
        return userService.getBySiteId(siteId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<User> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        System.out.println("User.server.port: " + environment.getProperty("local.server.port"));
        return userService.getByOrganizationId(organizationId);
    }

}