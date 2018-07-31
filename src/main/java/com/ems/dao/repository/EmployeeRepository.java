package com.ems.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.dao.entity.Employees;

@Repository("empRepository")
public interface EmployeeRepository extends JpaRepository<Employees, Integer>{

}
