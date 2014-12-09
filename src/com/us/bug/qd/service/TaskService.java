package com.us.bug.qd.service;

import java.util.List;

import com.us.bug.qd.model.Task;

public interface TaskService
{
	/**
	 * ��������
	 * 
	 * @param task
	 *            ����������Ϣ
	 */
	public void assingBug(Task task);
	Task loadById(int id);
	List<Task> getTaskByDeveloperId(int id);
	public void update(Task task);
}
