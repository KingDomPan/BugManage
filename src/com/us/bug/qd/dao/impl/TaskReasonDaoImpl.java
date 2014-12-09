package com.us.bug.qd.dao.impl;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.dao.TaskReasonDao;
import com.us.bug.qd.model.TaskReason;
import com.us.bug.qd.tools.Container;

@Component("taskReasonDaoImpl")
public class TaskReasonDaoImpl extends Dao implements TaskReasonDao
{

	@Override
	public Container<TaskReason> getReAssignBugByUserId(Integer managerID,
			String stateName, int page, int pageSize)
	{
		return this.getPageSpliter().getListByPage(
				TaskReason.class,
				page,
				pageSize,
				"where obj.task.assignManager.id=" + managerID
						+ " and obj.task.bug.stateName='" + stateName + "'",
				"obj.submitedDay", "desc");
	}

	@Override
	public void add(TaskReason taskReason)
	{
		this.getCrudHandler().save(taskReason);
	}

}
