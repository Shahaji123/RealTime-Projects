package com.dizitiveit.hrms.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.Attendance;
import com.dizitiveit.hrms.model.Employee;


public interface AttendanceDAO extends JpaRepository<Attendance,Integer>{

	List<Attendance> findByEmployee(Employee employee);
	
	Optional<Attendance> findByAttendanceId(Integer attendanceId);
//	
	 @Query(value = "select * FROM attendance where employee_employee_code=?1 and attendance_day=?2 ", nativeQuery = true)
	 Attendance findByattendanceDay(long emplyoeeCode,Date attendanceday);

	 @Query(value = "select * FROM com.attendance where attendance_id=?1 and MONTH(attendance_day) =?2 and YEAR(attendance_day)=?3", nativeQuery = true)
	Attendance findByattendanceId(Integer attendanceId,Long month,Long year);
	
	 
	 @Query(value = "select * FROM com.attendance where employee_employee_code=?1 and  DAY(attendance_day) =?2 and MONTH(attendance_day) =?3  and YEAR(attendance_day)=?4 and in_time is not null", nativeQuery = true)
	 Attendance getByAttendanceDay(long emplyoeeCode,long day,long month,long year);
	
	  @Query(value = "select * from com.attendance where MONTH(attendance_day) =?1  and YEAR(attendance_day)=?2 and employee_employee_code=?3 ", nativeQuery = true)
		List<Attendance> getByLop(Integer month, Integer year,Long employeeCode);
	  
	  @Query(value="SELECT * FROM attendance WHERE MONTH(attendance_day) =?1  and YEAR(attendance_day) =?2 and employee_employee_code=?3 ", nativeQuery = true)
		 List<Attendance> findByAbsentDays(int month,int year,long employeeCode);
		 
	  
}
