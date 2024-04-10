package com.management.student.controller;

import com.management.student.entity.Student;
import com.management.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentControllerTest {

	@InjectMocks
	StudentController studentController;

	@Mock
	StudentService studentService;

	@BeforeEach
	public void init() { MockitoAnnotations.initMocks(this); }

	@Test
	public void testAddStudent() {
		Student student = new Student();
		when(studentService.save(any(Student.class))).thenReturn(student);
		ResponseEntity<Student> response = studentController.addStudent(student);
		assertEquals(201, response.getStatusCodeValue());
	}

	@Test
	public void testFindAll(){
		List<Student> students = Arrays.asList(new Student(), new Student());
		when(studentService.getAll()).thenReturn(students);
		List<Student> response = studentController.findAll();
		assertEquals(2, response.size());
	}

	@Test
	public void testFindStudentById() {
		Student student = new Student();
		when(studentService.get(anyLong())).thenReturn(Optional.of(student));
		ResponseEntity<?> response = studentController.findStudentById(1L);
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testDeletestudent() {
		Student student = new Student();
		student.setActive(Boolean.FALSE);
		when (studentService.inactivateStudent(anyLong())).thenReturn(student);
		ResponseEntity<String> response = studentController.deleteStudent(1L);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void testUpdateStudent() {
		Student student = new Student();
		when(studentService.updateStudent(anyLong(), any(Student.class))).thenReturn(student);
		ResponseEntity<Student> response= studentController.updateStudent(1L, student);
		assertEquals(200, response.getStatusCodeValue());
	}
}
