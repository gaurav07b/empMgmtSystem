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
@Table(name = "departments")
public class Departments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dept_id")
	private Integer deptId;
	
	@Column(name="dept_name")
	private String deptName;
	
	@Column(name="dept_location")
	private String location;
	
	@OneToMany(mappedBy="department")
    private Set<Employees> employees;
	
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employees> employees) {
		this.employees = employees;
	}

	public Departments(Integer deptId, String deptName, String location, Set<Employees> employees) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.location = location;
		this.employees = employees;
	}

	public Departments() {
		
	}

}
