package com.ems.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryDto {
	
    @NotNull(message="SalID is required field")
	private Integer salId;	
	private Set<EmployeesDto> employees;	
	private Integer sal;	
	private Date date;

	public Integer getSalId() {
		return salId;
	}

	public void setSalId(Integer salId) {
		this.salId = salId;
	}

	public Set<EmployeesDto> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeesDto> employees) {
		this.employees = employees;
	}

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SalaryDto(@NotNull(message = "SalID is required field") Integer salId, Set<EmployeesDto> employees,
			Integer sal, Date date) {
		super();
		this.salId = salId;
		this.employees = employees;
		this.sal = sal;
		this.date = date;
	}

	public SalaryDto() {
		
	}

}
