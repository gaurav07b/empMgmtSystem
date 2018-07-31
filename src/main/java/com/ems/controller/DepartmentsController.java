package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.DepartmentsDto;
import com.ems.response.ResponseData;
import com.ems.service.IDepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("deptController")
@RequestMapping("/poc/department")

@ApiResponses(
		value = {
				@ApiResponse(code = 200, message = "Status OK"),
				@ApiResponse(code = 400, message = "Bad Request"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not found"),
				@ApiResponse(code = 500, message = "Internal Server Error")
				
		}
		)
@Api(tags = "5-Department Resources", value = "departments", description = "<br>SHOWS DEPARTMENT OPERATIONS</br>")
public class DepartmentsController {
	
	@Autowired IDepartmentService deptService;

//******************************************getAll*********************************************
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value="List of all departments"/*, response = DepartmentsDto.class*/, notes ="<b> Departments: </b> \t\n 1. fetch all departmets")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData getDept() {
		
		List<DepartmentsDto> dept = deptService.getAllDept();
		return new ResponseData("200", "List of Departments", dept);
		
	}

//******************************************getOne**********************************************	
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value="Get one Department by Id", notes ="<b> Departments: </b> \t\n 1. fetch one departmet \t\n 2. ID is required")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData findOne(@PathVariable(value = "id") int dId) {

		DepartmentsDto ddto = deptService.findOneDept(dId);
		return new ResponseData("200", "Following Department Found", ddto);
		 
	}

//******************************************addNew**********************************************
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add new Department", notes ="<b> Departments: </b> \t\n 1. add new department")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData addNew(@RequestBody DepartmentsDto newDto) {
		
		newDto = deptService.addNewDept(newDto);
		return new ResponseData("200", "Added successfuly", newDto);
		
	}
	
//******************************************deleteOne**********************************************	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="Delete Department by Id", notes ="<b> Departments: </b> \t\n 1. Deletes one departmet by ID \t\n 2. ID is required")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public String deleteDept(@PathVariable("id") int dId) {
		
		deptService.deleteOneDept(dId);
		return "Deletion Successful";	
	}

}
