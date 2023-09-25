package com.dizitiveit.hrms.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
@Entity
public class Attendance {

	@Id
	@GeneratedValue
	private Integer attendanceId;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime inTime;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime outTime;


	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 @Temporal(TemporalType.DATE)
	private Date attendanceDay;
	 
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 @Temporal(TemporalType.DATE)
	 private Date createdAt;
	
//	  @DateTimeFormat(pattern="yyyy-MM-dd")
//	  @Temporal(TemporalType.DATE)
	  private LocalDate lastModifiedDate;
	
	  private String totalHours;
	  private Integer permissionHours;
	
	
	  @ManyToOne
	  private Employee employee;
	
	
//	
	
	
	
}//class
