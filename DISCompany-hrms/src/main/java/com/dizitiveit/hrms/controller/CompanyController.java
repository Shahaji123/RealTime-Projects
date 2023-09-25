package com.dizitiveit.hrms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.CompanyDAO;
import com.dizitiveit.hrms.model.Company;
import com.dizitiveit.hrms.pojo.Responses;
@RestController
@RequestMapping("/company")
public class CompanyController {
    
	@Autowired
	private CompanyDAO companyDao;
	
	//getting data from html form
	@PostMapping("/addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company){
		
		if(companyDao.existsByCompanyName(company.getCompanyName())) {
			return ResponseEntity.badRequest().body(Responses.builder().message("Company Already Exists").build());
		}
		else {
		  companyDao.save(company);
			return ResponseEntity.ok(Responses.builder().message("Company Details Saved Successfully.").build());
		}
	}//end of addCompany method
    
	@PatchMapping("/updateCompany/{companyId}")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,@PathVariable("companyId") Integer companyId){
	    Company update=companyDao.findBycompanyId(companyId);
	   
	    if(update!=null) {
	    	update.setCompanyName(company.getCompanyName());
	    	update.setRegistrationNumber(company.getRegistrationNumber());
	    	update.setPanNumber(company.getPanNumber());
	    	update.setEsiNumber(company.getEsiNumber());
	    	update.setPfNumber(company.getPfNumber());
	    	update.setTinNumber(company.getTinNumber());
	    	update.setNumberOfBranches(company.getNumberOfBranches());
	    	update.setStatus(company.getStatus());
	    	update.setLastModifiedDate(company.getLastModifiedDate());
	         
	    	companyDao.save(update);
	    	return ResponseEntity.ok(Responses.builder().message("Update Successfull!").build());
	    	
	    }
	    else {
	    	return ResponseEntity.badRequest().body(Responses.builder().message("Sorry! company Id Not found").build());
	    }
	}//end of updateCompany method
	
	@GetMapping("/getCompanyDetail")
	public ResponseEntity<?> getCompany(){
		 List<Company> list=companyDao.findAll();
		 if(list!=null) {
			 HashMap<String,List<Company>> map=new HashMap<String,List<Company>>();
			 map.put("Company", list);
			 return ResponseEntity.ok(list);			 
		 }
		 else {
			 return ResponseEntity.badRequest().body(Responses.builder().message("record is not available"));
		 }
	}//method
  
	@DeleteMapping("/delete/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable("companyId") Integer companyId){
		 Optional<Company> delete=companyDao.findById(companyId);
		 System.out.println(delete.get());
		 if(delete.isPresent()) {
			 companyDao.delete(delete.get());
				return ResponseEntity.ok(Responses.builder().message("Delete Successfull!").build());
		 }
	      else {
				 return ResponseEntity.badRequest().body(Responses.builder().message("sorry! Id Not Found"));
	    	 
	      }
	}//end of delete method
	
	 
}//class
