package com.us.bug.qd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.dao.ModularDao;
import com.us.bug.qd.model.Modular;

@Component("modularDaoImpl")
public class ModularDaoImpl extends Dao implements ModularDao
{

	@Override
	public Modular loadById(int id)
	{
		return this.getCrudHandler().loadById(Modular.class, id);
	}

	/**
	 * query «ProjectId
	 * @param query
	 * @return
	 */
	@Override
	public List<Modular> getModularsByProjectId(String query)
	{	
		return this.getCrudHandler().getList(Modular.class,"where obj.project.id='"+query+"'");
	}

	@Override
	public void add(Modular modular)
	{
		this.getCrudHandler().save(modular);
	}

	@Override
	public void update(Modular modular)
	{
		this.getCrudHandler().update(modular);
	}

}
