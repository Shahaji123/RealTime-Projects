package com.dizitiveit.hrms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.Branch;
import com.dizitiveit.hrms.model.Company;

public interface BranchDAO extends JpaRepository<Branch,Integer> {
  
	public Branch findByBranchId(Integer branchId);
	public Branch findBybranchName(String branchName);
	public Boolean existsByBranchName(String branchName);
	  //Branch findById(Integer branchId);
	
	 @Query(value = "select * FROM com.branch where status=?1", nativeQuery = true)
		List<Branch> findBysetStatus(boolean status);
	 
	 @Query(value = "select branch_name FROM branch ", nativeQuery = true)
	 List<String> findBybranchName();
	 
	@Query(value = "select * FROM com.Branch order by branch_id asc", nativeQuery = true)
	 List<Branch> findAll();
}
