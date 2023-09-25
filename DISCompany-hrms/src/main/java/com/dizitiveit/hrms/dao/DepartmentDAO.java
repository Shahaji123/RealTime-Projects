package com.dizitiveit.hrms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.Department;

public interface DepartmentDAO extends JpaRepository<Department,Integer> {
	Department findBydeptId(Integer deptId);
	Boolean existsByDeptName(String deptName);
	Department findByDeptName(String deptName);
	
	 @Query(value = "select dept_name FROM com.department ", nativeQuery = true)
	 List<String> findBydeptName();
	 
	 
	 @Query(value = "select * FROM com.department order by dept_id asc", nativeQuery = true)
	 List<Department> findAll();
}
