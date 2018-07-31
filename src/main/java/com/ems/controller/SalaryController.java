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

import com.ems.dto.SalaryDto;
import com.ems.response.ResponseData;
import com.ems.service.ISalaryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("salaryController")
@RequestMapping("/poc/salary")
@ApiResponses(
		value = {
				@ApiResponse(code = 200, message = "Status OK"),
				@ApiResponse(code = 400, message = "Bad Request"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not found"),
				@ApiResponse(code = 500, message = "Internal Server Errort")
				
		}
		)
@Api(tags = "3-Salary Resources", value = "salary", description = "SHOWS SALARY OPERATIONS")
public class SalaryController {
	
	@Autowired
	private ISalaryService salService;

//******************************************getAll*********************************************
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value="Get all salaries")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData getEmps() {

		List<SalaryDto> sal = salService.getAllSalary();
		return new ResponseData("200", "Following Data Found", sal);
	}

//******************************************addNew**********************************************
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add new salary")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData addNew(@RequestBody SalaryDto newDto) {
		
		newDto = salService.addNewSal(newDto);
		return new ResponseData("200", "Added successfuly", newDto);
		
	}

//******************************************deleteOne**********************************************	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="Delete salary by Id")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public String deleteEmp(@PathVariable("id") int salId) {
		
		salService.deleteOneSal(salId);
		return "Deletion Successful";
		
	}

}
