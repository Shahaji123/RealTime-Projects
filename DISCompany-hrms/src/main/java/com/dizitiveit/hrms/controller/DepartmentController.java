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

import com.dizitiveit.hrms.dao.BranchDAO;
import com.dizitiveit.hrms.dao.DepartmentDAO;
import com.dizitiveit.hrms.model.Branch;
import com.dizitiveit.hrms.model.Department;
import com.dizitiveit.hrms.pojo.Responses;

@RestController
@RequestMapping("/department")
public class DepartmentController {
   
	@Autowired
	private DepartmentDAO deptDao;
	
	//branch_id is forein key
	@Autowired
    private BranchDAO branchDao;
	
	@PostMapping("/addDept/{branchName}")
	public ResponseEntity<?> addDept(@RequestBody Department department, @PathVariable("branchName") String branchName){
		if(deptDao.existsByDeptName(department.getDeptName()))
		{
			return ResponseEntity.badRequest().body(Responses.builder().message("Department Already Exists").build());
		}//end of if
		else
		{
		Branch branch1=branchDao.findBybranchName(branchName);
		if(branch1!=null) {
		 department.setBranch(branch1);	
		 deptDao.save(department);
		return ResponseEntity.ok(Responses.builder().message("Department Details are Saved Successfully.").build());
		}
		return ResponseEntity.badRequest().body(Responses.builder().message("Sorry! Branch Not found").build());
	 }//end of else
	
  }//method
	
	@PatchMapping("/updateDept/{deptId}")
	public ResponseEntity<?> updateDept(@RequestBody Department department ,@PathVariable("deptId") int deptId)
	{	
		Department deptUpdate=deptDao.findBydeptId(deptId);
		if(deptUpdate!=null)
		{
		deptUpdate.setDeptName(department.getDeptName());
		deptDao.save(deptUpdate);
		
		//return ResponseEntity.ok("Updated Successfully.");
		return ResponseEntity.badRequest().body(Responses.builder().message("Department Details are Updated Sucessfully").build());
		}
		//else
			 return ResponseEntity.badRequest().body(Responses.builder().message("DepartmentId not Found!").build());
	}//method
	  
	//retriving data
	 @GetMapping("/getDeptDetails") 
	 public ResponseEntity<?> Department() {
		 List<Department> department=deptDao.findAll();
		 if(department.size()>0) {
			 HashMap<String, List<Department>> map=new HashMap<String, List<Department>>();
		      map.put("Department", department);
		      return ResponseEntity.ok(map);
		 }  
		 return ResponseEntity.badRequest().body(Responses.builder().message("Data not Found").build());
		   
	}//method
	 
	 @GetMapping("/getDeptId/{deptId}")
	  public ResponseEntity<?> getDeptId(@PathVariable int deptId)
     { 
	     Department dept=deptDao.findBydeptId(deptId);
	     if(dept!=null) 
	     {
	      HashMap<String,Department> map=new HashMap<String, Department>();
	      map.put("Department",dept);
	      return ResponseEntity.ok(map);	 
	     }
	    	 return ResponseEntity.badRequest().body(Responses.builder().message("DeptId not Found").build());	  
      }//method
	 
	 @GetMapping("/getDeptNames")
	  public ResponseEntity<?> getDeptNames()
	  {
		  List<String> dept= deptDao.findBydeptName();
		  HashMap<String,List<String>> map=new HashMap<String,List<String>>();
	      map.put("departmentNames", dept);
	      return ResponseEntity.ok(map);
		  
	  }//method
	 
	 @DeleteMapping("/deleteDept/{deptId}")
		public ResponseEntity<?> deleteDept(@PathVariable("deptId") int deptId)
		{
			Optional<Department> dept=deptDao.findById(deptId);
		     if(dept.isPresent()) 
		     {
			deptDao.delete(dept.get());
			return ResponseEntity.ok(Responses.builder().message("Department Details are deleted Sucessfully").build());
		     }
		    return ResponseEntity.badRequest().body(Responses.builder().message("DepartmentId not Found!").build());
			  
		}//method
	 
}//class
