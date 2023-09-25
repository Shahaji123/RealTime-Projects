package com.dizitiveit.hrms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.AllowancesDAO;
import com.dizitiveit.hrms.dao.EmployeeAllowancesDAO;
import com.dizitiveit.hrms.dao.EmployeeDAO;
import com.dizitiveit.hrms.model.Allowances;
import com.dizitiveit.hrms.model.Employee;
import com.dizitiveit.hrms.model.EmployeeAllowances;
import com.dizitiveit.hrms.pojo.EmployeeAllowancePojo;

@RestController
@RequestMapping("/employeeAllowances")
public class EmployeeAllowancesController {
	
	 @Autowired
     private EmployeeAllowancesDAO employeeAllowancesDao;
	
	 @Autowired
	 private EmployeeDAO employeeDao;
	
	 @Autowired
	 private AllowancesDAO allowancesDao;

	@PostMapping("saveEmpAllowance/{employeeId}/{allowanceName}")
	 public ResponseEntity<?> saveEmployeeAllowance(@RequestBody EmployeeAllowances employeeAllowances,@PathVariable String employeeId,@PathVariable String allowanceName){
		Employee employee=employeeDao.findByEmployeeId(employeeId);
		Allowances allowances = allowancesDao.findByAllowanceName(allowanceName);
		if(allowances!=null) {
			employeeAllowances.setEmployee(employee);
			employeeAllowances.setAllowances(allowances);
			employeeAllowances.setCreatedAt(new Date());		
			employeeAllowances.setStatus(true);	
			employeeAllowancesDao.save(employeeAllowances);
		    return ResponseEntity.ok("Your Details Saved successfully "); 
		}
		return new ResponseEntity<>("Not found",HttpStatus.BAD_REQUEST); 
	}//method

	@PutMapping("updateEmpAllow/{empAllowanceId}")
	public ResponseEntity<?> updateEmpAllow(@RequestBody EmployeeAllowances employeeAllowances,@PathVariable long empAllowanceId)
	{
		EmployeeAllowances empAllow=employeeAllowancesDao.findByEmpAllowanceId(empAllowanceId);
		if(empAllow!=null)
		{
			empAllow.setFromMonth(employeeAllowances.getFromMonth());
			empAllow.setFromYear(employeeAllowances.getFromYear());
			empAllow.setToMonth(employeeAllowances.getToMonth());
			empAllow.setToYear(employeeAllowances.getToYear());
			empAllow.setValue(employeeAllowances.getValue());
			empAllow.setStatus(employeeAllowances.getStatus());
			//empAllow.setAllowAllowId(employeeAllowances.getAllowAllowId());
			employeeAllowancesDao.save(empAllow);
			//return ResponseEntity.ok("Employee Allowances Updated Successfully.");
			return ResponseEntity.ok("Employee Allowances Updated Successfully.");
	    }
		
			
			return new ResponseEntity<>("Data not found",HttpStatus.BAD_REQUEST);
	   
	}//method
	
	@PostMapping("/deleteEmpAllowance/{empAllowanceId}")
	public ResponseEntity<?> deleteEmpAllowance(@PathVariable long empAllowanceId){
		EmployeeAllowances emp=employeeAllowancesDao.findByEmpAllowanceId(empAllowanceId);
		  
		if(emp!=null) {
			employeeAllowancesDao.delete(emp);
			return ResponseEntity.ok("Id Deleted Successfylly"+empAllowanceId);
		}
		return new ResponseEntity<>("Sorry! Id not Found ",HttpStatus.BAD_REQUEST);
	}//method
	
	
	@PostMapping("/saveListEmpAllownaces/{employeeId}")
	public ResponseEntity<String> saveListEmpAllownaces(@RequestBody List<EmployeeAllowancePojo> employeeAllowancesPojo,
			@PathVariable("employeeId") String employeeId){
		Employee employee = employeeDao.findByEmployeeId(employeeId);
		System.out.println(employee);
		String errorRes = "";
		List<String> empAllowance = allowancesDao.findByallowanceName();
		
		for(EmployeeAllowancePojo employeeAPojo : employeeAllowancesPojo) {
		
			for (String allowanceName : empAllowance) {
				
				Allowances allowances = allowancesDao.findByAllowanceName(allowanceName);
				
				EmployeeAllowances employeeAllowancesExisting = employeeAllowancesDao.getExistingRecord(
						employeeAPojo.getFromMonth(), employeeAPojo.getFromYear(), employee.getEmployeeCode(),
						allowances.getAllowanceId());
				
				if (employeeAllowancesExisting == null) {
					EmployeeAllowances allowance = new EmployeeAllowances();
					allowance.setEmployee(employee);
					allowance.setAllowances(allowances);
					allowance.setFromMonth(employeeAPojo.getFromMonth());
					allowance.setFromYear(employeeAPojo.getFromYear());
					allowance.setToMonth(employeeAPojo.getToMonth());
					allowance.setToYear(employeeAPojo.getToYear());
					allowance.setCreatedAt(new Date());
					
					double val = 0.0;
					if(employeeAPojo.getAllowanceName().contains(allowanceName))
					{
						
						int index = employeeAPojo.getAllowanceName().indexOf(allowanceName);
//						System.out.println("index of allwance name: "+index);
//						val=employeeAPojo.getValue().get(index);
//						System.out.println("value of allwance index: "+val);
					}
					allowance.setValue(val);
					employeeAllowancesDao.save(allowance);
				}//if
				 else {
						errorRes = errorRes + "," + employeeAllowancesExisting.getAllowances().getAllowanceName();
					}
				
			}//inner loop
		}//outer loop
		if (errorRes == "") {
			return ResponseEntity.ok("Saved Sucessfully");
		} 
			return new ResponseEntity<>(errorRes + " Allowance for this month already exists",HttpStatus.BAD_REQUEST);
		
	}//method
	
	
	
}//class
