package com.dizitiveit.hrms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.DeductionDAO;
import com.dizitiveit.hrms.dao.EmployeeDAO;
import com.dizitiveit.hrms.dao.EmployeeDeductionDAO;
import com.dizitiveit.hrms.model.Deduction;
import com.dizitiveit.hrms.model.Employee;
import com.dizitiveit.hrms.model.EmployeeDeduction;

@RestController
@RequestMapping("/employeeDeduction")
public class EmployeeDeductionController {

	@Autowired
	private EmployeeDAO employeeDao;
	
	@Autowired
	private DeductionDAO deductionDao;
	
	@Autowired
	private EmployeeDeductionDAO empDeductionDao;
	
//	@PostMapping("/saveEmpDeductions/{employeeId}/{deductionId}")
//	public ResponseEntity<?> saveEmpDeduction(@RequestBody EmployeeDeduction empDeduction,@PathVariable String employeeId,@PathVariable String deductionName){
//		Employee emp=employeeDao.findByEmployeeId(employeeId);
//	    Deduction deduction=deductionDao.findByDeductionName(deductionName);
//	    
//	  //  if(dudu)
//	}//method
}
