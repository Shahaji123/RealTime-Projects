package com.dizitiveit.hrms.controller;

import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.EmployeeDAO;
import com.dizitiveit.hrms.dao.MonthlySalaryDAO;
import com.dizitiveit.hrms.model.Employee;
import com.dizitiveit.hrms.model.MonthlySalary;

 
@RestController
@RequestMapping("/monthlySalary")
public class MonthlySalaryController {
    
	  @Autowired
	  private EmployeeDAO employeeDao;
	  
	  @Autowired
	  private MonthlySalaryDAO monthlysalaryDao;
	  
	@PostMapping("/autoSaveForSingleEmployee/{employeeId}")
	public ResponseEntity<?> autoSaveForSingleEmployee(@RequestBody MonthlySalary monthlysalary,@PathVariable String employeeId){
		//Month months = Month.of(month);
		  Employee employee = employeeDao.findByEmployeeId(employeeId);
		   if(employee!=null) {
			//MonthlySalary sal=  
		   }
		  return null;
	}//method
}
