package com.us.bug.qd.dao.impl;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.BugReasonDao;
import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.model.BugReason;

@Component("bugReasonDaoImpl")
public class BugReasonDaoImpl extends Dao implements BugReasonDao
{
	@Override
	public void bugOperate(BugReason bugReason)
	{
		this.getCrudHandler().save(bugReason);
	}
}
