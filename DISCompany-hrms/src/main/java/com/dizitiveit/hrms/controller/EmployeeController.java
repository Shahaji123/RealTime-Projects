package com.dizitiveit.hrms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.BranchDAO;
import com.dizitiveit.hrms.dao.DepartmentDAO;
import com.dizitiveit.hrms.dao.DesignationDAO;
import com.dizitiveit.hrms.dao.EmployeeDAO;
import com.dizitiveit.hrms.dao.RolesDAO;
import com.dizitiveit.hrms.model.Branch;
import com.dizitiveit.hrms.model.Department;
import com.dizitiveit.hrms.model.Designation;
import com.dizitiveit.hrms.model.Employee;
import com.dizitiveit.hrms.model.Roles;
import com.dizitiveit.hrms.pojo.Responses;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDao;
	
	@Autowired
	private BranchDAO branchDao;
	
	@Autowired
	private DepartmentDAO deptDao;
	
	@Autowired
	private DesignationDAO desigDao;
	
	@Autowired
	private RolesDAO rolesDao;
	
	//Registration Employee
	@PostMapping("/registrationEmp/{roleName}/{branchName}/{deptName}/{desigName}")
	 public ResponseEntity<?> registrationEmployee(@RequestBody Employee employee,@PathVariable String roleName,
			  @PathVariable("branchName") String branchName, @PathVariable("deptName") String deptName,@PathVariable("desigName") String desigName){
	   Roles roles=rolesDao.findByRoleName(roleName);
	   
	     Branch branch=branchDao.findBybranchName(branchName);
	     Department dept=deptDao.findByDeptName(deptName);
	     Designation design=desigDao.findByDesigName(desigName);
	     
	    if(employeeDao.existsByOfficialEmailId(employee.getOfficialEmailId())) {
	    	 return ResponseEntity.badRequest().body(Responses.builder().message("Employee Email Already Exists").build());
 
	        }
	     if(employeeDao.existsByEmployeeId(employee.getEmployeeId())) {
	    	 return ResponseEntity.badRequest().body(Responses.builder().message("Employee Email Already Exists").build());
	     }
	     if(employeeDao.existsByPhoneNumber(employee.getPhoneNumber())) {
	    	 return ResponseEntity.badRequest().body(Responses.builder().message("Employee Email Already Exists").build());
	     }
	     if(employeeDao.existsByAdharNumber(employee.getAdharNumber())) {
	    	 return ResponseEntity.badRequest().body(Responses.builder().message("Employee Email Already Exists").build());
	     }
	     if(employeeDao.existsByPanCardNumber(employee.getPanCardNumber())) {
	    	 return ResponseEntity.badRequest().body(Responses.builder().message("Employee Email Already Exists").build());
	     }
    	
	     if(roles!=null && branch!=null && dept!=null ) {
	    	    employee.setRoles(roles);
				employee.setCreatedAt(new Date());
			//	employee.setSupervisor(employeeSupervisor);
				employee.setStatus(true);
				employee.setBranch(branch);
				employee.setDepartment(dept);
				employee.setDesg(design);		
			    
	    	    employeeDao.save(employee);
				return ResponseEntity.ok(Responses.builder().message("Employee Detail updated successfyll").build());
	     }	     
	      
    	 return ResponseEntity.badRequest().body(Responses.builder().message("Employee Email Already Exists").build());
  }//method
	
	//Update Employee
	@PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee,@PathVariable String employeeId,@RequestParam(name = "desigName", required = false) String desigName , @RequestParam(name="roleName",required=false) String roleName, @RequestParam(name="branchName") String branchName){
  	 Employee emp=employeeDao.findByEmployeeId(employeeId);
  	  if(emp!=null) {
  		  emp.setDateOfJoining(employee.getDateOfJoining());
  		  emp.setFirstName(employee.getFirstName());
  		  emp.setLastName(employee.getLastName());
  		  emp.setGender(employee.getGender());
  		  emp.setPermanentAddress(employee.getPermanentAddress());
  		  emp.setPhoneNumber(employee.getPhoneNumber());
  		  emp.setAdharNumber(employee.getAdharNumber());
  		  emp.setPanCardNumber(employee.getPanCardNumber());
  		  emp.setOfficialEmailId(employee.getOfficialEmailId());
  		  emp.setNationality(employee.getNationality());
  		  
  		  
  		  Designation desig=desigDao.findByDesigName(desigName);
  		  Roles roles=rolesDao.findByRoleName(roleName);
  		  Branch branch=branchDao.findBybranchName(branchName);
  		  
  		  if(desig!=null) {
  			  emp.setDesg(desig);
  			
  		  }
  		  else {
  			  return new ResponseEntity<String>("Designation Not Found",HttpStatus.BAD_REQUEST);
  		  }
  		  
  		  if(roles!=null) {
  			  emp.setRoles(roles);
  		  }
  		  else {
  			  return new ResponseEntity<String>("Roles not found!",HttpStatus.CREATED);
  		  }
  		  if(branch!=null) {
  			  emp.setBranch(branch);
  		  }
  		  else {
  			  return new ResponseEntity<String>("Branch Name not found!",HttpStatus.BAD_REQUEST);
  		  }
  		  
  		     employeeDao.save(emp);
	  		  return new ResponseEntity<String>("update Successfully",HttpStatus.CREATED);
  		
  	  }
  	   return new ResponseEntity<String>("Employee Id Not Found!",HttpStatus.CREATED);

   }//method
	
	//Get Details
	@GetMapping("/getEmployee")
	public ResponseEntity<?> getEmployee(){
	 List<Employee> emp=employeeDao.findAll();
	    if(emp!=null) {
	    	HashMap<String,List<Employee>> map=new HashMap<String,List<Employee>>();
	    	map.put("Employee", emp);
	    	return new ResponseEntity<>(map,HttpStatus.CREATED);
	    	
	    }
	    return new ResponseEntity<>("Employee Detail Not Available ",HttpStatus.BAD_REQUEST);
	}//method
}//class
