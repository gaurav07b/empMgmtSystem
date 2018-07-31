package com.ems.dao.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="job_type")
public class JobType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="JobTypeID")
	private Integer jobId;
	
	@Column(name="job_type")
	private String jobType;
	
	@OneToMany(mappedBy="jobType")
    private Set<Employees> employees;

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

	public Set<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employees> employees) {
		this.employees = employees;
	}

	public JobType(Integer jobId, String jobType, Set<Employees> employees) {
		super();
		this.jobId = jobId;
		this.jobType = jobType;
		this.employees = employees;
	}

	public JobType() {
		super();
	}

}
