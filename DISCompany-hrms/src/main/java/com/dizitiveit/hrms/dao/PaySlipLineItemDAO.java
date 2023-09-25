package com.dizitiveit.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.PaySlipLineItem;

public interface PaySlipLineItemDAO extends JpaRepository<PaySlipLineItem,Integer> {
	
	@Query(value = "select * FROM com.pay_slip_line_item where item_details=?1 and current_month=?2 and current_year=?3 and monthly_salary_monthly_salary_id=?4", nativeQuery = true)
	 PaySlipLineItem findByItemDetails(String itemDetails,String currentMonth,int currentYear,long monthlySalary);
}
