package com.us.bug.qd.service;

import java.util.List;

import com.us.bug.qd.model.Task;

public interface TaskService
{
	/**
	 * 分配任务
	 * 
	 * @param task
	 *            分配任务信息
	 */
	public void assingBug(Task task);
	Task loadById(int id);
	List<Task> getTaskByDeveloperId(int id);
	public void update(Task task);
}
