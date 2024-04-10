package com.management.student.controller;

import com.management.student.entity.Student;
import com.management.student.exception.ResourceNotFoundException;
import com.management.student.exception.StudentException;
import com.management.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(
                studentService.save(student),
                HttpStatus.CREATED);
    }

    @Tag(name="get", description = "GET methods of Student APIs")
    @GetMapping("/student/{id}")
    public ResponseEntity<?> findStudentById(@Parameter(
            description = "ID of student to be retrieved",
            required = true) @PathVariable("id") Long id) throws StudentException {
        Optional<Student> student = studentService.get(id);
        if(student.isPresent())
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Student not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Tag(name="get", description = "GET methods of Student APIs")
    @GetMapping("/students")
    public List<Student> findAll() throws StudentException{
        return studentService.getAll();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) throws StudentException {
        //studentService.delete(id);
        studentService.inactivateStudent(id);
        return new ResponseEntity<String>("Student deleted", HttpStatus.OK);
    }

    @Operation(summary = "Update a student",
                description = "Update an existing student. The response is updated Student object with name, grade, mobile and school name.")
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }


}