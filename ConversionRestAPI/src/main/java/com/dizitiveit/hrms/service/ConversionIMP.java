package com.dizitiveit.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dizitiveit.hrms.dao.ConversionDAO;
@Service
public class ConversionIMP implements IConversion {

	@Autowired
	private ConversionDAO conversionDao;
	
	@Override
	public String con() {
		return "123";
	}//method
}
