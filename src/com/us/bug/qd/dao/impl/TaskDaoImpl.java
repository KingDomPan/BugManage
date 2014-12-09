package com.us.bug.qd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.dao.TaskDao;
import com.us.bug.qd.model.Task;
@Component("taskDaoImpl")
public class TaskDaoImpl extends Dao implements TaskDao
{

	@Override
	public void update(Task task)
	{
		this.getCrudHandler().update(task);
	}

	@Override
	public Task loadById(int id)
	{
		return this.getCrudHandler().loadById(Task.class, id);
	}

	@Override
	public void assingBug(Task task)
	{
		this.getCrudHandler().save(task);
	}

	@Override
	public List<Task> getTaskByDeveloperId(int id)
	{
		return this.getCrudHandler().getList(Task.class, "where obj.dealDeveloper.id="+id);
	}
}
