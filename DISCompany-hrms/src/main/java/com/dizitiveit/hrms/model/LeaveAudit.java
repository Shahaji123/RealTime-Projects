package com.dizitiveit.hrms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Entity
@Data
public class LeaveAudit {

	@Id
	@GeneratedValue
	private Long leaveAuditId;
	private String financialYear;
	private Integer openingBalance;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@Temporal(TemporalType.DATE)
	private Date lastCreated;
	
	 private Integer leavesCredited;
	 private Integer leavesApproved;
	 private Integer leavesAwaitingApproval;
	 private Integer leaveBalance;
	 
	 @ManyToOne
	 private Employee employee;
	 
	 @ManyToOne
	 private LeaveMaster leaveMaster;
	 
}
