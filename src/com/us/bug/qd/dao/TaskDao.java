package com.us.bug.qd.dao;

import java.util.List;

import com.us.bug.qd.model.Task;

public interface TaskDao
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
