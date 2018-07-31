package com.ems.service;

import java.util.List;

import com.ems.dto.JobTypeDto;

public interface IJobTypeService {
	
	List<JobTypeDto> getAllJobType();
	String deleteJobType(int jobId);
	JobTypeDto addNewJob(JobTypeDto newDto);
	
}
