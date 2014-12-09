package com.us.bug.qd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/***********************************************************************
 * Module: Repair.java Author: Administrator Purpose: Defines the Class Repair
 ***********************************************************************/

@Entity
@Table(name = "t_repair")
public class Repair
{
	private int repairId;
	private Bug repairBug;// 修复的bug信息
	private User repairDeveloper;// 修复bug的开发人员
	private java.util.Date submitedDay;// 提交日期
	private java.lang.String repairScheme;// 解决方案

	@Id
	@GeneratedValue
	public int getRepairId()
	{
		return repairId;
	}

	public void setRepairId(int repairId)
	{
		this.repairId = repairId;
	}

	@OneToOne
	@JoinColumn(name = "repairBugId")
	public Bug getRepairBug()
	{
		return repairBug;
	}

	public void setRepairBug(Bug repairBug)
	{
		this.repairBug = repairBug;
	}

	@OneToOne
	@JoinColumn(name = "repairDeveloperId")
	public User getRepairDeveloper()
	{
		return repairDeveloper;
	}

	public void setRepairDeveloper(User repairDeveloper)
	{
		this.repairDeveloper = repairDeveloper;
	}

	public java.util.Date getSubmitedDay()
	{
		return submitedDay;
	}

	public void setSubmitedDay(java.util.Date submitedDay)
	{
		this.submitedDay = submitedDay;
	}

	public java.lang.String getRepairScheme()
	{
		return repairScheme;
	}

	public void setRepairScheme(java.lang.String repairScheme)
	{
		this.repairScheme = repairScheme;
	}

}