package com.dizitiveit.hrms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.LeaveAudit;

public interface LeaveAuditDAO extends JpaRepository<LeaveAudit,Integer> {


	@Query(value = "select *  FROM com.leave_audit where employee_employee_code=?1 and leave_master_leave_id=?2 order by leave_audit_id desc limit 1", nativeQuery = true)
	LeaveAudit findByQuarter(long employeeCode, long leaveId);
	
	@Query(value = "select *  FROM com.leave_audit where employee_employee_code=?1", nativeQuery = true)
	List<LeaveAudit> findByEmployee(long employeeCode);
}
