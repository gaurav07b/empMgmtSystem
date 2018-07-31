package com.ems.security.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String username;
    private String password;
    private Set<RoleDto> role;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public Set<RoleDto> getRole() {
		return role;
	}

	public void setRole(Set<RoleDto> role) {
		this.role = role;
	}

}
