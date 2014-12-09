package com.us.bug.qd.dao.impl;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.dao.RepairReasonDao;
import com.us.bug.qd.model.RepairReason;
import com.us.bug.qd.tools.Container;

@Component("repairReasonDaoImpl")
public class RepairReasonDaoImpl extends Dao implements RepairReasonDao
{
	@Override
	public void add(RepairReason repairReason)
	{
		this.getCrudHandler().save(repairReason);
	}

	@Override
	public Container<RepairReason> getReRepairBugByUserId(Integer developerID,
			String stateName, int page, int pageSize)
	{
		return this.getPageSpliter().getListByPage(
				RepairReason.class,
				page,
				pageSize,
				"where obj.repair.repairDeveloper.id=" + developerID
						+ " and obj.repair.repairBug.stateName='" + stateName + "'", "obj.submitedDay",
				"desc");
	}

	@Override
	public RepairReason loadById(int id)
	{
		return this.getCrudHandler().loadById(RepairReason.class, id);
	}
}
