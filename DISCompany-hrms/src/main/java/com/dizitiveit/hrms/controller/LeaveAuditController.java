package com.dizitiveit.hrms.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.EmployeeDAO;
import com.dizitiveit.hrms.dao.LeaveAuditDAO;
import com.dizitiveit.hrms.dao.LeaveMasterDAO;
import com.dizitiveit.hrms.model.Employee;
import com.dizitiveit.hrms.model.LeaveAudit;
import com.dizitiveit.hrms.model.LeaveMaster;
import com.dizitiveit.hrms.pojo.LeaveAuditPojo;
@RestController
@RequestMapping("/leaveAudit")
public class LeaveAuditController {

   @Autowired
   private EmployeeDAO employeeDao;
   
   @Autowired
   private LeaveMasterDAO leaveMasterDao;
   
   @Autowired
   private LeaveAuditDAO leaveAuditDao;
 
   @PostMapping("/addLeaveAudit/{employeeId}/{leaveId}")
   public ResponseEntity<?> addLeaveAudit(@RequestBody LeaveAudit leaveAudit,@PathVariable("employeeId") String employeeId,@PathVariable("leaveId")long leaveId){
	   Employee employee=employeeDao.findByEmployeeId(employeeId);
	   System.out.println(employee);  
	   LeaveMaster leave=leaveMasterDao.findByLeaveId(leaveId);
	   System.out.println(leave);
	   
	   if(employee!=null && leave!=null) {
		   leaveAudit.setEmployee(employee);
		   leaveAudit.setLeaveMaster(leave);
		   leaveAudit.setLastCreated(new Date());
		   leaveAuditDao.save(leaveAudit);
		   return new ResponseEntity<>("LeaveAudit save Successfully!",HttpStatus.CREATED);
	   }
	   return new ResponseEntity<>("Id Not found ",HttpStatus.BAD_REQUEST);
   }//method
   
   @GetMapping("/getCurrentLeaveDashboard/{employeeId}")
   public ResponseEntity<?> getCurrentLeaveDashboard(@PathVariable String employeeId){
	   Employee emp=employeeDao.findByEmployeeId(employeeId);
	   List<LeaveAudit> list=leaveAuditDao.findAll();
	   
	   List<LeaveAuditPojo> auditList = new ArrayList<LeaveAuditPojo>();
	   
	   for(LeaveAudit audit:list) {
		   LeaveAudit leave=leaveAuditDao.findByQuarter(emp.getEmployeeCode(),audit.getLeaveAuditId());
		   System.out.println(leave);
		   if(leave!=null) {
			   LeaveAuditPojo auditPojo=new LeaveAuditPojo();			 
			   //System.out.println("EmployeeCode:" +auditDetails.getEmployee().getEmployeeId());
			   auditPojo.setEmployeeId(audit.getEmployee().getEmployeeId());
               auditPojo.setLeaveId(audit.getLeaveMaster().getLeaveId());
			   auditPojo.setLeaveType(audit.getLeaveMaster().getLeaveType());
			   auditPojo.setLeaveBalance(audit.getLeaveBalance());
			   auditPojo.setLeavesAwaitingApproval(audit.getLeavesAwaitingApproval());
		       auditList.add(auditPojo);
	       }
		   HashMap<String, List<LeaveAuditPojo>> map=new HashMap<String,List<LeaveAuditPojo>>();
			  map.put("LeaveAudit", auditList);
			  return ResponseEntity.ok(map);
	   }//end of loop
	   
	
	   return new ResponseEntity<>("Id not found",HttpStatus.BAD_REQUEST);
	   
	 
	   
   }//method
   
   @GetMapping("/getLeavesAudit/{employeeId}")
   public ResponseEntity<?> getLeavesAudit(@PathVariable String employeeId){
	   Employee emp=employeeDao.findByEmployeeId(employeeId);
	   List<LeaveAudit> leaveEmpEntitlementDetails =leaveAuditDao.findByEmployee(emp.getEmployeeCode());
      List<LeaveAuditPojo> leaveEmpEntitlementList = new ArrayList<LeaveAuditPojo>();
      
      for(LeaveAudit audit : leaveEmpEntitlementDetails) {
    	  LeaveAuditPojo leaveAuditPojo = new LeaveAuditPojo();
    	  leaveAuditPojo.setLeaveId(audit.getLeaveAuditId());
    	  leaveAuditPojo.setEmployeeId(audit.getEmployee().getEmployeeId());
		  leaveAuditPojo.setLeaveType(audit.getLeaveMaster().getLeaveType());
		  leaveAuditPojo.setFinancialYear(audit.getFinancialYear()); 
		  leaveAuditPojo.setOpeningBalance(audit.getOpeningBalance());
	  DateFormat dateOfJoiningFormat = new SimpleDateFormat("dd-MM-yyyy");
	  leaveAuditPojo.setLastCredited(dateOfJoiningFormat.format(audit.getLastCreated()));
	  
	  leaveAuditPojo.setLeavesCredited(audit.getLeavesCredited());
	  leaveAuditPojo.setLeavesApproved(audit.getLeavesApproved());
	  leaveAuditPojo.setLeavesAwaitingApproval(audit.getLeavesAwaitingApproval());
	  leaveAuditPojo.setLeaveBalance(audit.getLeaveBalance()); 
	  //leaveEmpEntitlementPojo.setEmployee(leaveEmpEntitlement.getEmployee());
	  leaveEmpEntitlementList.add(leaveAuditPojo);
      }//end of loop
      HashMap<String, List<LeaveAuditPojo>> map = new HashMap<String,List<LeaveAuditPojo>>(); 
	  map.put("LeavesAudit",leaveEmpEntitlementList); 
	  return ResponseEntity.ok(map); 
   }//method
   
   
   
   
}
