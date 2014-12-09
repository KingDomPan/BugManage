package com.us.bug.qd.dao;

import java.util.List;

import com.us.bug.qd.model.Modular;

public interface ModularDao
{
	/**
	 * query是ProjectId
	 * @param query
	 * @return
	 */
	public List<Modular> getModularsByProjectId(String query);
	
	/**
	 * 添加模块信息
	 * @param modular
	 */
	public void add(Modular modular);
	
	/**
	 * 修改模块信息
	 * @param modular
	 */
	public void update(Modular modular);
	
	public Modular loadById(int id);
}
