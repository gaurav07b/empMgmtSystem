package com.ems.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.dao.entity.JobType;

@Repository("jobRepository")
public interface JobTypeRepository extends JpaRepository<JobType, Integer> {

}
