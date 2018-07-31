package com.ems.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ems.dao.entity.User;
import com.ems.security.model.UserDto;
import com.ems.security.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("signUpController")
@Api(tags = "1-SignUpRemove Resources", value = "register", description = "REGISTER HERE")
public class UserController {

	@Autowired
	private UserService userService;
	@ApiOperation(value = "Register new User")
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User saveUser(@RequestBody UserDto user) {

		return userService.save(user);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Remove User")
	@RequestMapping(value = "/removeUser/{name}", method = RequestMethod.DELETE)
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public void removeUser(@PathVariable(value = "name") String username) {
		User user = userService.findOne(username);
		if (user != null) {
			userService.delete(user.getId());
		} else
			System.out.println("No user for this ID exists");

	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "All Users registered")
	@RequestMapping(value="/showAll", method = RequestMethod.GET)
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public List<UserDto> showAllUsers(){
		List<UserDto> lst = new ArrayList<>();
		List<User> lst2 = userService.findAll();
		ModelMapper model = new ModelMapper();
		for (User user : lst2) {
			lst.add(model.map(user, UserDto.class));
		}
		return lst;
		
		
	}

}
