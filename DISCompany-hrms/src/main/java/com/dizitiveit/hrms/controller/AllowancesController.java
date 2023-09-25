package com.dizitiveit.hrms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.AllowancesDAO;
import com.dizitiveit.hrms.model.Allowances;
@RestController
@RequestMapping("/allownace")
public class AllowancesController {

	@Autowired
	private AllowancesDAO allowancesDao;
  
	 @PostMapping("/saveAllowances")
	 public ResponseEntity<?> saveAllowances(@RequestBody Allowances allowances)
	 {
		if(allowancesDao.existsByAllowanceName(allowances.getAllowanceName()))
		{
			Allowances allowancesUpdate = allowancesDao.findByAllowanceName(allowances.getAllowanceName());
			allowancesUpdate.setStatus(allowances.getStatus());
			allowancesUpdate.setAllowanceType(allowances.getAllowanceType());
			allowancesUpdate.setStartDate(allowances.getStartDate());
			allowancesUpdate.setEndDate(allowances.getEndDate());
			allowancesUpdate.setValue(allowances.getValue());
			allowancesUpdate.setLastModifiedDate(new Date());
			allowancesDao.save(allowancesUpdate);
			return ResponseEntity.ok("Allowances Details Updated  Sucessfully");
		}
		else {
			allowances.setActive(true);
			allowancesDao.save(allowances);
			return new ResponseEntity<>("Allowances Details Saved Sucessfully",HttpStatus.OK);
		}
	 }//method
	 
		
		@GetMapping("/getAllowances/{allowanceName}")
		 public ResponseEntity<?> getAllowances (@PathVariable String allowanceName ){
			Allowances allowances = allowancesDao.findByAllowanceName(allowanceName);
			if(allowances!=null)
			{
			HashMap<String,Allowances> map=new HashMap<String, Allowances>();
			   map.put("Allowances", allowances);
			   return ResponseEntity.ok(map);	
			}
				 return ResponseEntity.ok("Allowance Name"+""+allowanceName+""+"Not Found");		
		}//method
		
		@GetMapping("/listOfAllowances")
		 public ResponseEntity<?> listOfAllowances()
		 {
			List<Allowances> allowances = allowancesDao.findAll();
			HashMap<String,List<Allowances>> map=new HashMap<String, List<Allowances>>();
			   map.put("Allowances", allowances);
			   return ResponseEntity.ok(map);		
		 }//method
		

		@DeleteMapping("/deleteAllowances/{allowanceId}")
		 public ResponseEntity<?> deleteAllowances(@PathVariable long allowanceId){
			Optional<Allowances> allowances = allowancesDao.findByAllowanceId(allowanceId);
			if(allowances.isPresent()) {
				allowancesDao.delete(allowances.get());
				return ResponseEntity.ok("Allowances Details Deleted Sucessfully");
			}
		
			return new ResponseEntity<>("Allowances Id Not fount",HttpStatus.BAD_REQUEST);
			   		
		 }//method
		

		@PutMapping("/deactiveAllowances/{allowanceName}")
		public ResponseEntity<?> deactiveAllowances(@PathVariable String allowanceName )
		{
			Allowances allowances = allowancesDao.findByAllowanceName(allowanceName);
			if(allowances != null)
			{
				allowances.setStatus(false);
				allowancesDao.save(allowances);
				return ResponseEntity.ok(" Allowances De-Activated Successfully "+allowanceName);
			}
				 return new ResponseEntity<>("Allowance Name"+""+allowanceName+""+"Not Found",HttpStatus.BAD_REQUEST);
			
		}//method
		 @GetMapping("/getAllowance")
		 public ResponseEntity<?> getAllowance() {
			List<Allowances> allowances = allowancesDao.findAll();
			HashMap<String, List<Allowances>> map = new HashMap<String, List<Allowances>>();
			map.put("Allowances", allowances);
			return ResponseEntity.ok(map);

		}//method
}//class
