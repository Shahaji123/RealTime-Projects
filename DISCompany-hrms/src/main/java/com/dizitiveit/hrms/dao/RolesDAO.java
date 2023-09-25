package com.dizitiveit.hrms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.Roles;

public interface RolesDAO extends JpaRepository<Roles,Long>{

	public Boolean  existsByRoleName(String roleName);
	public Roles findByRoleName(String roleName);
	 
	@Query(value = "select role_name FROM com.roles ", nativeQuery = true)
	 List<String> findByroleName();
	
}
