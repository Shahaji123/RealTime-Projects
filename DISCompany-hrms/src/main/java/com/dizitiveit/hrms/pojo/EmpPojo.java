package com.dizitiveit.hrms.pojo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.dizitiveit.hrms.model.Employee;

import lombok.Data;
@Data
public class EmpPojo {
	
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

}
