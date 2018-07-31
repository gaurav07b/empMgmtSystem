package com.ems.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.entity.Departments;
import com.ems.dao.entity.Employees;
import com.ems.dao.entity.Project;
import com.ems.dao.entity.Salary;
import com.ems.dao.repository.EmployeeRepository;
import com.ems.dto.DepartmentsDto;
import com.ems.dto.EmployeesDto;
import com.ems.dto.ProjectDto;
import com.ems.dto.SalaryDto;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;
	
//==================================================GET ALL EMPLOYEES======================================================
	@Override
	public List<EmployeesDto> getAllEmployees() {
		List<Employees> userList = empRepository.findAll();
		return empAssembler(userList);
	}
	
	DeptServiceImpl dsl = new DeptServiceImpl();
	ProjServiceImpl psl = new ProjServiceImpl();
	SalaryServiceImpl ssl = new SalaryServiceImpl();
	
//=======================================================ASSEMBLER=========================================================
	private List<EmployeesDto> empAssembler(List<Employees> userList) {
		List<EmployeesDto> employees = new ArrayList<>();
		userList.forEach( emp -> {
			EmployeesDto empDto = new EmployeesDto();
			empDto.setEmpId(emp.getEmpid());
			empDto.setAge(emp.getAge());
			empDto.setContact(emp.getContact());
			empDto.setEmail(emp.getEmail());
			empDto.setGender(emp.getGender());
			empDto.setJoinDate(emp.getJoinDate());
			empDto.setName(emp.getName());
			DepartmentsDto dDTO = convertToDtoDept(emp.getDepartment());
			empDto.setDepartment(dDTO);
			List<ProjectDto> lpDto = psl.assembler(emp.getProjects());
			empDto.setProjects(lpDto);
			SalaryDto sDto = salEntToDto(emp.getSalary());
			empDto.setSalary(sDto);
			employees.add(empDto);
		});
		
		return employees;
	}
//===================================================Salary entity to DTO====================================================	
	private SalaryDto salEntToDto(Salary salary) {
		SalaryDto newSalDto = new SalaryDto();
		newSalDto.setSalId(salary.getSalId());
		newSalDto.setSal(salary.getSal());
		newSalDto.setDate(salary.getDate());
		return newSalDto;
	}

//=====================================================GET ONE EMPLOYEE=======================================================
		@Override
		public EmployeesDto findOneEmp(int empId) {
			Employees empl = empRepository.getOne(empId);
			EmployeesDto empDto = new EmployeesDto();
			empDto.setEmpId(empl.getEmpid());
			empDto.setAge(empl.getAge());
			empDto.setContact(empl.getContact());
			empDto.setEmail(empl.getEmail());
			empDto.setName(empl.getName());
			empDto.setJoinDate(empl.getJoinDate());
			DepartmentsDto dDTO = convertToDtoDept(empl.getDepartment());
			empDto.setDepartment(dDTO);
			List<ProjectDto> lpDto = psl.assembler(empl.getProjects());
			empDto.setProjects(lpDto);
			SalaryDto sDto = salEntToDto(empl.getSalary());
			empDto.setSalary(sDto);
			return empDto;
		}
		
//====================================================ADD NEW EMPLOYEE=====================================================
		@Override
		public EmployeesDto addNewEmp(EmployeesDto newDto) {
				
			Employees employee = new Employees();
			employee.setName(newDto.getName());	
			employee.setAge(newDto.getAge());
			employee.setContact(newDto.getContact());
			Departments dept = convertToEntDept(newDto.getDepartment());
			employee.setDepartment(dept);
			employee.setEmail(newDto.getEmail());
			employee.setGender(newDto.getGender());
			employee.setJoinDate(newDto.getJoinDate());
			employee.setJobType(newDto.getJobType());
			List<Project> projSet = convertToEntProj(newDto.getProjects());
			employee.setProjects(projSet);
			Salary sdto = converToEntSal(newDto.getSalary());
			employee.setSalary(sdto);
			
			empRepository.save(employee);
				
			return newDto;
		}

//============================================UPDATE EMPLOYEES(id in payload)=================================================
		@Override
		public EmployeesDto updateEmp(EmployeesDto empDto) {
			Employees emp = empRepository.getOne(empDto.getEmpId());
			emp.setEmail(empDto.getEmail());
			emp.setContact(empDto.getContact());
			Departments dept = convertToEntDept(empDto.getDepartment());
			emp.setDepartment(dept);

			empRepository.save(emp);
			return empDto;
		}
		
//============================================UPDATE EMPLOYEES(id in URL)=====================================================

		@Override
		@Transactional
		public EmployeesDto updateEmpURL(int eId, EmployeesDto empDto) {
			Employees emp = empRepository.getOne(eId);
			emp.setEmail(empDto.getEmail());
			emp.setContact(empDto.getContact());

			return empDto;
		}

//========================================================DELETE ONE==========================================================
	@Override
	public String deleteOneEmp(int empId) {
		empRepository.delete(empRepository.getOne(empId));
		return "Deletion Successful";		
	}
	
//========================================================DELETE ALL==========================================================
	@Override
	public void deleteAllEmp() {
		empRepository.deleteAll();
	}

//=======================================Conversions DTO to Entity and vice-versa==========================================

	private Departments convertToEntDept(DepartmentsDto ddto) {
		Departments department = new Departments();
		department.setDeptId(ddto.getDeptId());

		return department;
}

	private DepartmentsDto convertToDtoDept(Departments department) {
		DepartmentsDto ddto = new DepartmentsDto();
		ddto.setDeptId(department.getDeptId());
		ddto.setDeptName(department.getDeptName());
		ddto.setLocation(department.getLocation());
		return ddto;
}
	
	private Salary converToEntSal(SalaryDto salDto) {
		Salary sal = new Salary();
		sal.setSalId(salDto.getSalId());

		return sal;
}

	private List<Project> convertToEntProj(List<ProjectDto> projects) {
		List<Project> proj = new ArrayList<>();
		projects.forEach(project->{
			Project pNew = new Project();
			pNew.setpId(project.getpId());

			proj.add(pNew);
		});
		return proj;
	}

}
