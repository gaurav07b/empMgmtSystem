package com.ems.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobTypeDto {
	
    @NotNull(message="JobID is required field")
	private Integer jobId;	
	private String jobType;	
    private Set<EmployeesDto> employees;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	public Set<EmployeesDto> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeesDto> employees) {
		this.employees = employees;
	}

	public JobTypeDto(Integer jobId, String jobType, Set<EmployeesDto> employees) {
		super();
		this.jobId = jobId;
		this.jobType = jobType;
		this.employees = employees;
	}

	public JobTypeDto() {
		super();
	}

}
