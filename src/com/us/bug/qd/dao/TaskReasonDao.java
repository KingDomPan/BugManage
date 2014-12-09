package com.us.bug.qd.dao;

import com.us.bug.qd.model.TaskReason;
import com.us.bug.qd.tools.Container;

public interface TaskReasonDao
{
	void add(TaskReason taskReason);

	public Container<TaskReason> getReAssignBugByUserId(Integer managerID,
			String stateName, int page, int pageSize);
}
