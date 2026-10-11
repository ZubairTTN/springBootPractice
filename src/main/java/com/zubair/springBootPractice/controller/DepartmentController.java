package com.zubair.springBootPractice.controller;

import com.zubair.springBootPractice.entity.Department;
import com.zubair.springBootPractice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

//    Create Method
    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department departmentEntry)
    {
        Department createdDepartment = departmentService.createDepartment(departmentEntry);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDepartment);
    }

//    Read One Method
    @GetMapping("/get")
    public ResponseEntity<Department> getDepartment(@RequestParam Long id)
    {
        Department departmentResponse = departmentService.getDepartment(id);
        if(departmentResponse == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(departmentResponse);
        }
    }

//    Get all department entries
    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAllDepartment()
    {
        List<Department> departmentList = departmentService.getAllDepartment();
        if(departmentList.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(departmentList);
        }
    }

//    Update Entry
    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestParam Long id, @RequestBody Department department)
    {
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        if(updatedDepartment == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(updatedDepartment);
        }
    }

//    Delete department entry
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDepartment(@RequestParam Long id)
    {
        Boolean isDeleted = departmentService.deleteDepartment(id);
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
        Boolean isDeletedAll = departmentService.deleteAll();
        if(!isDeletedAll)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found to delete...");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("Records deleted Successfully...");
        }
    }
}
