package com.ems.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="employees")

public class Employees {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="emp_id")
	private Integer empid;
	
	@Column(name="emp_name")
	private String name;
	
	@Column(name="emp_gender")
	private Character gender;
	
	@Column(unique=true, name="emp_contact")
	private Long contact;
	
	@Column(unique=true, name="emp_mail")
	@Email
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="JobTypeID")
	private JobType jobType;
	
	@Column(name="emp_age")
	private Integer age;
	
	@Column(name="DateOfJoining")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date joinDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Dept_ID")
	private Departments department;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "Employee_Project", 
	        joinColumns = { @JoinColumn(name = "employee_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "project_id") }
	    )
	
	    List<Project> projects = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sal_id")
	private Salary salary;
	
	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
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

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Employees(Integer empid, String name, Character gender, Long contact, @Email String email, JobType jobType,
			Integer age, Date joinDate, Departments department, List<Project> projects, Salary salary) {
		super();
		this.empid = empid;
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

	public Employees() {
		
	}
	
}
