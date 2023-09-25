package com.dizitiveit.hrms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="pay_slips")
public class PaySlips {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer paySlipsId;
	private String month;
	private Integer year;
	
	@Lob
	private byte[] paySlip;
	private String contentType;
	
	@ManyToOne
	private Employee employee;
	
}
