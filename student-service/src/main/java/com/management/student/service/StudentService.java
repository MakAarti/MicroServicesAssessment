package com.management.student.service;

import com.management.student.model.Student;
import com.management.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        List<Student> users = studentRepository.findAll();
        return users;
    }

    public Optional<Student> get(Long id) {
        return studentRepository.findById(id);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
