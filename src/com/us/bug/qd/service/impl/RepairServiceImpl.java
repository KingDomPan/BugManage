package com.us.bug.qd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.RepairDao;
import com.us.bug.qd.model.Repair;
import com.us.bug.qd.service.RepairService;
import com.us.bug.qd.tools.Container;

@Component("repairServiceImpl")
public class RepairServiceImpl implements RepairService
{
	@Override
	public Repair loadById(int rid)
	{
		return this.repairDao.loadById(rid);
	}

	@Override
	public Container<Repair> getDeveloperSubmitedBugByUserId(
			Integer developerID, int page, int pageSize)
	{
		return this.repairDao.getDeveloperSubmitedBugByUserId(developerID, page, pageSize);
	}

	private RepairDao repairDao;
	
	public RepairDao getRepairDao()
	{
		return repairDao;
	}

	@Resource(name="repairDaoImpl")
	public void setRepairDao(RepairDao repairDao)
	{
		this.repairDao = repairDao;
	}

	@Override
	public void add(Repair repair)
	{
		this.repairDao.add(repair);
	}

	@Override
	public Container<Repair> getToBeCheckedBugByUserId(Integer testerID,
			String stateName, int page, int pageSize)
	{
		return this.repairDao.getToBeCheckedBugByUserId(testerID, stateName, page, pageSize);
	}
	
}
