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
	  * 获取所有的员工信息
	  * @param page
	  * @param pageSize
	  * @return 
	  */
	public Container<User> getUserByPage(int page,int pageSize);
	public List<User> getDevelopersInPosition();
	public void update(User user);
	
	/**
	 * 获取所有在职项目经理信息
	 * @return id,userId,userName
	 */
	public List<User> getMangersInPosition();
	
	
	
	/**
	 * 员工是否在职
	 * @param user  
	 * @return
	 */
	public boolean isInPosition(User user);
	
	public Container<User> getDevelopersInPosition(int page,int pageSize);
}
