package com.ems.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentsDto {
	
    @NotNull(message = "DeptID is required field")
	private Integer deptId;
	private String deptName;
	private String location;
    private Set<EmployeesDto> employees;

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

	public Set<EmployeesDto> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeesDto> employees) {
		this.employees = employees;
	}

	public DepartmentsDto(Integer deptId, String deptName, String location, Set<EmployeesDto> employees) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.location = location;
		this.employees = employees;
	}

	public DepartmentsDto() {
		
	}

}
