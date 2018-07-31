package com.ems.security.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.dao.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long>{
	Role findRoleByName(String roleName);
}
