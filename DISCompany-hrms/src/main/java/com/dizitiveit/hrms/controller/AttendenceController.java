package com.dizitiveit.hrms.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

import com.dizitiveit.hrms.dao.AttendanceDAO;
import com.dizitiveit.hrms.dao.AttendanceSummaryDAO;
import com.dizitiveit.hrms.dao.EmployeeDAO;
import com.dizitiveit.hrms.dao.HolidaysDAO;
import com.dizitiveit.hrms.model.Attendance;
import com.dizitiveit.hrms.model.AttendanceSummary;
import com.dizitiveit.hrms.model.Employee;
import com.dizitiveit.hrms.pojo.AttendPojo;
import com.dizitiveit.hrms.pojo.AttendancePojo;

@RestController
@RequestMapping("/attendance")
public class AttendenceController {
    
	

	@Autowired
	private AttendanceDAO attendanceDao;
	
	@Autowired
	private EmployeeDAO employeeDao;

	@Autowired
	private AttendanceSummaryDAO attendanceSummaryDao;
	
	@Autowired
	private HolidaysDAO holidaysDao;
	
	 @PostMapping("/addAttendence/{employeeId}")
	 public ResponseEntity<String> addAttendence(@RequestBody Attendance attendance,@PathVariable("employeeId") String employeeId) throws ParseException {
		Employee emp=employeeDao.findByEmployeeId(employeeId);
		System.out.println(emp);
		
		Attendance existing=attendanceDao.findByattendanceDay(emp.getEmployeeCode(),attendance.getAttendanceDay());
		//System.out.println(existing);
		if(existing==null) {
			attendance.setEmployee(emp);
			attendance.setCreatedAt(new Date());
			ZoneId zone1=ZoneId.of("Asia/Kolkata");
			LocalTime time=LocalTime.now(zone1);
			System.out.println(time);
			
			    String time1= attendance.getInTime().toString();
			    String time2 =attendance.getOutTime().toString();

			 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");  
			   Date d1 = null;
			   Date d2 = null;
			   
			   d1 = format.parse(time1);//may be throws exception
		       d2 = format.parse(time2);
//		       
//		       System.out.println(d1.getTime());
//		       System.out.println(d2.getTime());
		       
		       long diff = d2.getTime() - d1.getTime();
		       long diffHours = diff / (60 * 60 * 1000);  
		      
		       String strHours=String.valueOf(diffHours);
		       System.out.println(strHours);
		       long diffMinutes =  (diff / (60 * 1000)) % 60;  
		       String strMinutes=String.valueOf(diffMinutes);
		       
		       long diffSeconds = (diff / 1000) % 60 ;  
		       String strSeconds=String.valueOf(diffSeconds);
		       attendance.setTotalHours(strHours+ ":" + strMinutes+ ":" +strSeconds);
		     //  System.out.println(attendance.getTotalHours());
		       attendanceDao.save(attendance);
		       return new ResponseEntity<>("Attendance Details Saved Successfully",HttpStatus.CREATED);
	
		}
		return new ResponseEntity<>("Attendance Not Successfully",HttpStatus.BAD_REQUEST);
	}//method
	 
	 @PutMapping("/updateAttendanceDetails/{attendanceId}/{month}/{year}")
	 public ResponseEntity<?> updateAttendance(@RequestBody Attendance attendance,@PathVariable("attendanceId") int attendanceId,@PathVariable("month") long month,@PathVariable("year") long year){
        System.out.println(attendanceId);
         Attendance attendUpdate=attendanceDao.findByattendanceId(attendanceId,month,year);
        
           if(attendUpdate!=null) {
        	    attendUpdate.setAttendanceDay(attendance.getAttendanceDay());
   			    attendUpdate.setInTime(attendance.getInTime());
   			    attendUpdate.setOutTime(attendance.getOutTime());
   		        ZoneId zone1 = ZoneId.of("Asia/Kolkata");
   	            LocalTime time = LocalTime.now(zone1);   
   	            
   	            String time1=attendUpdate.getInTime().toString();
   	            String time2=attendUpdate.getOutTime().toString();
   	         Date d1=null;
   	         Date d2=null;
   	      SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
   	            try {
   	            	  d1=format.parse(time1);
   	            	  d2=format.parse(time2);
   	            	
   	            }catch(ParseException e) {
   	            	e.printStackTrace();
   	            }//catch
   	           long diff = d2.getTime() - d1.getTime();
   	           System.out.println(diff);
   	           long diffHours = diff / (60 * 60 * 1000);  
               String strHours=String.valueOf(diffHours);
               
               long diffMinutes =  (diff / (60 * 1000)) % 60;  
               String strMinutes=String.valueOf(diffMinutes);
              
               long diffSeconds = (diff / 1000) % 60 ;  
               String strSeconds=String.valueOf(diffSeconds);
               attendUpdate.setTotalHours(strHours+ ":" + strMinutes+ ":" +strSeconds);
               attendUpdate.setLastModifiedDate(LocalDate.now());
               System.out.println(LocalDate.now());
               
               System.out.println(LocalTime.now());
   			  attendanceDao.save(attendUpdate);
   	  	 return new ResponseEntity<>("Updated Successfully",HttpStatus.CREATED);
          }
          return new ResponseEntity<>("Sorry Id Not found!",HttpStatus.BAD_REQUEST);
		
	 }//method
	 
	
	    @GetMapping("/getAttendanceDetails")
		public Map getAttendenceDetails(){
		List<Attendance>  list=attendanceDao.findAll();
		Map<String,List<Attendance>> map=new HashMap<String,List<Attendance>>();
		  map.put("Details", list);
	    	return map;
		}//method
	    
	    @GetMapping("getAttendanceDetails/{employeeId}")
	    public ResponseEntity<?> getAttendanceDetails(@PathVariable String employeeId){
	    	Employee id=employeeDao.findByEmployeeId(employeeId);
	        List<Attendance> list=attendanceDao.findByEmployee(id);
	        List<AttendancePojo> list1=new ArrayList<AttendancePojo>();
	         if(id!=null) {
	        	
	        	 
	        	 AttendancePojo  aPojo=new AttendancePojo();
	        	 //loop to store the value in attendance pojo
	        	 for(Attendance attend:list) {
	        		 aPojo.setEmployeeId(attend.getEmployee().getEmployeeId());
	        		 aPojo.setAttendanceId(attend.getAttendanceId());
	        		 String empName=attend.getEmployee().getFirstName() + " " + attend.getEmployee().getLastName();
	        		 aPojo.setEmpName(empName);
	        	     
	        		 DateFormat dfAttendance = new SimpleDateFormat("yyyy-MM-dd");
	    	   	     if(attend.getAttendanceDay()!=null)
	    	   	     {
	    	   	        aPojo.setAttendanceDay(dfAttendance.format(attend.getAttendanceDay()));
	    	   	      }	  
	    	   	     
	    	   	   //DateFormat dfIn = new SimpleDateFormat("HH:mm:ss");
	    	   		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    		     if(attend.getInTime()!=null) 
	    		     {
	    		     aPojo.setInTime(formatter.format(attend.getInTime()));
	    		  
	    		      }
	    		     
	    		     DateTimeFormatter formatt = DateTimeFormatter.ofPattern("HH:mm:ss");
	   			     if(attend.getOutTime()!=null)
	   			     {
	   			     aPojo.setOutTime(formatt.format(attend.getOutTime())); 
	   			    }
	   			     
	   			  aPojo.setTotalHours(attend.getTotalHours());
	   			list1.add(aPojo); 
	    	   	     
	        	 }//end of loop
	        	 HashMap<String, List<AttendancePojo>> map=new HashMap<String,List<AttendancePojo>>();
     	          map.put(employeeId, list1);
	        	 return new ResponseEntity<>(map,HttpStatus.CREATED);
	         }
	        return new ResponseEntity<>("Sorry ID Not Found!",HttpStatus.BAD_REQUEST);
	     }//method

	  @DeleteMapping("/delete/{attendanceId}")  
      public ResponseEntity<String> deleteId(@PathVariable Integer attendanceId){
    	   Optional<Attendance> id=attendanceDao.findByAttendanceId(attendanceId);
    	   
    	   if(id.isPresent()) {
    		   attendanceDao.delete(id.get());
    		   return new ResponseEntity<>("Successfully Deleted id"+id,HttpStatus.CREATED);
    	   }
    	   return new ResponseEntity<>("Sorry Id Not found",HttpStatus.BAD_REQUEST);
      }//method
	 
	  @PostMapping("/webClockIn/{employeeId}")
	  public ResponseEntity<String> webClockIn(@PathVariable String employeeId){
		  Employee id=employeeDao.findByEmployeeId(employeeId);
		  Attendance attendance=new Attendance();
		  attendance.setAttendanceDay(new Date());
		  System.out.println(new Date());
		  LocalDate localDate=new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		  //System.out.println(localDate);
		  //System.out.println(LocalDate.now());
		 long date=localDate.getDayOfMonth();
		 long month=localDate.getMonthValue();
		 long year=localDate.getYear();
		 System.out.println(date+"  "+month+" "+year);
		 
		 Attendance attend=attendanceDao.getByAttendanceDay(id.getEmployeeCode(), date, month, year);
		 //System.out.println(attend);
		 if(attend==null) {
//			    ZoneId zone1 = ZoneId.of("Asia/Kolkata");
//	    	    LocalTime time = LocalTime.now(zone1);
//	    	    System.out.println(time);
//	    	    System.out.println(LocalTime.now());
	    	    attendance.setInTime(LocalTime.now());
	    	    attendance.setEmployee(id);
	    	    attendanceDao.save(attendance);
	    	    return new ResponseEntity<>("Thanks You",HttpStatus.CREATED);
		 }//if
		  return new ResponseEntity<>("Sorry! Try again",HttpStatus.BAD_REQUEST);
	  }//method
	  
	  public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		  long diffInMillies = date1.getTime() - date2.getTime();
		  System.out.println("Static "+diffInMillies);
		  return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS); 
		  }//method
	  
	 
	  @PostMapping("/webClockOut/{employeeId}")
	  public ResponseEntity<String> webClockOut(@PathVariable String employeeId){
			Employee employee = employeeDao.findByEmployeeId(employeeId);
			LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		     long day = localDate.getDayOfMonth();
		     long month = localDate.getMonthValue();
			 long year = localDate.getYear();
			System.out.println(localDate);	
			Attendance attendanceExisting = attendanceDao.getByAttendanceDay(employee.getEmployeeCode(),day,month,year);
		      System.out.println(attendanceExisting);
		      if(attendanceExisting!=null) {
		    	  ZoneId zone1 = ZoneId.of("Asia/Kolkata");
		    	    LocalTime time = LocalTime.now(zone1);
	               System.out.println(time);
	               attendanceExisting.setOutTime(time);
	               String time1= attendanceExisting.getInTime().toString();
	               String time2 =attendanceExisting.getOutTime().toString();
	               SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	               Date d1 = null;
	               Date d2 = null;
	               try {
	                   d1 = format.parse(time1);
	                   d2 = format.parse(time2);
	               } catch (ParseException e) {
	                   e.printStackTrace();
	               }
	               long diff = d2.getTime() - d1.getTime();
		              
	               long diffHours = diff / (60 * 60 * 1000);
	               String strHours=String.valueOf(diffHours);
	             
	               long diffMinutes =  (diff / (60 * 1000)) % 60;
	               String strMinutes=String.valueOf(diffMinutes);
	              
	               long diffSeconds = (diff / 1000) % 60 ;
	               String strSeconds=String.valueOf(diffSeconds);
	             
	               attendanceExisting.setTotalHours(strHours+ ":" + strMinutes+ ":" +strSeconds);
	               attendanceDao.save(attendanceExisting);
	               AttendanceSummary attendanceSummary = new AttendanceSummary();
	               attendanceSummary.setOutTime(time);
	               attendanceSummary.setAttendanceDay(new Date());
	               attendanceSummary.setEmployee(employee);
	               attendanceSummaryDao.save(attendanceSummary);
		      return new ResponseEntity<>("Thanks Successfully ",HttpStatus.CREATED);
		      }
		      return new ResponseEntity<>("Sorry Id Not Found!",HttpStatus.BAD_REQUEST); 
		     }//method
	  
	  @GetMapping("/getTodayAttendance/{employeeId}")
	  public ResponseEntity<?> getTodayAttendance(@PathVariable String employeeId){
		  
		  Employee emp=employeeDao.findByEmployeeId(employeeId);
		  LocalDate date=new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		  System.out.println(date);
		  long day=date.getDayOfMonth();
		  long month=date.getMonthValue();
		  long year=date.getYear();
		  Attendance attendance=attendanceDao.getByAttendanceDay(emp.getEmployeeCode(), day, month, year);
		  System.out.println(attendance);
		  if(attendance!=null) {
			  AttendancePojo attendancePojo=new AttendancePojo();
		      DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		      if(attendance.getAttendanceDay()!=null) {
		    	  attendancePojo.setAttendanceDay(df.format(attendance.getAttendanceDay()));
		    	  
		      }
		      String name=emp.getFirstName()+" "+emp.getLastName();
		      attendancePojo.setEmpName(name);
		      attendancePojo.setEmployeeId(attendance.getEmployee().getEmployeeId());
		      attendancePojo.setAttendanceId(attendance.getAttendanceId());
		      DateTimeFormatter formatterIn = DateTimeFormatter.ofPattern("HH:mm:ss");
		  	if (attendance.getInTime() != null) {
				attendancePojo.setInTime(formatterIn.format(attendance.getInTime()));
			}
			DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("HH:mm:ss");
			if (attendance.getOutTime() != null) {
				attendancePojo.setOutTime(formatterOut.format(attendance.getOutTime()));
			}
			attendancePojo.setTotalHours(attendance.getTotalHours());
			HashMap<String, AttendancePojo> map = new HashMap<String, AttendancePojo>();
			map.put("EmployeeAttendance", attendancePojo);
			return ResponseEntity.ok(map);
		  }
		  return new ResponseEntity<>("Sorry attendance id not found",HttpStatus.BAD_REQUEST);
		  
	  }//method
	  
	  @PostMapping("/countMonthWeekends/{year}/{month}")
	   public int countMonthWeekends(@PathVariable int year,@PathVariable int month){
		  
		  Calendar cal=Calendar.getInstance();
		  // Note that month is 0-based in calendar, bizarrely.
		  cal.set(year, month-1, 1);
		 // System.out.println(cal);
		  int daysInMonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		 // System.out.println(daysInMonth);
		  int count=0;
		  
		  for(int day=1;day<=daysInMonth;day++) {
			  cal.set(year,month-1,day);
			  int daysOfWeek=cal.get(Calendar.DAY_OF_WEEK);
			  //System.out.println(daysOfWeek);
			  if(daysOfWeek==Calendar.SUNDAY||daysOfWeek==Calendar.SATURDAY) {
				  count++;
				  //System.out.println(count);
			  }
		  }//end of loop
//		  System.out.println(daysInMonth);
//		IntStream.range(1,daysInMonth+1).forEach(day->{
//			cal.set(year,month-1,day);
//			if(day==cal.get(Calendar.SUNDAY)||day==Calendar.SATURDAY) {
//		      
//			}
//			
//		});
		  
		  return count;
		  
	  }//method
	  
	  @PostMapping("/currentMonth/{month}/{year}/{employeeId}")
	  public int workingDaysCount(@PathVariable("month") int month,@PathVariable("year")int year,@PathVariable("employeeId") String employeeId) {
		  Employee emp=employeeDao.findByEmployeeId(employeeId);
		  List<Date> holidays=holidaysDao.getbyHolidaysMonth(month, year);
		  System.out.println(holidays);
		  List<Attendance> attendance=attendanceDao.getByLop(month, year, emp.getEmployeeCode());
		  System.out.println(attendance);
		  System.out.println(emp.getEmployeeCode());
		  Calendar calendar=Calendar.getInstance();
		  calendar.set(year,month-1,1);
		  int daysInMonth = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		  int count=countMonthWeekends(year,month);
		  System.out.println(count);
		  int workingDays=(daysInMonth-count)-attendance.size();
		  System.out.println(workingDays);
		  
		  return workingDays;
	  }//method
		      
	  @GetMapping("/getAttendanceView/{employeeId}/{month}/{year}")
	  public ResponseEntity<?> getAttendanceView(@PathVariable("employeeId") String employeeId,@PathVariable("month") int month,@PathVariable("year") int year) {
		  Employee emp=employeeDao.findByEmployeeId(employeeId);
		  System.out.println(employeeId);
		  if(emp!=null) {
			 // List<Attendance> attendance=attendanceDao.getByLop(month, year, emp.getEmployeeCode());
			  List<Attendance> attendance=attendanceDao.findByAbsentDays(month, year,emp.getEmployeeCode());
			  System.out.println(attendance);
			  //add to pojo class
			  AttendPojo attPojo=new AttendPojo();
			  
			  for(Attendance attende:attendance) {
				  attPojo.setEmployeeId(attende.getEmployee().getEmployeeId());
				  attPojo.setEmpName(attende.getEmployee().getFirstName()+"  "+attende.getEmployee().getLastName());
				  int count=countMonthWeekends(month,year);
				  attPojo.setAbsentDays(count);
			  }
			  HashMap<String, AttendPojo> map = new HashMap<String, AttendPojo>();
				map.put("Attendance", attPojo);
			  return ResponseEntity.ok(map);
		  }
		  return new ResponseEntity<>("Invalid Id",HttpStatus.BAD_REQUEST);
		  
	  }//method
}//class
