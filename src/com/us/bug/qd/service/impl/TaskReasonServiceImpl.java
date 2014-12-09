package com.us.bug.qd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.TaskReasonDao;
import com.us.bug.qd.model.TaskReason;
import com.us.bug.qd.service.TaskReasonService;
import com.us.bug.qd.tools.Container;

@Component("taskReasonServiceImpl")
public class TaskReasonServiceImpl implements TaskReasonService
{

	@Override
	public Container<TaskReason> getReAssignBugByUserId(Integer managerID,
			String stateName, int page, int pageSize)
	{
		return this.taskReasonDao.getReAssignBugByUserId(managerID, stateName, page, pageSize);
	}

	private TaskReasonDao taskReasonDao;
	
	public TaskReasonDao getTaskReasonDao()
	{
		return taskReasonDao;
	}

	@Resource(name="taskReasonDaoImpl")
	public void setTaskReasonDao(TaskReasonDao taskReasonDao)
	{
		this.taskReasonDao = taskReasonDao;
	}

	@Override
	public void add(TaskReason taskReason)
	{
		this.taskReasonDao.add(taskReason);
	}
	
}
