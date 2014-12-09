package com.us.bug.qd.service;

import com.us.bug.qd.model.RepairReason;
import com.us.bug.qd.tools.Container;

public interface RepairReasonService
{
	public void add(RepairReason repairReason);
	RepairReason loadById(int id);
	/**
	 * 
	 * @param developerID
	 * @param stateName ÷ÿ–ﬁ∏¥
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Container<RepairReason> getReRepairBugByUserId(Integer developerID,String stateName,int page,int pageSize);
}
