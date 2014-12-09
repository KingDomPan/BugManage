package com.us.bug.qd.service;

import java.util.List;

import com.us.bug.qd.model.User;
import com.us.bug.qd.tools.Container;

public interface UserService
{
	public void save(User user);
	public User isExist(User user);
	User loadById(int id);
	 /**
	  * ��ȡ���е�Ա����Ϣ
	  * @param page
	  * @param pageSize
	  * @return 
	  */
	public Container<User> getUserByPage(int page,int pageSize);
	public List<User> getDevelopersInPosition();
	public void update(User user);
	
	/**
	 * ��ȡ������ְ��Ŀ������Ϣ
	 * @return id,userId,userName
	 */
	public List<User> getMangersInPosition();
	
	
	
	/**
	 * Ա���Ƿ���ְ
	 * @param user  
	 * @return
	 */
	public boolean isInPosition(User user);
	
	public Container<User> getDevelopersInPosition(int page,int pageSize);
}
