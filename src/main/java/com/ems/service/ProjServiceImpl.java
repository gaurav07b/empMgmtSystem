package com.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.entity.Employees;
import com.ems.dao.entity.Project;
import com.ems.dao.repository.ProjectRepository;
import com.ems.dto.EmployeesDto;
import com.ems.dto.ProjectDto;

@Service("projService")
public class ProjServiceImpl implements IProjectService {

	@Autowired
	private ProjectRepository projRepo;
	
//=============================================GET ALL PROJECTS===========================================================
	
	@Override
	public List<ProjectDto> getAllProject() {
		List<Project> projList = projRepo.findAll();
		return assembler(projList);
	}

//=====================================================ASSEMBLER=========================================================
	
	public List<ProjectDto> assembler(List<Project> projList) {
		List<ProjectDto> projDto = new ArrayList<>();
		projList.forEach(proj -> {
			ProjectDto pdto = new ProjectDto();
			pdto.setpId(proj.getpId());
			pdto.setpName(proj.getpName());
			pdto.setpLocation(proj.getpLocation());
			List<EmployeesDto> sDto = empProjAssembler(proj.getEmployees());
			pdto.setEmployees(sDto);
			
			projDto.add(pdto);
		});
		return projDto;
	}

//=========================================================DELETE======================================================
	
	@Override
	public String deleteProject(int projId) {
		Project proj = projRepo.getOne(projId);
		projRepo.delete(proj);
		return "Deleted Successfully";
	}
	
//=====================================================FIND ONE PROJECT=================================================
	
	@Override
	public ProjectDto findOneProject(int projId) {
		Project proj = projRepo.getOne(projId);
		ProjectDto pDto = new ProjectDto();
		pDto.setpId(proj.getpId());
		pDto.setpName(proj.getpName());
		pDto.setpLocation(proj.getpLocation());
		return pDto;
	}

//=====================================================ADD NEW PROJECT====================================================
	
	@Override
	public ProjectDto addNewProject(ProjectDto newDto) {
		Project proj = new Project();
		proj.setpLocation(newDto.getpLocation());
		proj.setpName(newDto.getpName());
		List<Employees> emp = empEntity(newDto.getEmployees());
		proj.setEmployees(emp);
		projRepo.save(proj);
		return newDto;
	}
//=======================================================DTO TO ENTITY====================================================	
	List<Employees> empEntity(List<EmployeesDto> entEmp) {
		List<Employees> entSet = new ArrayList<>();
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
//========================================================ENTITY TO DTO==============================================	
	public List<EmployeesDto> empProjAssembler(List<Employees> empSet) {
		List<EmployeesDto> sDto = new ArrayList<EmployeesDto>();
		empSet.forEach(eSet->{
			EmployeesDto eDto = new EmployeesDto();
			eDto.setEmpId(eSet.getEmpid());
			eDto.setName(eDto.getName());
			eDto.setContact(eDto.getContact());
			eDto.setEmail(eDto.getEmail());
			sDto.add(eDto);
		});
		return sDto;
	}
		
}
