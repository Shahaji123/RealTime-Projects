package com.dizitiveit.hrms.controller;

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

import com.dizitiveit.hrms.dao.DeductionDAO;
import com.dizitiveit.hrms.model.Deduction;


@RestController
@RequestMapping("/deducation")
public class DeductionController {
	
	@Autowired
	private DeductionDAO deductionDao;
	
	@PostMapping("/saveDeductions")
	 public ResponseEntity<?> saveAllowances(@RequestBody Deduction deductions)
	 {
		if(deductionDao.existsByDeductionName(deductions.getDeductionName()))
		{
			Deduction deductionsUpdate = deductionDao.findByDeductionName(deductions.getDeductionName());
			deductionsUpdate.setDeductionType(deductions.getDeductionType());
			deductionsUpdate.setStatus(deductions.getStatus());
			deductionsUpdate.setDeductionName(null);
			deductionsUpdate.setStatus(true);
			deductionDao.save(deductionsUpdate);
			return new ResponseEntity<>("Deduction Details Updated  Sucessfully",HttpStatus.CREATED);
		}
		else {
			deductionDao.save(deductions);
			return new ResponseEntity<>("Deduction Details Saved Sucessfully",HttpStatus.CREATED);
		}
	 }//method
	
	
	@GetMapping("/getAllDeductionNames")
	 public ResponseEntity<?> getAllDeductionNames()
	 {
		List<String> deductions = deductionDao.findByDeductionName();
		HashMap<String,List<String>> map=new HashMap<String,List<String>>();
		   map.put("Deductions", deductions);
		   return ResponseEntity.ok(map);	
	 }//method
	
	@GetMapping("/listOfDeduction")
	 public ResponseEntity<?> listOfDeductions()
	 {
		List<Deduction> deductions = deductionDao.findAll();
		HashMap<String,List<Deduction>> map=new HashMap<String, List<Deduction>>();
		   map.put("Deductions", deductions);
		   return ResponseEntity.ok(map);
		
	 }//method
	
	@DeleteMapping("/deleteDeductions/{deductionId}")
	 public ResponseEntity<?> deleteAllowances(@PathVariable long deductionId)
	 {
		Optional<Deduction> id=deductionDao.findByDeductionId(deductionId);
		if(id.isPresent()) {
			
			deductionDao.delete(id.get());
		return new ResponseEntity<>("Deduction Details Deleted Sucessfully",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Sorry! ID not found",HttpStatus.BAD_REQUEST);
	 }//method
	
	
	
	@PutMapping("/deactiveDeduction/{deductionName}")
	public ResponseEntity<?> deactiveAllowances(@PathVariable String deductionName )
	{
		Deduction deductions = deductionDao.findByDeductionName(deductionName);
		if(deductions != null)
		{
			deductions.setStatus(false);
			deductionDao.save(deductions);
			return new ResponseEntity<>("Deduction Details saved Sucessfully",HttpStatus.CREATED);
		}
	
			 return new ResponseEntity<>("Deduction Name"+""+deductionName+""+"Not Found",HttpStatus.BAD_REQUEST);
		
	}//method	
	
//	@PostMapping("/saveDeduction")
//	public ResponseEntity<?> saveDeduction(@RequestBody Deduction deduction) 
//	{
//		if (deductionDao.existsByDeductionName(deduction.getDeductionName())) {
//			
//			
//			Deduction deductionsUpdate = deductionDao.findByDeductionName(deduction.getDeductionName());
//			deductionsUpdate.setDeductionType(deduction.getDeductionType());
//			deductionsUpdate.setDeductionName(deduction.getDeductionName());
//			deductionsUpdate.setStatus(deduction.isStatus());
//			deduction.setLastModifiedDate(new Date());
//			deductionsUpdate.setStatus(true);
//			deductionDao.save(deductionsUpdate);
//			return ResponseEntity.ok(Responses.builder().message("Deduction Details Updated  Sucessfully").build());
//		} 
//		
//		else {
//			
//			deduction.setStatus(true);
//			deductionDao.save(deduction);
//			return ResponseEntity.ok(Responses.builder().message("Deduction Details Saved Sucessfully").build());
//		}
//		
//		
//	}
	
}//class
