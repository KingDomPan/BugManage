package com.us.bug.qd.service;

import com.us.bug.qd.model.TaskReason;
import com.us.bug.qd.tools.Container;

public interface TaskReasonService
{
	void add(TaskReason taskReason);
	
	public Container<TaskReason> getReAssignBugByUserId(Integer managerID,String stateName,int page,int pageSize);
}
