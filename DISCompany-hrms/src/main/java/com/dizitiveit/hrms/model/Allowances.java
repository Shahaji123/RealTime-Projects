package com.dizitiveit.hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
@Entity
public class Allowances {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long allowanceId;
	private String allowanceName;
	private String allowanceType;
	private Double value;
	private Boolean status;
	private Boolean active;
	private String lastModfiedBy;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date lastModifiedDate;
	 
	 
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	 private Date endDate;
}
