package com.ems.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.dao.entity.Project;

@Repository("projRepository")
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
