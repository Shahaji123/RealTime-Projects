package com.dizitiveit.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dizitiveit.hrms.model.AttendanceSummary;

public interface AttendanceSummaryDAO extends JpaRepository<AttendanceSummary,Integer>{

}
