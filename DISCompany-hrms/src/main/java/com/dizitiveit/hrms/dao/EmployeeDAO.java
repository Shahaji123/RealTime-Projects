package com.dizitiveit.hrms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dizitiveit.hrms.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee,Long> {
	
   Optional<Employee> findByOfficialEmailIdOrPhoneNumber(String officialEmailId,String phoneNumber);
	//Employee findByOfficialEmailId(String officialEmailId);
	Employee findByEmployeeId(String employeeId);
//	Employee findByPhoneNumber(String phoneNumber);
	 Boolean existsByOfficialEmailId(String emailId);
	public Boolean existsByPhoneNumber(String phoneNumber);
	Boolean existsByEmployeeId(String employeeId);
	Boolean existsByAdharNumber(String AdharNumber);
	Boolean existsByPanCardNumber(String panCardNumber);
//	Boolean existsByStatus(boolean status);
}
