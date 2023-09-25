package com.dizitiveit.hrms.pojo;

import lombok.Builder;

@Builder
public class Responses {
  private String message;

//generate setter && getter method
public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
   
}
