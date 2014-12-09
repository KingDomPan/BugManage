package com.us.bug.qd.dao;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.us.bug.qd.model.Task;
import com.us.bug.qd.tools.Container;
import com.us.bug.qd.tools.CrudHandler;
import com.us.bug.qd.tools.PageSpliter;

public abstract class Dao
{
	private CrudHandler crudHandler;
	private PageSpliter pageSpliter;
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}

	public CrudHandler getCrudHandler()
	{
		return crudHandler;
	}

	public PageSpliter getPageSpliter()
	{
		return pageSpliter;
	}

	@Resource
	public void setCrudHandler(CrudHandler crudHandler)
	{
		this.crudHandler = crudHandler;
	}

	@Resource
	public void setPageSpliter(PageSpliter pageSpliter)
	{
		this.pageSpliter = pageSpliter;
	}

	public Container<Task> getToBeRepairedByPage(Integer managerID, int page,
			int pageSize)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
