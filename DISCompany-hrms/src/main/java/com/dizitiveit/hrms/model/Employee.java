package com.dizitiveit.hrms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
//@Table(name="employee")
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long employeeCode;
	private String employeeId;

	
	@DateTimeFormat(pattern="yyyy-mm-dd")
	@Temporal(TemporalType.DATE)
	private Date dateOfJoining;
	private String firstName;
	private String lastName;
	private String gender;
	private String permanentAddress;
	private String phoneNumber;
	private String officialEmailId;
	
	private String nationality;
	private String adharNumber;
	private String panCardNumber;
	private boolean status;
	private Date createdAt;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Roles roles;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Branch branch;
	
	@OneToOne(cascade=CascadeType.MERGE)
	private Department department;
	
	@OneToOne(cascade=CascadeType.MERGE)
	private Designation desg;	
	
	
}
