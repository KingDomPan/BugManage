package com.us.bug.qd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.RepairReasonDao;
import com.us.bug.qd.model.RepairReason;
import com.us.bug.qd.service.RepairReasonService;
import com.us.bug.qd.tools.Container;

@Component("repairReasonServiceImpl")
public class RepairReasonServiceImpl implements RepairReasonService
{
	@Override
	public void add(RepairReason repairReason)
	{
		this.repairReasonDao.add(repairReason);
	}

	@Override
	public Container<RepairReason> getReRepairBugByUserId(Integer developerID,
			String stateName, int page, int pageSize)
	{
		return this.repairReasonDao.getReRepairBugByUserId(developerID,
				stateName, page, pageSize);
	}

	@Override
	public RepairReason loadById(int id)
	{
		return this.repairReasonDao.loadById(id);
	}

	private RepairReasonDao repairReasonDao;

	public RepairReasonDao getRepairReasonDao()
	{
		return repairReasonDao;
	}

	@Resource(name="repairReasonDaoImpl")
	public void setRepairReasonDao(RepairReasonDao repairReasonDao)
	{
		this.repairReasonDao = repairReasonDao;
	}
	
}
