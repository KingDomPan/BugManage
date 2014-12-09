package com.us.bug.qd.service;

import com.us.bug.qd.model.Repair;
import com.us.bug.qd.tools.Container;

public interface RepairService
{
	public void add(Repair repair);

	public Container<Repair> getDeveloperSubmitedBugByUserId(
			Integer developerID, int page, int pageSize);
	
	public Container<Repair> getToBeCheckedBugByUserId(Integer testerID,String stateName,int page,int pageSize);
	
	public Repair loadById(int rid);
}
