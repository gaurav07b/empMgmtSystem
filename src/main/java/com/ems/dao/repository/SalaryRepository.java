package com.ems.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.dao.entity.Salary;

@Repository("salRepository")
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

}
