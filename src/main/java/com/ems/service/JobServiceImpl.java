package com.ems.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.entity.Employees;
import com.ems.dao.entity.JobType;
import com.ems.dao.repository.JobTypeRepository;
import com.ems.dto.EmployeesDto;
import com.ems.dto.JobTypeDto;

@Service("jobTypeService")
public class JobServiceImpl implements IJobTypeService {

	@Autowired
	private JobTypeRepository jobRepo;

// ==================================================GET ALL JOBS==========================================================

	@Override
	public List<JobTypeDto> getAllJobType() {
		List<JobType> jobType = jobRepo.findAll();
		return jobAssembler(jobType);
	}

// ====================================================ASSEMBLER===========================================================

	private List<JobTypeDto> jobAssembler(List<JobType> jobType) {
		List<JobTypeDto> jobDto = new ArrayList<>();
		jobType.forEach(job -> {
			JobTypeDto jto = new JobTypeDto();
			jto.setJobId(job.getJobId());
			jto.setJobType(job.getJobType());
			Set<EmployeesDto> sDto = entToDto(job.getEmployees());
			jto.setEmployees(sDto);

			jobDto.add(jto);
		});
		return jobDto;
	}

// =====================================================DELETE=============================================================

	@Override
	public String deleteJobType(int jobId) {
		JobType jobT = jobRepo.getOne(jobId);
		jobRepo.delete(jobT);
		return "Deletion Successful";
	}

// ====================================================ADD NEW JOB========================================================

	@Override
	public JobTypeDto addNewJob(JobTypeDto newDto) {
		JobType jType = new JobType();
		jType.setJobId(newDto.getJobId());
		jType.setJobType(newDto.getJobType());
		Set<Employees> emp = empEntity(newDto.getEmployees());
		jType.setEmployees(emp);
		jobRepo.save(jType);
		return newDto;
	}

// ======================================================DTO TO ENTITY====================================================

	Set<Employees> empEntity(Set<EmployeesDto> entEmp) {
		Set<Employees> entSet = new HashSet<>();
		entEmp.forEach(empSet -> {
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

// =======================================================ENTITY TO DTO===================================================

	public Set<EmployeesDto> entToDto(Set<Employees> emp) {

		Set<EmployeesDto> sDto = new HashSet<EmployeesDto>();
		emp.forEach(eSet -> {
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
