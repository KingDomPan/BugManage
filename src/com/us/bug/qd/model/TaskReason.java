/***********************************************************************
 * Module:  TaskReason.java
 * Author:  Administrator
 * Purpose: Defines the Class TaskReason
 ***********************************************************************/

package com.us.bug.qd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_taskReason")
public class TaskReason
{
	private int taskReasonId;
	private Task task;
	private String taskReasonType;
	private java.lang.String reasonDetail;
	private java.util.Date submitedDay;

	public java.lang.String getReasonDetail()
	{
		return reasonDetail;
	}

	public java.util.Date getSubmitedDay()
	{
		return submitedDay;
	}

	@ManyToOne
	@JoinColumn(name = "taskId")
	public Task getTask()
	{
		return task;
	}

	@Id
	@GeneratedValue
	public int getTaskReasonId()
	{
		return taskReasonId;
	}

	public void setSubmitedDay(java.util.Date submitedDay)
	{
		this.submitedDay = submitedDay;
	}

	public String getTaskReasonType()
	{
		return taskReasonType;
	}

	public void setTaskReasonType(String taskReasonType)
	{
		this.taskReasonType = taskReasonType;
	}

	public void setReasonDetail(java.lang.String reasonDetail)
	{
		this.reasonDetail = reasonDetail;
	}

	public void setTask(Task task)
	{
		this.task = task;
	}

	public void setTaskReasonId(int taskReasonId)
	{
		this.taskReasonId = taskReasonId;
	}

}