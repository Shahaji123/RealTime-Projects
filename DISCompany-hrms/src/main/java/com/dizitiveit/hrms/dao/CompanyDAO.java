package com.dizitiveit.hrms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.Company;

public interface CompanyDAO extends  JpaRepository<Company,Integer>{
    
	
    Company findBycompanyId(Integer companyId);
	Boolean existsByCompanyName(String companyName);
	Boolean existsByCompanyId(Integer companyId);
	
	 @Query(value = "select *  FROM com.company  where status=?1 ",nativeQuery = true)
		List<Company> findBysetStatus(boolean status);
		
	 @Query(value = "select * FROM com.company order by company_id asc", nativeQuery = true)
	  List<Company> findAll();
}
