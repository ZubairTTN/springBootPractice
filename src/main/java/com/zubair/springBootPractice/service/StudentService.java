package com.zubair.springBootPractice.service;

import com.zubair.springBootPractice.entity.Department;
import com.zubair.springBootPractice.entity.Student;
import com.zubair.springBootPractice.repository.DepartmentRepository;
import com.zubair.springBootPractice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository)
    {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

//    Create Student Entry using studentRepository
    public Student createStudent(Student studentEntry)
    {
        System.out.println("Inside Student Service");
        Department department = resolveDepartment(studentEntry);
        if(department == null)
        {
            return null;
        }
        studentEntry.setDepartment(department);
        studentEntry.setDeleted(false);
        Student studentResponse = studentRepository.save(studentEntry);
        System.out.println("Exiting Student Service");
        return studentResponse;
    }

//    get student entry
    public Student getStudent(Long id)
    {
        Optional<Student> studentResponse =  studentRepository.findByIdAndDeletedIsFalse(id);
        if(studentResponse.isPresent())
        {
            return studentResponse.get();
        }
        else {
            return null;
        }
    }

//    get all student entries
    public List<Student> getAllStudent()
    {
        List<Student> studentsList = studentRepository.findAllByDeletedIsFalse();
        return studentsList;
    }

//    update Student Entry
    public Student updateStudent(Long id, Student student)
    {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty())
        {
            return null;
        }
        else {
            Student studentToSave = existingStudent.get();
            studentToSave.setName(student.getName());
            studentToSave.setAge(student.getAge());
            studentToSave.setEmail(student.getEmail());
            studentToSave.setSubject(student.getSubject());
            studentToSave.setRollNo(student.getRollNo());
            studentToSave.setDeleted(false);
            if(student.getDepartment() != null)
            {
                Department department = resolveDepartment(student);
                if(department == null)
                {
                    return null;
                }
                studentToSave.setDepartment(department);
            }
            return studentRepository.save(studentToSave);
        }
    }

    // Permanently delete an active student
    public Boolean deleteStudent(Long id) {
        Optional<Student> existingStudent =
                studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()) {
            return false;
        }

        studentRepository.delete(existingStudent.get());
        return true;
    }

//    delete all entries
    public Boolean deleteAll()
    {
        try {
            studentRepository.deleteAll();
            return true;
    } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
}

//    delete entry Softly
    public Boolean deleteStudentSoftly(Long id)
    {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty())
        {
            return false;
        }
        else {
            Student studentToSave = existingStudent.get();
            studentToSave.setDeleted(true);
            studentRepository.save(studentToSave);
            return true;
        }
    }

    private Department resolveDepartment(Student student)
    {
        if(student.getDepartment() == null || student.getDepartment().getId() == null)
        {
            return null;
        }
        return departmentRepository.findById(student.getDepartment().getId()).orElse(null);
    }

}
