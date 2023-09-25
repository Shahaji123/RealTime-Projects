package com.dizitiveit.hrms.controller;

import java.util.Date;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.dao.HolidaysDAO;
import com.dizitiveit.hrms.model.Holidays;
import com.dizitiveit.hrms.pojo.Responses;

@RestController
@RequestMapping("/holiday")
public class HolidaysController {

	@Autowired
	private HolidaysDAO holidaysDao;
	
	@PostMapping("/addHolidays")
	public ResponseEntity<?> addHolidays(@RequestBody Holidays holidays){
		
		holidays.setLastModifiedDate(new Date());	
	    holidaysDao.save(holidays);
		return ResponseEntity.ok(Responses.builder().message("Holidays Saved Successfully.").build());	
	}//method
	
	@PatchMapping("/updateHolidays/{holidaysId}")
	public ResponseEntity<?> updateHolidays(@RequestBody Holidays holidays, @PathVariable("holidaysId") int holidaysId) {
		
		Holidays empHolidaysUpdate = holidaysDao.findByHolidaysId(holidaysId);
		if (empHolidaysUpdate != null) {
			empHolidaysUpdate.setHolidayName(holidays.getHolidayName());
			empHolidaysUpdate.setHolidayDate(holidays.getHolidayDate());
			empHolidaysUpdate.setHolidayType(holidays.getHolidayType());
			holidaysDao.save(empHolidaysUpdate);	
			return ResponseEntity.ok("Holidays details Updated Successfully.");
		} 
		return new ResponseEntity<>(" Holidays ID Not Found ",HttpStatus.BAD_REQUEST);
	
	}//method
	
	@GetMapping("/getHolidays")
	public ResponseEntity<?> getEmpHolidays() {
		List<Holidays> holidays = holidaysDao.findAll();
		if (holidays.size() > 0) {
			HashMap<String, List<Holidays>> map = new HashMap<String, List<Holidays>>();
			map.put("Holidays", holidays);
			return ResponseEntity.ok(map);
		} 
			return ResponseEntity.ok("Data not found.");
		
	}//method
	
	@GetMapping("getHolidaysMonth/{month}/{year}")
	public ResponseEntity<?> getHolidaysMonth(@PathVariable int month, @PathVariable int year) {
		List<Holidays> holidays = holidaysDao.getbyholidaysMonth(month,year);
		if (holidays!=null) 
		{
			HashMap<String, List<Holidays>> map = new HashMap<String, List<Holidays>>();
			map.put("Holidays", holidays);
			return ResponseEntity.ok(map);
		} 
			return ResponseEntity.ok("Data not found.");
		
	}//method
	
	@DeleteMapping("/deleteHolidays/{holidaysId}")
	public ResponseEntity<?> deleteHolidays(@PathVariable int holidaysId) {
		
		if (holidaysDao.existsByHolidaysId(holidaysId)) 
		{
			holidaysDao.deleteById(holidaysId);	
			return ResponseEntity.ok("Holidays Details Deleted Successfully.");
			
		} 
		
		return new ResponseEntity<>(" Not found empHolidaysId ",HttpStatus.BAD_REQUEST);		
		
	}//method
	
  
}
