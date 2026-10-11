package com.zubair.springBootPractice.service;

import com.zubair.springBootPractice.entity.Department;
import com.zubair.springBootPractice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository)
    {
        this.departmentRepository = departmentRepository;
    }

//    Create Department Entry
    public Department createDepartment(Department departmentEntry)
    {
        return departmentRepository.save(departmentEntry);
    }

//    get department entry
    public Department getDepartment(Long id)
    {
        Optional<Department> departmentResponse = departmentRepository.findById(id);
        if(departmentResponse.isPresent())
        {
            return departmentResponse.get();
        }
        else {
            return null;
        }
    }

//    get all department entries
    public List<Department> getAllDepartment()
    {
        return departmentRepository.findAll();
    }

//    update Department Entry
    public Department updateDepartment(Long id, Department department)
    {
        Optional<Department> existingDepartment = departmentRepository.findById(id);
        if(existingDepartment.isEmpty())
        {
            return null;
        }
        else {
            Department departmentToSave = existingDepartment.get();
            departmentToSave.setName(department.getName());
            return departmentRepository.save(departmentToSave);
        }
    }

//    delete department entry
    public Boolean deleteDepartment(Long id)
    {
        boolean departmentToDelete = departmentRepository.existsById(id);
        if(!departmentToDelete)
        {
            return false;
        }
        else {
            departmentRepository.deleteById(id);
            return true;
        }
    }

//    delete all entries
    public Boolean deleteAll()
    {
        try {
            departmentRepository.deleteAll();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
