package com.zubair.springBootPractice.controller;

import com.zubair.springBootPractice.entity.Student;
import com.zubair.springBootPractice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

//    Create Method
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student studentEntry)
    {
        Student createdStudent = studentService.createStudent(studentEntry);
//        System.out.println("Exiting Student Controller");
        if(createdStudent == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

//    Read One Method
    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id)
    {
        Student studentResponse =  studentService.getStudent(id);
        if(studentResponse == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return  ResponseEntity.status(HttpStatus.OK).body(studentResponse);
        }
    }
    
//    Get all student entries
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent()
    {
        List<Student> studentList = studentService.getAllStudent();
        if(studentList.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        }
    }

//    Update Entry
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id, @RequestBody Student student)
    {
        Student updatedStudent = studentService.updateStudent(id, student);
        if(updatedStudent == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
        }
    }

//    Delete student entry
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id)
    {
        Boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record to be deleted is not found...");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("Record is deleted successfully...");
        }
    }

//    delete all entries
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllEntries()
    {
        Boolean isDeletedAll = studentService.deleteAll();
        if(!isDeletedAll)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found to delete...");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("Records deleted Successfully...");
        }
    }

    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id)
    {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);
        if(!isDeleted)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Required Entry is not found...");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("The Required Entry is Deleted Softly...");
        }
    }

    @GetMapping("/csrf")
    public CsrfToken getToken(CsrfToken csrfToken)
    {
        return csrfToken;
    }

}
