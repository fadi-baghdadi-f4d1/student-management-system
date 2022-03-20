package com.fadiBaghdadi.studentManagementSystem.controllers;

import com.fadiBaghdadi.studentManagementSystem.services.StudentService;
import com.fadiBaghdadi.studentManagementSystem.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    public ResponseEntity<?> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable final String studentId) {
        return studentService.getStudent(studentId);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable final String studentId) {
        return studentService.deleteStudent(studentId);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable String studentId) {
        return studentService.updateStudent(student, studentId);
    }
}

