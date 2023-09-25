package com.dizitiveit.hrms.pojo;



import java.util.Date;

import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.dizitiveit.hrms.model.Employee;

import lombok.Data;
@Data
public class BankDetailPojo {
	private String accountHolderName;
	private String ifscCode;
	private String accountNumber;
	private String bankName;
	private String branchName;
	private String ownedBy;
	private String status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date lastModifiedDate;
		

		@OneToOne
		private Employee employee;
}
