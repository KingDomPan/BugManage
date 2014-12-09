package com.us.bug.qd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.BugReasonDao;
import com.us.bug.qd.model.BugReason;
import com.us.bug.qd.service.BugReasonService;


@Component("bugReasonServiceImpl")
public class BugReasonServiceImpl implements BugReasonService
{
	private BugReasonDao bugReasonDao;
	
	public BugReasonDao getBugReasonDao()
	{
		return bugReasonDao;
	}

	@Resource(name="bugReasonDaoImpl")
	public void setBugReasonDao(BugReasonDao bugReasonDao)
	{
		this.bugReasonDao = bugReasonDao;
	}

	@Override
	public void bugOperate(BugReason bugReason)
	{
		bugReasonDao.bugOperate(bugReason);
	}
}
