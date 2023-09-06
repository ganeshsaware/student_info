package com.students.info.repository;

import com.students.info.Entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    List<Parent> findBystudentId(long student_id);
}
