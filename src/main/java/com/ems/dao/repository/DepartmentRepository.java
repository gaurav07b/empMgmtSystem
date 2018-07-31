package com.ems.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.dao.entity.Departments;

@Repository("deptRepository")
public interface DepartmentRepository extends JpaRepository<Departments, Integer> {

}
