package com.ems.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.entity.Departments;
import com.ems.dao.entity.Employees;
import com.ems.dao.repository.DepartmentRepository;
import com.ems.dto.DepartmentsDto;
import com.ems.dto.EmployeesDto;

@Service("deptService")
public class DeptServiceImpl implements IDepartmentService {
	
	@Autowired
	private DepartmentRepository deptRepo;
//================================================GET ALL DEPARTMENTS=======================================================
	@Override
	public List<DepartmentsDto> getAllDept() {
		List<Departments> depts = deptRepo.findAll();
		return deptAssembler(depts);
	}
//======================================================ASSEMBLER===========================================================
	private List<DepartmentsDto> deptAssembler(List<Departments> depts) {
		List<DepartmentsDto> ddto = new ArrayList<>();
		depts.forEach(dept -> {
			DepartmentsDto deptDto = new DepartmentsDto();
			deptDto.setDeptId(dept.getDeptId());
			deptDto.setDeptName(dept.getDeptName());
			deptDto.setLocation(dept.getLocation());
			Set<EmployeesDto> sDto = entToDto(dept.getEmployees());
			deptDto.setEmployees(sDto);
			ddto.add(deptDto);
			
		});
		return ddto;
	}

//=======================================================DELETE============================================================
	
	@Override
	public String deleteOneDept(int deptId) {
		Departments dept = deptRepo.getOne(deptId);
		deptRepo.delete(dept);
		return "Deletion Successful";
	}
	
//===================================================FIND ONE DEPARTMENT===================================================

	@Override
	public DepartmentsDto findOneDept(int deptId) {
		Departments dept = deptRepo.getOne(deptId);
		DepartmentsDto ddto = new DepartmentsDto();
		ddto.setDeptId(dept.getDeptId());
		ddto.setDeptName(dept.getDeptName());
		ddto.setLocation(dept.getLocation());
		Set<EmployeesDto> sDto = entToDto(dept.getEmployees());
		ddto.setEmployees(sDto);
		
		return ddto;
	}

//=======================================ADD NEW DEPARTMENT==============================================================
	
	@Override
	public DepartmentsDto addNewDept(DepartmentsDto newDto) {
		Departments dept = new Departments();
		dept.setDeptName(newDto.getDeptName());
		dept.setLocation(newDto.getLocation());
		Set<Employees> emp = empEntity(newDto.getEmployees());
		dept.setEmployees(emp);
		deptRepo.save(dept);
		
		return newDto;
	}


//========================================ENTITY TO DTO==================================================================	

	public Set<EmployeesDto> entToDto(Set<Employees> emp) {
	
	Set<EmployeesDto> sDto = new HashSet<EmployeesDto>();
	emp.forEach(eSet->{
		EmployeesDto eDto = new EmployeesDto();
		eDto.setEmpId(eSet.getEmpid());
		eDto.setName(eSet.getName());
		eDto.setContact(eSet.getContact());
		eDto.setEmail(eSet.getEmail());
		sDto.add(eDto);
	});
	return sDto;
}

//==============================================DTO TO ENTITY=================================================================

	Set<Employees> empEntity(Set<EmployeesDto> entEmp) {
		
	Set<Employees> entSet = new HashSet<>();
	entEmp.forEach(empSet->{
		Employees emp = new Employees();
		emp.setEmpid(empSet.getEmpId());
		emp.setAge(empSet.getAge());
		emp.setContact(empSet.getContact());
		emp.setEmail(empSet.getEmail());
		emp.setGender(empSet.getGender());
		emp.setName(empSet.getName());
		entSet.add(emp);
	});
	return entSet;
	
}

}

