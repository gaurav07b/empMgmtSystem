package com.ems.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto {
	
    @NotNull(message="ProjID is required field")
	private Integer pId;
	private String pName;
	private String pLocation;	
    private List<EmployeesDto> employees;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpLocation() {
		return pLocation;
	}

	public void setpLocation(String pLocation) {
		this.pLocation = pLocation;
	}

	public List<EmployeesDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeesDto> employees) {
		this.employees = employees;
	}

	public ProjectDto(Integer pId, String pName, String pLocation, List<EmployeesDto> employees) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pLocation = pLocation;
		this.employees = employees;
	}

	public ProjectDto() {
		
	}
}
