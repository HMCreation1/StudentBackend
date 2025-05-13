package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entity.Student;

public interface Repository extends JpaRepository<Student, Integer>{

}
