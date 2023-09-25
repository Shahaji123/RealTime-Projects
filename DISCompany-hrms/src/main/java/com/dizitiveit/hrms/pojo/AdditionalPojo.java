package com.dizitiveit.hrms.pojo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AdditionalPojo {

	private int additionalDetailsId;
	private String alternateNumber;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date weddingDay;
	private String passportNumber;
	private String photoDisplay;
	private String photo;
	
}
