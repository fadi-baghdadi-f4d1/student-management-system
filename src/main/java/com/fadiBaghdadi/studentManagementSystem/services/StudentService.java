package com.fadiBaghdadi.studentManagementSystem.services;

import com.fadiBaghdadi.studentManagementSystem.model.Student;
import com.fadiBaghdadi.studentManagementSystem.rep.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            Student save = this.studentRepository.save(student);
            return ResponseEntity.ok(save);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getStudents() {
        try {
            List<Student> students = this.studentRepository.findAll();
            if (students.isEmpty()) {
                return new ResponseEntity<>("No Students available", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getStudent(@PathVariable final String studentId) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                return ResponseEntity.ok(student);
            }
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteStudent(@PathVariable final String studentId) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                studentRepository.delete(student.get());
                return ResponseEntity.ok("Student " + student.get() + " Successfully deleted!");
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable String studentId) {
        try {
            Optional<Student> st = studentRepository.findById(studentId);
            if (st.isEmpty()) {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }
            st.ifPresent(s -> {
                s.setStudentName(student.getStudentName());
                s.setStudentAge(student.getStudentAge());
                s.setStudentGrade(student.getStudentGrade());
                studentRepository.save(s);
            });
            return new ResponseEntity<>("Student Successfully Updated \n" + student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
