package com.dizitiveit.hrms.controller;

import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dizitiveit.hrms.dao.EmployeeDAO;
import com.dizitiveit.hrms.dao.MonthlySalaryDAO;
import com.dizitiveit.hrms.dao.PaySlipLineItemDAO;
import com.dizitiveit.hrms.model.Employee;
import com.dizitiveit.hrms.model.MonthlySalary;
import com.dizitiveit.hrms.model.PaySlipLineItem;

public class PaySlipsLineItemController {

	
	   @Autowired
     	private MonthlySalaryDAO monthlySalaryDao;
	   
	   @Autowired
	   private EmployeeDAO employeeDao;
	   
	   @Autowired
	   private PaySlipLineItemDAO paySlipLineItemDao;
	   
	   @PostMapping("/savePaySlips/{employeeId}/{month}/{year}")
	   public ResponseEntity<String> savePaySlips(@RequestBody PaySlipLineItem paySlip,@PathVariable String employeeId,@PathVariable int month,@PathVariable int year){
		  
		   Month mnt=Month.of(month);
		   Employee employee=employeeDao.findByEmployeeId(employeeId);
		   System.out.println(employee);
		   if(employee!=null) {
			   MonthlySalary sal=monthlySalaryDao.findWithYear(employee.getEmployeeCode(), mnt.name(), year);
			   if(sal!=null) {
			  
				   MonthlySalary msal=monthlySalaryDao.findByMonthlySalaryId(sal.getMonthlySalaryId());
			    PaySlipLineItem payItem=paySlipLineItemDao.findByItemDetails(paySlip.getItemDetails(),mnt.name(), year,sal.getMonthlySalaryId());
			   
			   if(payItem==null) {
				   paySlip.setMonthlySalary(sal);
				   paySlip.setCurrentMonth(mnt.name());
				   paySlip.setCurrentYear(year);
				   paySlip.setUpdatedDate(LocalDateTime.now());
				   paySlipLineItemDao.save(paySlip);
				  
				   return ResponseEntity.ok("Saved "+paySlip.getPayItemId()+" Successfully");
				   
			   }
			   return new ResponseEntity<>("Sorry your Id already Exists ",HttpStatus.BAD_REQUEST);
		   }
		 }
		   
		   return new ResponseEntity<>("Sorry! Employee  "+employeeId+" Not Found",HttpStatus.BAD_REQUEST);
	   }//method
}//class
