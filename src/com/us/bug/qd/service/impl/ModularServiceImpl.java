package com.us.bug.qd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.ModularDao;
import com.us.bug.qd.model.Modular;
import com.us.bug.qd.service.ModularService;

@Component("modularServiceImpl")
public class ModularServiceImpl implements ModularService {

	@Override
	public Modular loadById(int id)
	{
		return modularDao.loadById(id);
	}

	@Override
	public void add(Modular modular)
	{
		this.modularDao.add(modular);
	}

	@Override
	public void update(Modular modular)
	{
		this.modularDao.update(modular);
	}

	private ModularDao modularDao;
	
	public ModularDao getModularDao()
	{
		return modularDao;
	}

	@Resource(name="modularDaoImpl")
	public void setModularDao(ModularDao modularDao)
	{
		this.modularDao = modularDao;
	}

	@Override
	public List<Modular> getModularsByProjectId(String query) {
		return modularDao.getModularsByProjectId(query);
	}
	
}
