package com.dizitiveit.hrms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dizitiveit.hrms.model.LeaveMaster;

public interface LeaveMasterDAO extends JpaRepository<LeaveMaster,Long> {

	public Boolean existsByLeaveType(String LeaveType);
	public LeaveMaster findByLeaveId(long leaveId);
	//public Boolean findByLeaveType(String leaveType);
	
	@Query(value="select * from com.leave_master where status=?1",nativeQuery=true)
	List<String> findByLeaveType(Boolean status);
	
	@Query(value="select * from com.leave_master where status=?1",nativeQuery=true)
    List<LeaveMaster> findByAllStatus(Boolean status);
}
