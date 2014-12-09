package com.us.bug.qd.action;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ActionSupport;
import com.us.bug.qd.dao.impl.AddDaoImpl;

@SuppressWarnings("serial")
@Component("addAction")
@Scope("prototype")

public class AddAction extends ActionSupport
{
	private AddDaoImpl addDaoImpl;

	public AddDaoImpl getAddDaoImpl()
	{
		return addDaoImpl;
	}

	@Resource(name="addDaoImpl")
	public void setAddDaoImpl(AddDaoImpl addDaoImpl)
	{
		this.addDaoImpl = addDaoImpl;
	}
	
	public void addUsers()
	{
		addDaoImpl.addUsers();
	}
	
	public void addProjects()
	{
		addDaoImpl.addProjects();
	}
	
	public void addVersions()
	{
		addDaoImpl.addVersions();
	}
	
	
	public void addModulars()
	{
		addDaoImpl.addModulars();
	}
	
	public void addBugs()
	{
		addDaoImpl.addBugs();
	}
	public void addBugReasons()
	{
		addDaoImpl.addBugReasons();
	}
	
	public void addData()
	{
		addDaoImpl.addUsers();
		addDaoImpl.addProjects();
		addDaoImpl.addModulars();
		addDaoImpl.addVersions();
		addDaoImpl.addBugs();
		//addDaoImpl.addBugReasons();
	}
}
