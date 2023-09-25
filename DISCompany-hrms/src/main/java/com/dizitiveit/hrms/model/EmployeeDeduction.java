package com.dizitiveit.hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class EmployeeDeduction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 private Long empDeductionId;
	private String deductionName;
	private Integer fromMonth;
	private Integer fromYear;
	private Integer toMonth;
	private Integer toYear;
	private Boolean status;
	
	private Double value;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date effectFromDate;
	
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	  @Temporal(TemporalType.DATE)
	 private Date lastModifiedDate;
	
	 @DateTimeFormat(pattern="dd-MM-yyyy")
	 private Date createdAt;


	 @OneToOne
	 private Employee employee;
	 
	 @ManyToOne
	 private Deduction deductions;





}
