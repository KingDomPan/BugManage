package com.us.bug.qd.dao;

import com.us.bug.qd.model.Repair;
import com.us.bug.qd.tools.Container;

public interface RepairDao
{
	public void add(Repair repair);
	
	/**
     *  ��ȡ�޸���Ա�ύ��bug�޸���Ϣ
     * @param developerID 
     * @param page
     * @param pageSize
     * @return 
     */
    public Container<Repair> getDeveloperSubmitedBugByUserId(Integer developerID,int page,int pageSize);
    public Repair loadById(int rid);
    public Container<Repair> getToBeCheckedBugByUserId(Integer testerID,String stateName,int page,int pageSize);

}
