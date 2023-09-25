package com.dizitiveit.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.EmployeeAllowances;

public interface EmployeeAllowancesDAO extends JpaRepository<EmployeeAllowances,Integer> {

	
	EmployeeAllowances findByEmpAllowanceId(Long employeeAllowanceId);
	
	 @Query(value = "SELECT * FROM employee_allowances WHERE from_month=?1  and from_year =?2 and employee_employee_code=?3 and allowances_allowance_id=?4", nativeQuery = true)
	 EmployeeAllowances getExistingRecord(Integer month,Integer year,Long emplyoeeCode,Long allowanceId);
	 
}
