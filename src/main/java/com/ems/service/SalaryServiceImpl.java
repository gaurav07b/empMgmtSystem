package com.ems.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.entity.Employees;
import com.ems.dao.entity.Salary;
import com.ems.dao.repository.SalaryRepository;
import com.ems.dto.EmployeesDto;
import com.ems.dto.SalaryDto;

@Service("salaryService")
public class SalaryServiceImpl implements ISalaryService {

	@Autowired
	private SalaryRepository salRepo;
//=====================================================GET ALL SALARY=======================================================	
	@Override
	public List<SalaryDto> getAllSalary() {
		List<Salary> salList = salRepo.findAll();
		return salAssembler(salList);
	}
//=========================================================ASSEMBLER========================================================
	private List<SalaryDto> salAssembler(List<Salary> salList) {
		List<SalaryDto> salDto = new ArrayList<>();
		salList.forEach(sal -> {
			SalaryDto sdto = new SalaryDto();
			sdto.setSalId(sal.getSalId());
			sdto.setDate(sal.getDate());
			sdto.setSal(sal.getSal());
			Set<Employees> emp = sal.getEmployees();
			Set<EmployeesDto> eAssembler = eDto(emp);
			sdto.setEmployees(eAssembler);
			salDto.add(sdto);
		});
		return salDto;
	}

//=====================================================ADD NEW SALARY=======================================================
	@Override
	public SalaryDto addNewSal(SalaryDto sDto) {
		Salary sal = new Salary();
		sal.setSal(sDto.getSal());
		sal.setDate(sDto.getDate());
		Set<Employees> emp = dtoToEntity(sDto.getEmployees());
		sal.setEmployees(emp);
		salRepo.save(sal);
		return sDto;
	}

//========================================================DELETE===========================================================
	@Override
	public String deleteOneSal(int salId) {
		Salary sDto = salRepo.getOne(salId);
		salRepo.delete(sDto);
		return "Deleted Successfully";
	}
//=====================================================ENTITY TO DTO=======================================================
	
	
	///////****************************************************************************//////////////////////////////////
	private Set<EmployeesDto> eDto(Set<Employees> emp) {
		Set<EmployeesDto> newDto = new HashSet<>();
		emp.forEach(newEmp->{
			EmployeesDto empDto = new EmployeesDto();
			empDto.setEmpId(newEmp.getEmpid());
			empDto.setContact(newEmp.getContact());
			empDto.setEmail(newEmp.getEmail());
			empDto.setGender(newEmp.getGender());
			empDto.setName(newEmp.getName());
			empDto.setJoinDate(newEmp.getJoinDate());
			newDto.add(empDto);
		});
		
		return newDto;
	}
	
	///////////////////********************************************************************************/////////////////
	
//======================================================DTO TO ENTITY=======================================================
	
	
	///////****************************************************************************//////////////////////////////////
	private Set<Employees> dtoToEntity(Set<EmployeesDto> employees) {
		Set<Employees> ent = new HashSet<>();
		employees.forEach(empl->{
			Employees emp = new Employees();
			emp.setEmpid(empl.getEmpId());
			emp.setName(empl.getName());
			emp.setAge(empl.getAge());
			emp.setContact(empl.getContact());
			emp.setEmail(empl.getEmail());
			emp.setJoinDate(empl.getJoinDate());
			ent.add(emp);
		});
		
		return ent;
	}
	
	///////////////////********************************************************************************/////////////////


}
