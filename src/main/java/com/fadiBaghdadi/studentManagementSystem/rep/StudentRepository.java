package com.fadiBaghdadi.studentManagementSystem.rep;

import com.fadiBaghdadi.studentManagementSystem.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository
        extends MongoRepository<Student, String> {

}