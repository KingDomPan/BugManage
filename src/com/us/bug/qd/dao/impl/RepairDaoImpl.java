package com.us.bug.qd.dao.impl;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.dao.RepairDao;
import com.us.bug.qd.model.Repair;
import com.us.bug.qd.tools.Container;

@Component("repairDaoImpl")
public class RepairDaoImpl extends Dao implements RepairDao
{
	@Override
	public void add(Repair repair)
	{
		this.getCrudHandler().save(repair);
	}

	@Override
	public Container<Repair> getDeveloperSubmitedBugByUserId(
			Integer developerID, int page, int pageSize)
	{
		return this.getPageSpliter().getListByPage(Repair.class, page,
				pageSize, "where obj.repairDeveloper.id=" + developerID,
				"obj.submitedDay", "desc");
	}

	@Override
	public Container<Repair> getToBeCheckedBugByUserId(Integer testerID,
			String stateName, int page, int pageSize)
	{
		return this.getPageSpliter().getListByPage(Repair.class, page,
				pageSize, "where obj.repairBug.tester.id=" + testerID+" and obj.repairBug.stateName='"+stateName+"'",
				"obj.submitedDay", "desc");
	}

	@Override
	public Repair loadById(int rid)
	{
		return this.getCrudHandler().loadById(Repair.class, rid);
	}

}
