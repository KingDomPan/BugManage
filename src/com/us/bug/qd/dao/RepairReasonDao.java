package com.us.bug.qd.dao;

import com.us.bug.qd.model.RepairReason;
import com.us.bug.qd.tools.Container;

public interface RepairReasonDao
{
	public void add(RepairReason repairReason);
	RepairReason loadById(int id);
	public Container<RepairReason> getReRepairBugByUserId(Integer developerID,
			String stateName, int page, int pageSize);
}
