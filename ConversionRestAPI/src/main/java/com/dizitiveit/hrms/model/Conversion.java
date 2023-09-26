package com.dizitiveit.hrms.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Conversion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer contactId;
	private String contactName;
	private String email;
	private String number;

	
}
