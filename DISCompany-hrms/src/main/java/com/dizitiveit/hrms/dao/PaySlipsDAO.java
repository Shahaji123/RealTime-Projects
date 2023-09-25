package com.dizitiveit.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.PaySlipLineItem;
import com.dizitiveit.hrms.model.PaySlips;

public interface PaySlipsDAO extends JpaRepository<PaySlips,Integer> {

	 
	 
}
