package com.us.bug.qd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/***********************************************************************
 * Module: RepairReason.java Author: Administrator Purpose: Defines the Class RepairReason
 ***********************************************************************/

@Entity
@Table(name = "t_repairReason")
public class RepairReason
{
	private int rrid;
	private Repair repair;
	private java.util.Date submitedDay;// 处理时间
	private String repairReason;
	private String reasonDetail;
	
	@Id
	@GeneratedValue
	public int getRrid()
	{
		return rrid;
	}
	public void setRrid(int rrid)
	{
		this.rrid = rrid;
	}
	
	@OneToOne
	@JoinColumn(name="repairId")
	public Repair getRepair()
	{
		return repair;
	}
	public void setRepair(Repair repair)
	{
		this.repair = repair;
	}
	public java.util.Date getSubmitedDay()
	{
		return submitedDay;
	}
	public void setSubmitedDay(java.util.Date submitedDay)
	{
		this.submitedDay = submitedDay;
	}
	public String getRepairReason()
	{
		return repairReason;
	}
	public void setRepairReason(String repairReason)
	{
		this.repairReason = repairReason;
	}
	public String getReasonDetail()
	{
		return reasonDetail;
	}
	public void setReasonDetail(String reasonDetail)
	{
		this.reasonDetail = reasonDetail;
	}
}
