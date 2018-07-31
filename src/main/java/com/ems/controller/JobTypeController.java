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

import com.ems.dto.JobTypeDto;
import com.ems.response.ResponseData;
import com.ems.service.JobServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("jobTypeController")
@RequestMapping("/poc/jobType")
@ApiResponses(
		value = {
				@ApiResponse(code = 200, message = "Status OK"),
				@ApiResponse(code = 400, message = "Bad Request"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not found"),
				@ApiResponse(code = 500, message = "Internal Server Error")
				
		}
		)
@Api(tags = "4-Jobtype Resources", value = "jobType", description = "SHOWS JOBTYPE OPERATIONS")
public class JobTypeController {
	
	@Autowired
	private JobServiceImpl jService;

//******************************************getAll*********************************************
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value="Get all JobsTypes")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData getAllJob() {
		List<JobTypeDto> jList = jService.getAllJobType();
		return new ResponseData("200", "Following jobs found", jList);
		
	}

//******************************************deleteOne**********************************************	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value="Delete one JobType by Id")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public String removeJob(@PathVariable("id") int jobId) {
		
		jService.deleteJobType(jobId);
		return "Deleted successfully";
	}

//******************************************addNew**********************************************	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add new JobType")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseData addJob(@RequestBody JobTypeDto jDto) {
		
		jDto = jService.addNewJob(jDto);
		return new ResponseData("200", "Successful", jDto);
		
	}

}
