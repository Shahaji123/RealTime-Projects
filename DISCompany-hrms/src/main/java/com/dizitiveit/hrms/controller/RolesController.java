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

import com.dizitiveit.hrms.dao.RolesDAO;
import com.dizitiveit.hrms.model.Roles;
import com.dizitiveit.hrms.pojo.Responses;
@RestController
@RequestMapping("/role")
public class RolesController {
  
	@Autowired
	private RolesDAO rolesDao;
	
	@PostMapping("/addRole")
	public ResponseEntity<?> addRoles(@RequestBody Roles roles){
		if(!rolesDao. existsByRoleName(roles.getRoleName())) {
			rolesDao.save(roles);
			return ResponseEntity.ok(Responses.builder().message("RoleS Save Successfully").build());			
		}
		return ResponseEntity.badRequest().body(Responses.builder().message("Role Id Already Existed").build());
	}//method
	
	@PatchMapping("/updateRoles/{roleName}")
	public ResponseEntity<?> updateRoles(@RequestBody Roles roles, @PathVariable("roleName") String roleName)
	{
	    Roles rolesUpdate=rolesDao.findByRoleName(roleName);
		if(rolesUpdate !=null)
		{
		rolesUpdate.setRoleName(roles.getRoleName());
		rolesUpdate.setDescription(roles.getDescription());
		rolesUpdate.setStatus(roles.getStatus());
		rolesDao.save(rolesUpdate);
		return ResponseEntity.ok(Responses.builder().message("Role Details Updated Successfully.").build());
	    } 
			 return ResponseEntity.badRequest().body(Responses.builder().message("Role Name already exist").build()); 
    }//method
	
	  @GetMapping("/getRolesDetails")
	  public ResponseEntity<?> getroles()
	  {
		List<Roles> role=rolesDao.findAll();
		if(role.size()>0)
		{
			HashMap<String,List<Roles>> map=new HashMap<String, List<Roles>>();
			   map.put("Roles", role);
			   return ResponseEntity.ok(map);	
		}

		 return ResponseEntity.badRequest().body(Responses.builder(). message("Data not found").build()); 
      }//method
	  
	     @GetMapping("/getRolesName/{roleName}") 
		 public ResponseEntity<?> getRolesId(@PathVariable String roleName)
	      { 
		   Roles roles=rolesDao.findByRoleName(roleName);
		   if(roles!=null) 
		 {
		   HashMap<String,Roles> map=new HashMap<String, Roles>();
		   map.put("Roles", roles);
		   return ResponseEntity.ok(map);	 
		 } 
	    return ResponseEntity.badRequest().body(Responses.builder(). message("Data not found with Rolename " + roleName).build()); 
			 
	   }//method
	     
	     @GetMapping("/getRoleNames")
		  public ResponseEntity<?> getBranchNames()
		  {
			  List<String> role= rolesDao.findByroleName();
			  HashMap<String,List<String>> map=new HashMap<String,List<String>>();
		      map.put("roleNames", role);
		      return ResponseEntity.ok(map);	  
		  }//method
	     
	     @DeleteMapping("/deleteRoles/{roleId}")
			public ResponseEntity<?> deleteRoles(@PathVariable long roleId)
			{
	    	 Optional<Roles> delet=rolesDao.findById(roleId);
				if(delet.isPresent())
				{
				 rolesDao.delete(delet.get());
				 return ResponseEntity.ok(Responses.builder().message("Role Details Deleted Sucessfully "+ roleId).build());
				}
				 return ResponseEntity.badRequest().body(Responses.builder(). message(" Not found roleId " + roleId).build()); 
				
				
			}			
	
}//class
