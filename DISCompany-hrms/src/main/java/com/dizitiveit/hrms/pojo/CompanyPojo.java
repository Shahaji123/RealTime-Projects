package com.dizitiveit.hrms.pojo;

import java.sql.Date;

import lombok.Data;

@Data
public class CompanyPojo {
	private Integer companyId;
	private String companyName;
	private String registrationNumber;
	private String gstNumber;
	private String panNumber;
	private String esiNumer;
	private String pfNumber;
	private String tinNumber;
	private Integer numberOfBranches;
	private Integer workingHours;
	private boolean status;
	private Date lastModifiedDate;

}
