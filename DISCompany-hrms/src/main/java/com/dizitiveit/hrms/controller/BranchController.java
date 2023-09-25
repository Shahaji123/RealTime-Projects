package com.dizitiveit.hrms.controller;

import java.util.ArrayList;

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

import com.dizitiveit.hrms.dao.BranchDAO;
import com.dizitiveit.hrms.model.Branch;
import com.dizitiveit.hrms.pojo.BranchPojo;
import com.dizitiveit.hrms.pojo.Responses;
@RestController
@RequestMapping("/branch")
public class BranchController {
    
	@Autowired
	private BranchDAO branchDao;
 
	@PostMapping("/addBranch")
	public ResponseEntity<?> addBranch(@RequestBody Branch branch){
		
		if(branchDao.existsByBranchName(branch.getBranchName())) {
			return ResponseEntity.badRequest().body(Responses.builder().message("Sorry! your companyName already existed "));	
	}
	else {
		branchDao.save(branch);
		return ResponseEntity.ok(Responses.builder().message("Branch Details Saved Successfully.").build());
		
	}
 }//method
	
	@PatchMapping("/updateBranch/{branchName}")
	public ResponseEntity<?> updateDept(@RequestBody Branch branch , @PathVariable("branchName") String branchName)
	{
		Branch branchUpdate=branchDao.findBybranchName(branchName);
		if(branchUpdate!=null)
		{
		branchUpdate.setBranchName(branch.getBranchName());
		branchUpdate.setCity(branch.getCity());
		branchUpdate.setState(branch.getState());
		branchUpdate.setCountry(branch.getCountry());
		branchUpdate.setPhoneNumberOne(branch.getPhoneNumberOne());
		branchUpdate.setPhoneNumberTwo(branch.getPhoneNumberTwo());
		branchUpdate.setEmailId(branch.getEmailId());
		branchUpdate.setBranchPremisesType(branch.getBranchPremisesType());
		branchUpdate.setBranchPremisesRent(branch.getBranchPremisesRent());
		branchUpdate.setLastModifiedDate(branch.getLastModifiedDate());
		branchUpdate.setStatus(branch.getStatus());	
		branchDao.save(branchUpdate);
		return ResponseEntity.ok(Responses.builder().message("Branch Details are Updated Successfully.").build());
		}
		else {
			return ResponseEntity.badRequest().body(Responses.builder().message("Branch Name not found").build());
		}
	}//method
	
   @GetMapping("/getBranch")
	public ResponseEntity<?> getBranch(){
	    // List<Branch> list=branchDao.findAllBranchs();
	   List<Branch> list=branchDao.findAll();
	     if(list!=null) {
	    	 //list.sort(t1,t2);
	    	 HashMap<String,List<Branch>> map=new HashMap<String,List<Branch>>();
	    	 map.put("Branch", list);
			 return ResponseEntity.ok(list);	
	     }
	     else {
	    	 return ResponseEntity.badRequest().body(Responses.builder().message("record is not available"));
	     }
	}//method
   
	 @GetMapping("/getBranchName/{branchName}") 
	 public ResponseEntity<?> getBranchId(@PathVariable("branchName") String branchName)
     { 
	   Branch branch=branchDao.findBybranchName(branchName);
	   if(branch!=null) 
	    {
	      HashMap<String,Branch> map=new HashMap<String, Branch>();
	      map.put("Branch", branch);
	      return ResponseEntity.ok(map);	 
	    }
	   else 
		 { 
		   return ResponseEntity.badRequest().body(Responses.builder().message("Data Not Found with branchName").build());
		  }
    }//method
	 
	 @GetMapping("/getBranchNames")
	  public ResponseEntity<?> getBranchNames()
	  {
		  List<String> list= branchDao.findBybranchName();
		  HashMap<String,List<String>> map=new HashMap<String,List<String>>();
	      map.put("branch Names", list);		      
	      return ResponseEntity.ok(map);	  
	  }//method
	 
	    @DeleteMapping("/deleteBranch/{branchId}")
		public ResponseEntity<?> deleteBranch(@PathVariable("branchId") Integer branchId)
		{
		 Optional<Branch> delete=branchDao.findById(branchId);
			if(delete.isPresent())
			{
			branchDao.delete(delete.get());
			return ResponseEntity.ok(Responses.builder().message("Branch Name deleted Successfully!").build());
			}
			else {
				 return ResponseEntity.badRequest().body(Responses.builder(). message(" Not found branchId " + branchId).build());
			}
			
		}//method
	 
	 @PostMapping("/deactiveBranch/{branchId}")
		public ResponseEntity<?> deactiveBranch(@PathVariable("branchId") Integer branchId )
		{
			Branch status= branchDao.findByBranchId(branchId);
			if(status!=null)
			{
				status.setStatus(false);
				branchDao.save(status);
				return ResponseEntity.ok(Responses.builder().message("Branch Details deactivated Sucessfully").build());
			}
			else {
				 return ResponseEntity.badRequest().body(Responses.builder().message("Branch Id "+ " " +status+" "+ " Not Found ").build());
			}
		}//method
	 
	 @GetMapping("ActiveListBranchNames")
	  public ResponseEntity<?> ActiveListBranchNames(){
		  List<Branch> branch=branchDao.findBysetStatus(true);
		  List<BranchPojo> branPojo = new ArrayList<BranchPojo>();
		  for(Branch bran : branch)
		  {
			  BranchPojo branchPojo = new BranchPojo();
			  branchPojo.setBranchId(bran.getBranchId());
			  branchPojo.setBranchName(bran.getBranchName());
			  branchPojo.setCity(bran.getCity());
			  branchPojo.setStatus(bran.getStatus());
			  branPojo.add(branchPojo);
		  }
			
		    HashMap<String,List<BranchPojo>> map=new HashMap<String, List<BranchPojo>>();
		    map.put("Branch", branPojo); 
		    return ResponseEntity.ok(map);
		  }
}