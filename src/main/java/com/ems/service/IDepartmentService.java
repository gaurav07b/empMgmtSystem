package com.ems.service;

import java.util.List;

import com.ems.dto.DepartmentsDto;

public interface IDepartmentService {
	
	List<DepartmentsDto> getAllDept();
	String deleteOneDept(int deptId);
	DepartmentsDto findOneDept(int deptId);
	DepartmentsDto addNewDept(DepartmentsDto newDto);

}
