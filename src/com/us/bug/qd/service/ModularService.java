package com.us.bug.qd.service;

import java.util.List;

import com.us.bug.qd.model.Modular;

public interface ModularService {
	/**
	 * query��ProjectId
	 * @param query
	 * @return
	 */
	public List<Modular> getModularsByProjectId(String query);
	
	/**
	 * ���ģ����Ϣ
	 * @param modular
	 */
	public void add(Modular modular);
	
	/**
	 * �޸�ģ����Ϣ
	 * @param modular
	 */
	public void update(Modular modular);
	public Modular loadById(int id);
}
