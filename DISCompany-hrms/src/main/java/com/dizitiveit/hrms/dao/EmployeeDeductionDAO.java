package com.dizitiveit.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dizitiveit.hrms.model.Deduction;

public interface EmployeeDeductionDAO extends JpaRepository<Deduction,Integer>{

}
