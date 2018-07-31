package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.service.IEmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.ems.dto.EmployeesDto;
import com.ems.response.ResponseData;

@RestController("empController")
@RequestMapping("/poc/employee")
@ApiResponses(
		value = {
				@ApiResponse(code = 200, message = "Status OK"),
				@ApiResponse(code = 400, message = "Bad Request"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not found"),
				@ApiResponse(code = 500, message = "Internal Server Error")
				
		}
		)
@Api(tags = "7-Employee Resources", value = "employees", description = "SHOWS EMPLOYEE OPERATIONS")
public class EmployeesController {

	@Autowired
	private IEmployeeService empService;
	String msg = "Following Data Found";
	
//******************************************getAll*********************************************
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value="Get all Employee details")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData getEmps() {

		List<EmployeesDto> emps = empService.getAllEmployees();
		return new ResponseData("200", msg, emps);
	}

//******************************************getOne**********************************************
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value="Get single Employee details by Id")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData findOne(@PathVariable(value = "id") int eId) {

		EmployeesDto edto = empService.findOneEmp(eId);
		return new ResponseData("200", msg, edto);

	}
	
//******************************************addNew**********************************************
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="Add new Employee")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData addNew(@RequestBody EmployeesDto newDto) {
		
		newDto = empService.addNewEmp(newDto);
		return new ResponseData("200", msg, newDto);
		
	}
	
//******************************************updateEmp-(id in input)**********************************************	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.PUT)
	@ApiOperation(value="Update Employee(email, contact, department) -pass id in body")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData  updateEmpBody(@RequestBody EmployeesDto empDto) {
			
		empDto=empService.updateEmp(empDto);
		return new ResponseData("200","Successfuylly Updated!!",empDto);	
	}

//******************************************updateEmp-(id in url)*******************************************
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value= "/{id}", method=RequestMethod.PUT)
	@ApiOperation(value="Update Employee(email, contact, department) -pass id in URL")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData updateEmpURL(@PathVariable(value = "id") int eId, @RequestBody EmployeesDto empDto) {
		
		EmployeesDto edto = empService.updateEmpURL(eId, empDto);
		return new ResponseData("200", msg, edto);
	}
	
//******************************************deleteOne**********************************************	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="Delete one Employee")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public String deleteEmp(@PathVariable("id") int eId) {
		
		empService.deleteOneEmp(eId);
		return "Deletion Successful of eId= "+eId;		
	}
	
//*****************************************deleteAll***********************************************
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE)
	@ApiOperation(value="Delete all employees")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public String deleteAll() {
		
		return "Deleted All Employees";
		
	}
}
