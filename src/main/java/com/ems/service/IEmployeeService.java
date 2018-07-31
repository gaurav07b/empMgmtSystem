package com.ems.service;

import java.util.List;

import com.ems.dto.EmployeesDto;

public interface IEmployeeService {
	
	List<EmployeesDto> getAllEmployees();
	String deleteOneEmp(int empId);
	EmployeesDto findOneEmp(int empId);
	EmployeesDto addNewEmp(EmployeesDto newDto);
	EmployeesDto updateEmp(EmployeesDto empDto);
	void deleteAllEmp();
	EmployeesDto updateEmpURL(int eId, EmployeesDto empDto);
}
