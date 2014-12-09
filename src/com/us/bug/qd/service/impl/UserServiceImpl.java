package com.us.bug.qd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.UserDao;
import com.us.bug.qd.model.User;
import com.us.bug.qd.service.UserService;
import com.us.bug.qd.tools.Container;

@Component("userServiceImpl")
public class UserServiceImpl implements UserService
{
	@Override
	public List<User> getDevelopersInPosition()
	{
		return userDao.getDevelopersInPosition();
	}

	private UserDao userDao;
	@Override
	public Container<User> getUserByPage(int page, int pageSize)
	{
		return userDao.getUserByPage(page, pageSize);
	}

	public UserDao getUserDao()
	{
		return userDao;
	}

	
	@Override
	public User isExist(User user)
	{
		return userDao.isExist(user);
	}
	
	@Override
	public User loadById(int id)
	{
		return userDao.loadById(id);
	}
	@Override
	public void save(User user)
	{
		userDao.save(user);
	}

	@Resource(name="userDaoImpl")
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	@Override
	public void update(User user)
	{
		this.userDao.update(user);
	}
	
	
	/**
	 * 员工是否在职
	 * @param user  
	 * @return
	 */
	@Override
	public boolean isInPosition(User user)
	{
		return userDao.isInPosition(user);
	}

	@Override
	public Container<User> getDevelopersInPosition(int page, int pageSize)
	{
		return userDao.getDevelopersInPosition(page,pageSize);
	}

	@Override
	public List<User> getMangersInPosition()
	{
		return userDao.getMangersInPosition();
	}
	
}
