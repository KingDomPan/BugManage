package com.us.bug.qd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.TaskDao;
import com.us.bug.qd.model.Task;
import com.us.bug.qd.service.TaskService;


@Component("taskServiceImpl")
public class TaskServiceImpl implements TaskService
{
	@Override
	public void update(Task task)
	{
		this.taskDao.update(task);
	}


	@Override
	public Task loadById(int id)
	{
		return this.taskDao.loadById(id);
	}


	private TaskDao taskDao;

	public TaskDao getTaskDao()
	{
		return taskDao;
	}

	
	@Resource(name="taskDaoImpl")
	public void setTaskDao(TaskDao taskDao)
	{
		this.taskDao = taskDao;
	}
	
	@Override
	public void assingBug(Task task)
	{
		taskDao.assingBug(task);
	}


	@Override
	public List<Task> getTaskByDeveloperId(int id)
	{
		return taskDao.getTaskByDeveloperId(id);
	}

}
