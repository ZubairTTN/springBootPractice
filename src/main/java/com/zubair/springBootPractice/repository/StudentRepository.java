package com.zubair.springBootPractice.repository;

import com.zubair.springBootPractice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByDeletedIsFalse();
    Optional<Student> findByIdAndDeletedIsFalse(Long id);

}
