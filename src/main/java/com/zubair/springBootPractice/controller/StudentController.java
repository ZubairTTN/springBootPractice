package com.zubair.springBootPractice.controller;

import com.zubair.springBootPractice.entity.Student;
import com.zubair.springBootPractice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//        System.out.println("Inside Student Controller");
       Student createdStudent = studentService.createStudent(studentEntry);
//        System.out.println("Student Entry is created...");
//        System.out.println("Exiting Student Controller");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

//    Read One Method
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id)
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
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student)
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id)
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

}
