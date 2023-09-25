package com.dizitiveit.hrms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
@Data
@Entity
public class PaySlipLineItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer payItemId;
	private String currentMonth;
	private Integer currentYear;
	private String itemDetails;
	private Double itemValue;
	private boolean itemType;
	private String updatedBy;
	private  LocalDateTime updatedDate;
    
	@ManyToOne
	private MonthlySalary monthlySalary;
}
