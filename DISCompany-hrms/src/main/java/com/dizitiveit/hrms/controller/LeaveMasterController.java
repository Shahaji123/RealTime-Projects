package com.dizitiveit.hrms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.LeaveMasterDAO;
import com.dizitiveit.hrms.model.LeaveMaster;
import com.dizitiveit.hrms.pojo.LeaveMasterPojo;

@RestController
@RequestMapping("/leaveMaster")
public class LeaveMasterController {

	@Autowired
	private LeaveMasterDAO leaveMasterDao;
	
	@PostMapping("/addLeaveMaster")
	public ResponseEntity<?> addLeOaveMaster(@RequestBody LeaveMaster leaveMaster){
		if(!leaveMasterDao.existsByLeaveType(leaveMaster.getLeaveType())) {
		   
			leaveMasterDao.save(leaveMaster);
			return new ResponseEntity<>("Leave saved successfully",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Your Leave already Existed!",HttpStatus.BAD_REQUEST);
	}//method
	
	@PutMapping("/updateLeaveMaster/{leaveId}")
	public ResponseEntity<?> updateLeaveMaster(@RequestBody LeaveMaster leaveMaster,@PathVariable("leaveId") Long leaveId ){
		LeaveMaster leaveMasterUpdate=leaveMasterDao.findByLeaveId(leaveId);
		if(leaveMasterUpdate!=null)
		{
	     leaveMasterUpdate.setLeaveType(leaveMaster.getLeaveType());
		leaveMasterUpdate.setCarryForward(leaveMaster.getCarryForward());
		leaveMasterUpdate.setPeriod(leaveMaster.getPeriod());
		leaveMasterUpdate.setCount(leaveMaster.getCount());
		leaveMasterUpdate.setStatus(true);
		leaveMasterDao.save(leaveMasterUpdate);
		return new ResponseEntity<>("LeaveMaster Updated  Successfully",HttpStatus.CREATED);
		
	    }
		return new ResponseEntity<>("Sorry! ID Not found",HttpStatus.BAD_REQUEST);
	}//method
	

	@GetMapping("/leaveMaserDetails")
	public ResponseEntity<?> leaveMasterDetails(){
		List<LeaveMaster> list=leaveMasterDao.findAll();
		if(list.size()>0) {
			Map<String,List<LeaveMaster>> map=new HashMap<String,List<LeaveMaster>>();
		     map.put("LeaveMaster",list);
		return ResponseEntity.ok(list);
		}
		return new ResponseEntity<>("Record is empty",HttpStatus.BAD_REQUEST);
	}//method
	

	 @GetMapping("/getLeaveTypes")
	  public ResponseEntity<?> getLeaveTypes()
	  {
		  List<String> leaveType= leaveMasterDao.findByLeaveType(true);
		  HashMap<String,List<String>> map=new HashMap<String,List<String>>();
	      map.put("LeaveTypes", leaveType);
	      return ResponseEntity.ok(map);
		  
	  }
	
	 @GetMapping("/leaveDetails/{leaveId}")
	 public ResponseEntity<?> leaveDetails(@PathVariable long leaveId){
		LeaveMaster leave=leaveMasterDao.findByLeaveId(leaveId);
		if(leave!=null) {
		Map<String,LeaveMaster> map=new TreeMap<String,LeaveMaster>();
		map.put("Ieave Id", leave);
		return ResponseEntity.ok(map);
		}
		return new ResponseEntity<>("Sorry! Id not found",HttpStatus.BAD_REQUEST);
	 }//method
	
	@PutMapping("/deActivateLeave/{leaveId}")
	public ResponseEntity<?> deActivateLeave(@PathVariable long leaveId){
		LeaveMaster leave=leaveMasterDao.findByLeaveId(leaveId);
		  if(leave!=null) {
			  leave.setStatus(false);
			  leaveMasterDao.save(leave);
			  return new ResponseEntity<>("Your Id deactivated !",HttpStatus.CREATED);
		  }
		  return new ResponseEntity<>("Id Not found!",HttpStatus.BAD_REQUEST);
		
	}//method
	
	
	@PutMapping("/activateLeave/{leaveId}")
	public ResponseEntity<?> activateLeave(@PathVariable long leaveId){
		LeaveMaster leave=leaveMasterDao.findByLeaveId(leaveId);
		if(leave!=null) {
			leave.setStatus(true);
			leaveMasterDao.save(leave);
			return new ResponseEntity<>("your Id activated",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Sorry! Id not found!",HttpStatus.BAD_REQUEST);
	}//method
	
	@GetMapping("/activeListOfStatus")
	public ResponseEntity<?> activeListOfStatus(){
		
		List<LeaveMaster> list=leaveMasterDao.findByAllStatus(true);
	      if(list!=null) {
	    	  
	    	  List<LeaveMasterPojo> alist=new ArrayList<LeaveMasterPojo>();
	    	
	    	  for(LeaveMaster leave:list) {
	    		  LeaveMasterPojo lmPojo=new LeaveMasterPojo();
	    		  lmPojo.setLeaveId(leave.getLeaveId());
	    		  lmPojo.setLeaveType(leave.getLeaveType());
	    		  lmPojo.setStatus(leave.isStatus());
	    		  alist.add(lmPojo);
	    	  }
	    	  Map<String, List<LeaveMasterPojo>> map=new  TreeMap<String, List<LeaveMasterPojo>>();
	    	  map.put("Active List",alist);
	    	  return ResponseEntity.ok(map);
	      }
	    return new ResponseEntity<>("Record not Available ",HttpStatus.CREATED);
	}//method
	
	 
    @GetMapping("/deactiveListOfStatus")
	  public ResponseEntity<?> deactivelistOfStatus(){
		  List<LeaveMaster> leaveMaster=leaveMasterDao.findByAllStatus(false);
		  List<LeaveMasterPojo> leavePojoList = new ArrayList<LeaveMasterPojo>();
		  for(LeaveMaster leave : leaveMaster)
		  {
			  LeaveMasterPojo leavePojo = new LeaveMasterPojo();
			  leavePojo.setLeaveId(leave.getLeaveId());
			  leavePojo.setLeaveType(leave.getLeaveType());
			  leavePojo.setStatus(leave.isStatus());
			  leavePojoList.add(leavePojo);
		      
		  }
		    HashMap<String,List<LeaveMasterPojo>> map=new HashMap<String, List<LeaveMasterPojo>>();
		    map.put("LeaveMaster", leavePojoList); 
		    return ResponseEntity.ok(map);
		  }

	
	
	
	
	
}//class
