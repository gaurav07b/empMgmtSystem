package com.ems.service.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ems.dao.repository.DepartmentRepository;
import com.ems.dto.DepartmentsDto;
import com.ems.service.DeptServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptServiceImplTest {

	@Autowired
	private DeptServiceImpl dsi;
	
	@MockBean
	private DepartmentRepository deptRepo;	
	
	@Test
	public void testGetAllDept() {
		List<DepartmentsDto> lst = new ArrayList<>();
		lst.add(new DepartmentsDto(2, "ServiceTest", "TestLocation", null));
		lst.add(new DepartmentsDto(12, "ServiceTest1", "TestLocation1", null));
		lst.add(new DepartmentsDto(21, "ServiceTest2", "TestLocation2", null));

		when(dsi.getAllDept()).thenReturn(lst);
		List<DepartmentsDto> dept = dsi.getAllDept();
		assertNotNull(dept);
	}

	@Test
	public void testDeleteOneDept() {
		List<DepartmentsDto> lst = new ArrayList<>();
		lst.add(new DepartmentsDto(12, "ServiceTest1", "TestLocation1", null));
		lst.add(new DepartmentsDto(21, "ServiceTest2", "TestLocation2", null));
		lst.add(new DepartmentsDto(5, "ServiceTest3", "TestLocation3", null));
		
		when(dsi.deleteOneDept(Mockito.anyInt())).thenReturn("Deletion Successful");
		String response = dsi.deleteOneDept(5);
		assertEquals("Deletion Successful", response);

	}

	@Test
	public void testFindOneDept() {
		List<DepartmentsDto> lst = new ArrayList<>();
		DepartmentsDto dDto = new DepartmentsDto(1, "ServiceTest-0", "TestLocation-0", null);
		lst.add(new DepartmentsDto(2, "ServiceTest", "TestLocation", null));
		lst.add(new DepartmentsDto(12, "ServiceTest1", "TestLocation1", null));
		lst.add(new DepartmentsDto(21, "ServiceTest2", "TestLocation2", null));
		
		Mockito.when(dsi.findOneDept(Mockito.anyInt())).thenReturn(dDto);
		DepartmentsDto deptDto = dsi.findOneDept(1);
		assertNotNull(deptDto);
	}

	@Test
	public void testAddNewDept() {
		//List<DepartmentsDto> lst = new ArrayList<>();
		DepartmentsDto dDto = new DepartmentsDto();
		dDto.setDeptId(23);
		dDto.setDeptName("NewTestData");
		dDto.setLocation("TestDataLocation");
		dDto.setEmployees(null);
		
		when(dsi.addNewDept(Mockito.any(DepartmentsDto.class))).thenReturn(dDto);
		DepartmentsDto deptDto = dsi.addNewDept(dDto);
		assertNotNull(deptDto);
	}

}
