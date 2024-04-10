package com.management.student.service;

import com.management.student.entity.Student;
import com.management.student.exception.ResourceNotFoundException;
import com.management.student.exception.StudentException;
import com.management.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

	@InjectMocks
	StudentService studentService;

	@Mock
	StudentRepository studentRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave() throws StudentException {
		Student student = new Student();
		when(studentRepository.save(any(Student.class))).thenReturn(student);
		Student response = studentService.save(student);
		assertEquals(student, response);
	}

	@Test
	public void testGetAll() {
		List<Student> students = Arrays.asList(new Student(), new Student());
		when(studentRepository.findAll()).thenReturn(students);
		List<Student> response = studentService.getAll();
		assertEquals(2, response.size());
	}

	@Test
	public void testGetAllEmpty() {
		when(studentRepository.findAll()).thenReturn(Arrays.asList());
		assertThrows(StudentException.class, () -> studentService.getAll());
	}

	@Test
	public void testGet() {
		Student student = new Student();
		when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
		Optional<Student> response = studentService.get(1L);
		assertTrue(response.isPresent());
		assertEquals(student, response.get());
	}

	@Test
	public void testDelete() throws StudentException {
		Student student = new Student();
		when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
		doNothing().when(studentRepository).deleteById(anyLong());
		assertDoesNotThrow(() -> studentService.delete(1L));
	}

	@Test
	public void testDeleteNotFound() {
		when (studentRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows (StudentException.class, () -> studentService.delete(1L));
	}

	@Test
	public void testInactivateStudent() {
		Student student= new Student();
		when (studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
		when(studentRepository.save(any(Student.class))).thenReturn(student);
		Student response = studentService.inactivateStudent(1L);
		assertFalse(response.getActive());
	}

	@Test
	public void testInactivateStudentNotFound() {
		when(studentRepository. findById(anyLong())).thenReturn(Optional.empty());
		assertThrows (ResourceNotFoundException.class, () -> studentService.inactivateStudent(1L));
	}

	@Test
	public void testUpdateStudent() throws ResourceNotFoundException {
		Student student = new Student();
		when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
		when(studentRepository.save(any(Student.class))).thenReturn(student);
		Student response = studentService.updateStudent(1L, student);
		assertEquals(student, response);
	}

	@Test
	public void testUpdateStudentNotFound() {
		Student student = new Student();
		when(studentRepository. findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> studentService.updateStudent(1L, student));
	}
}

