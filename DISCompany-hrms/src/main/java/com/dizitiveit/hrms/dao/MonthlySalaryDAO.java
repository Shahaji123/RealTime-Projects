package com.dizitiveit.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.MonthlySalary;

public interface MonthlySalaryDAO extends JpaRepository<MonthlySalary,Integer> {

	
	  public MonthlySalary findByMonthlySalaryId(Integer monthlySalaryId);
	  
	 @Query(value = "select * FROM com.monthly_salary where employee_employee_code=?1 and current_month=?2 and current_year=?3", nativeQuery = true)
	 MonthlySalary findWithYear(long employeeCode,String month,int currentYear);
}
