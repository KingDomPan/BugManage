/***********************************************************************
 * Module:  BugReason.java
 * Author:  Administrator
 * Purpose: Defines the Class BugReason
 ***********************************************************************/
package com.us.bug.qd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_bugReason")
public class BugReason
{
	private long bugDealReasonId;
	private Bug dealledBug;// 待处理的bug的
	private String bugReasonType;// 理由类型
	private java.lang.String reasonDetail;// 处理理由
	private java.util.Date submitedDay;// 处理时间
	private User manager;
	
	@Id
	@GeneratedValue
	public long getBugDealReasonId()
	{
		return bugDealReasonId;
	}

	@OneToOne
	@JoinColumn(name = "managerId")
	public User getManager()
	{
		return manager;
	}

	public void setManager(User manager)
	{
		this.manager = manager;
	}

	public void setBugDealReasonId(long bugDealReasonId)
	{
		this.bugDealReasonId = bugDealReasonId;
	}

	@ManyToOne
	@JoinColumn(name = "dealledBugId")
	public Bug getDealledBug()
	{
		return dealledBug;
	}

	public void setDealledBug(Bug dealledBug)
	{
		this.dealledBug = dealledBug;
	}

	public java.lang.String getReasonDetail()
	{
		return reasonDetail;
	}

	public String getBugReasonType()
	{
		return bugReasonType;
	}

	public void setBugReasonType(String bugReasonType)
	{
		this.bugReasonType = bugReasonType;
	}

	public void setReasonDetail(java.lang.String reasonDetail)
	{
		this.reasonDetail = reasonDetail;
	}

	public java.util.Date getSubmitedDay()
	{
		return submitedDay;
	}

	public void setSubmitedDay(java.util.Date submitedDay)
	{
		this.submitedDay = submitedDay;
	}

}