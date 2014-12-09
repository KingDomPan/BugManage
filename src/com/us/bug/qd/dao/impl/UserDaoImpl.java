package com.us.bug.qd.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.dao.UserDao;
import com.us.bug.qd.model.User;
import com.us.bug.qd.tools.Container;
import com.us.bug.qd.tools.MD5Code;

@Component(value = "userDaoImpl")
public class UserDaoImpl extends Dao implements UserDao
{
	private MD5Code md5Code;

	public MD5Code getMd5Code()
	{
		return md5Code;
	}

	@Resource(name = "md5Code")
	public void setMd5Code(MD5Code md5Code)
	{
		this.md5Code = md5Code;
	}

	@Override
	public Container<User> getUserByPage(int page, int pageSize)
	{
		Container<User> container = null;
		container = this.getPageSpliter().getListByPage(User.class, page,
				pageSize, "obj.id", "asc");
		return container;
	}

	@Override
	public void update(User user)
	{
		this.getCrudHandler().update(user);
	}

	@Override
	public void save(User user)
	{
		this.getCrudHandler().save(user);
	}

	@Override
	public User isExist(User user)
	{
		String hql = "from User u where u.userName='" + user.getUserName()
				+ "' and u.pwd='" + md5Code.getMD5ofStr(user.getPwd()) + "'";
		User users = (User) this.getCrudHandler().sclar(hql);
		return users;
	}

	@Override
	public User loadById(int id)
	{
		return this.getCrudHandler().loadById(User.class, id);
	}

	@Override
	public List<User> getMangersInPosition()
	{
		return this.getCrudHandler().getList(User.class,
				"where obj.post='" + "项目经理' and obj.userState='" + "在职'");
	}

	@Override
	public boolean isInPosition(User user)
	{
		User u = this.loadById(user.getId());
		if (u.getUserState().equals("在职"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Container<User> getDevelopersInPosition(int page, int pageSize)
	{
		return this.getPageSpliter().getListByPage(User.class, page, pageSize,
				"where obj.post='" + "开发人员' and obj.userState='" + "在职'",
				"obj.id", "asc");

	}

	@Override
	public List<User> getDevelopersInPosition()
	{
		return this.getCrudHandler().getList(User.class,
				"where obj.post='" + "开发人员' and obj.userState='" + "在职'",
				"obj.id", "asc");

	}

}
