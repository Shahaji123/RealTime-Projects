package com.dizitiveit.hrms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.AdditionalDetailDAO;
import com.dizitiveit.hrms.dao.EmployeeDAO;
import com.dizitiveit.hrms.model.AdditionalDetails;
import com.dizitiveit.hrms.model.Employee;
import com.dizitiveit.hrms.pojo.Responses;

@RestController
@RequestMapping("/additional")
public class AdditionalController {
	
	@Autowired
	private AdditionalDetailDAO additionalDao;
	
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	@PostMapping("/addAdditionalDetails/{employeeId}")
	public ResponseEntity<?> addAdditionalDetails(@RequestBody AdditionalDetails additionalDetails,@PathVariable String employeeId)
	{
		
		Employee employee=employeeDao.findByEmployeeId(employeeId);
		AdditionalDetails addiDetailsUpdate= additionalDao.findByEmployee(employee);
		if(addiDetailsUpdate !=null)
			{
				
			addiDetailsUpdate.setAlternateNumber(additionalDetails.getAlternateNumber());
			addiDetailsUpdate.setWeddingDay(additionalDetails.getWeddingDay());
			addiDetailsUpdate.setPassportNumber(additionalDetails.getPassportNumber());
			//addiDetailsUpdate.setPhoto(additionalDetails.getPhoto());
			addiDetailsUpdate.setPhotoDisplay(additionalDetails.getPhotoDisplay());
			additionalDao.save(addiDetailsUpdate);
			return ResponseEntity.ok(Responses.builder().message("Additional Details Updated Successfully.").build());
			}
			else 
			 { 
	            additionalDetails.setEmployee(employee);
	            additionalDao.save(additionalDetails);
		        return ResponseEntity.ok(Responses.builder().message("Additional Details are saved sucessfully").build());
			 }
		 
	}//method
	
	@PutMapping("/updateAdditionalDetails/{employeeId}")
	public ResponseEntity<?> updateAdditionalDetails(@RequestBody AdditionalDetails additionalDetails , @PathVariable String employeeId)
	{
		Employee employee = employeeDao.findByEmployeeId(employeeId);
		AdditionalDetails addiDetailsUpdate= additionalDao.findByEmployee(employee);
		if(addiDetailsUpdate !=null)
		{
		
	    addiDetailsUpdate.setAlternateNumber(additionalDetails.getAlternateNumber());
		addiDetailsUpdate.setWeddingDay(additionalDetails.getWeddingDay());
		addiDetailsUpdate.setPassportNumber(additionalDetails.getPassportNumber());
		//addiDetailsUpdate.setPhoto(additionalDetails.getPhoto());
		addiDetailsUpdate.setPhotoDisplay(additionalDetails.getPhotoDisplay());
		additionalDao.save(addiDetailsUpdate);
		return ResponseEntity.ok(Responses.builder().message("Additional Details Updated Successfully.").build());
		}
	
			 return ResponseEntity.badRequest().body(Responses.builder().message("EmployeeID not found").build()); 
		
	}//method
	
	@GetMapping("/getAdditionalDetails")
	 public ResponseEntity<?> AdditionalDetails() {
	 List<AdditionalDetails> additionalDetails=additionalDao.findAll();
	 if(additionalDetails.size()>0) {
		 HashMap<String,List<AdditionalDetails>> map=new HashMap<String, List<AdditionalDetails>>();
		   map.put("AdditionalDetails", additionalDetails);
		   return ResponseEntity.ok(map);
	 }  
		return ResponseEntity.badRequest().body(Responses.builder().message("Data not Found").build());
	
	}//method
	
	       @DeleteMapping("/deleteAdditionalDetails/{additionalId}")
			public ResponseEntity<?> deleteAdditionalDetails(@PathVariable int additionalId)
			{
				Optional<AdditionalDetails> additionalDetails=additionalDao.findByadditionalId(additionalId);
				if(additionalDetails.isPresent())
				{
				additionalDao.deleteById(additionalId);
				return ResponseEntity.ok(Responses.builder().message("Additional Details are Deleted successfully").build());
				}			
					 return ResponseEntity.badRequest().body(Responses.builder().message("AdditionalDetails ID not found").build()); 
		
			}//method
}
