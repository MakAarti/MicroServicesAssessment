package com.management.student.service;

import com.management.student.exception.ResourceNotFoundException;
import com.management.student.exception.StudentException;
import com.management.student.entity.Student;
import com.management.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) throws StudentException {
        student.setActive(Boolean.TRUE);
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        List<Student> students = studentRepository.findAll();
        if(students.size() == 0)
            throw new StudentException("Students not found...");
        else
            return students;
    }

    public Optional<Student> get(Long id) {
        return studentRepository.findById(id);
    }

    public void delete(Long id) throws StudentException {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent())
        {
            studentRepository.deleteById(id);
        }
        else throw new StudentException("Student does not exist with this Id : " + id);
    }

    public Student inactivateStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));
        student.setActive(false);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));
        student.setName(studentDetails.getName());
        student.setGrade(studentDetails.getGrade());
        student.setMobile(studentDetails.getMobile());
        student.setSchoolName(studentDetails.getSchoolName());
        final Student updatedStudent = studentRepository.save(student);
        return updatedStudent;
    }
}
