package com.ems.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ems.dao.entity.JobType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeesDto {

    @NotNull(message="EmpID is required field")
	private Integer empId;
    @NotNull(message="Name is required")
	private String name;
	private Character gender;
	@Size(min=10, max=12)
	private Long contact;
	@Email(message="Email must be in abc@xyz format")
	private String email;
	private JobType jobType;
	@Min(18)
	private Integer age;
	private Date joinDate;
	private DepartmentsDto department;
    List<ProjectDto> projects = new ArrayList<>();
	private SalaryDto salary;
	
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public DepartmentsDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentsDto department) {
		this.department = department;
	}

	public SalaryDto getSalary() {
		return salary;
	}

	public void setSalary(SalaryDto salary) {
		this.salary = salary;
	}

	public List<ProjectDto> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDto> projects) {
		this.projects = projects;
	}

	public EmployeesDto(Integer empId, String name, Character gender, Long contact, String email, JobType jobType,
			Integer age, Date joinDate, DepartmentsDto department, List<ProjectDto> projects, SalaryDto salary) {
		super();
		this.empId = empId;
		this.name = name;
		this.gender = gender;
		this.contact = contact;
		this.email = email;
		this.jobType = jobType;
		this.age = age;
		this.joinDate = joinDate;
		this.department = department;
		this.projects = projects;
		this.salary = salary;
	}

	public EmployeesDto() {
		
	}

}
