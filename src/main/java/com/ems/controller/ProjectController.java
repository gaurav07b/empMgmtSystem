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

import com.ems.dto.ProjectDto;
import com.ems.response.ResponseData;
import com.ems.service.IProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("projController")
@RequestMapping("/poc/project")
@ApiResponses(
		value = {
				@ApiResponse(code = 200, message = "Status OK"),
				@ApiResponse(code = 400, message = "Bad Request"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not found"),
				@ApiResponse(code = 500, message = "Internal Server Error")
				
		}
		)
@Api(tags = "6-Project Resources", value = "project", description = "SHOWS PROJECT OPERATIONS")
public class ProjectController {
	
	@Autowired
	private IProjectService projService;

//******************************************getAll*********************************************
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value="Get all project details")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData getAllProjects(){
		List<ProjectDto> pDto = projService.getAllProject();
		return new ResponseData("200", "Following Data Found", pDto);

	}
	
//******************************************getOne*********************************************
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value="Get one Project details by Id")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData findOne(@PathVariable(value = "id") int pId) {

		ProjectDto pDto = projService.findOneProject(pId);
		return new ResponseData("200", "Following Data Found", pDto);

	}
	
//*******************************************addNew*********************************************
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add new Project")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData addNew(@RequestBody ProjectDto newDto) {
		
		newDto = projService.addNewProject(newDto);
		return new ResponseData("200", "Added successfuly", newDto);
		
	}

//******************************************deleteOne********************************************	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="Delete one Project by Id")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public String deleteProj(@PathVariable("id") int pId) {
		
		projService.deleteProject(pId);
		return "Deletion Successful";
		
	}


}
