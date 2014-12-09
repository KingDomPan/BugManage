/***********************************************************************
 * Module:  TaskReport.java
 * Author:  Administrator
 * Purpose: Defines the Class TaskReport
 ***********************************************************************/

package com.us.bug.qd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_task")
public class Task
{
	private int taskId;// 任务编号
	private Bug bug;// 分配的bug信息
	private User dealDeveloper;// 处理bug的开发人员
	private User assignManager;// 分配bug的项目经理
	private Date submitedDay;// 提交时间
	private String remark;// 备注信息
	private boolean isActive;// 是否有效
	private String bugPriority;// 优先级
	private Set<TaskReason> taskReasons = new HashSet<TaskReason>();

	public String getBugPriority()
	{
		return bugPriority;
	}

	public void setBugPriority(String bugPriority)
	{
		this.bugPriority = bugPriority;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	public Set<TaskReason> getTaskReasons()
	{
		return taskReasons;
	}

	public void setTaskReasons(Set<TaskReason> taskReasons)
	{
		this.taskReasons = taskReasons;
	}

	@Id
	@GeneratedValue
	public int getTaskId()
	{
		return taskId;
	}

	public void setTaskId(int taskId)
	{
		this.taskId = taskId;
	}

	@ManyToOne
	@JoinColumn(name = "bugId")
	public Bug getBug()
	{
		return bug;
	}

	public void setBug(Bug bug)
	{
		this.bug = bug;
	}

	@ManyToOne
	@JoinColumn(name = "dealDeveloperId")
	public User getDealDeveloper()
	{
		return dealDeveloper;
	}

	public void setDealDeveloper(User dealDeveloper)
	{
		this.dealDeveloper = dealDeveloper;
	}

	@ManyToOne
	@JoinColumn(name = "assignManagerId")
	public User getAssignManager()
	{
		return assignManager;
	}

	public void setAssignManager(User assignManager)
	{
		this.assignManager = assignManager;
	}

	public Date getSubmitedDay()
	{
		return submitedDay;
	}

	public void setSubmitedDay(Date submitedDay)
	{
		this.submitedDay = submitedDay;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(columnDefinition = "bit default 1", name = "isActive")
	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

}