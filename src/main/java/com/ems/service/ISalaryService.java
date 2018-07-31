package com.ems.service;

import java.util.List;

import com.ems.dto.SalaryDto;

public interface ISalaryService {
	
	List<SalaryDto> getAllSalary();
	SalaryDto addNewSal(SalaryDto newDto);
	String deleteOneSal(int salId);

}
