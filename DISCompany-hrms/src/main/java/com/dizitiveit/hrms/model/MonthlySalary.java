package com.dizitiveit.hrms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
@Data
public class MonthlySalary {

	
	@Id
	@GeneratedValue
	private Integer monthlySalaryId;
	private String currentMonth;
	private Integer currentYear;
	private Double totalEarnings;
	private Double totalDeductions;
	private Double netSalary;
	private Integer basicSalary;
	private Integer daysPaid;

	@ManyToOne
	private Employee employee;
}
