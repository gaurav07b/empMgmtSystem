package com.ems.controller.test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ems.controller.DepartmentsController;
import com.ems.dto.DepartmentsDto;
import com.ems.response.ResponseData;
import com.ems.service.DeptServiceImpl;
import com.ems.service.IDepartmentService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
//@ContextConfiguration
@WebMvcTest(DepartmentsController.class)
//@WebAppConfiguration
public class DepartmentsControllerTest {
	
	@Autowired
	private MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;
//    @MockBean
//    private DeptServiceImpl deptService;
    @MockBean
    private IDepartmentService ideptService;

	  @Before
	    public void setUp() {
	        this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	    }
	//**************************************Basic Test Case***************8**********************
	@Test
	public void test() {
		assert(true);
	}
	//*******************************************************************************************
	
	
	@Test
	public void testGetDept() throws Exception {
		List<DepartmentsDto> lst = new ArrayList<>();
		lst.add(new DepartmentsDto(20,"TestDepartment","TestLocation",null));
		lst.add(new DepartmentsDto(21,"TestDepartment2","TestLocation2",null));
		when(ideptService.getAllDept()).thenReturn(lst);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/department").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		verify(ideptService).getAllDept();
		Gson gson = new Gson();
		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
		assertEquals("200", responseData.getCode().toString());
		assertEquals(result.getResponse().getContentType(), MediaType.APPLICATION_JSON_UTF8.toString());
	
	}

//	@Test
//	public void testFindOne() throws Exception {
//		DepartmentsDto dDto = new DepartmentsDto(20,"TestDepartment","TestLocation",null);
//		when(iDeptServ.findOneDept(Mockito.anyInt())).thenReturn(dDto);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/department/3").accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//		verify(iDeptServ).findOneDept(Mockito.anyInt());//to verify that service is called once
//		Gson gson = new Gson();
//		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
//		assertEquals(responseData.getCode().toString(), "200");
//		assertEquals(result.getResponse().getContentType(), MediaType.APPLICATION_JSON_UTF8.toString());
//	}
//
//	@Test
//	public void testAddNew() throws Exception {
//		DepartmentsDto dDto = new DepartmentsDto(20,"TestDepartment","TestLocation",null);
//		when(iDeptServ.addNewDept(Mockito.any(DepartmentsDto.class))).thenReturn(dDto);
//		Gson gson = new Gson();
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/department").
//				contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(gson.toJson(dDto));
//		MvcResult result = mvc.perform(requestBuilder).andReturn();//*******************************is this correct as it throws exception???
//		verify(iDeptServ).addNewDept(Mockito.any(DepartmentsDto.class));
//		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
//		assertEquals(responseData.getCode().toString(), "200");
//	}
//
//	@Test
//	public void testDeleteDept() throws Exception {
//		when(iDeptServ.deleteOneDept(Mockito.anyInt())).thenReturn("Deletion Successful");
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/department/"+"{id}", new Integer(1));
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//		verify(iDeptServ.deleteOneDept(Mockito.anyInt()));
//		assertEquals(result,"Deletion Successful");
//	}

}
