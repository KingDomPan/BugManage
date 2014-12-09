package com.us.bug.qd.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/***********************************************************************
 * Module:  User.java
 * Author:  Administrator
 * Purpose: Defines the Class UserInfo
 ***********************************************************************/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User
{
	private int id;// 主键 自增标识
	private String userId;// 用户ID
	private String userName;// 用户名
	private String post;// 用户职位
	private String pwd;// 用户密码
	private String sex;// 性别
	private Date birthday;// 出生日期
	private String telephone;// 电话
	private String userState;// 用户状态
	private String remark;// 用户备注信息
	private Set<Task> tasks = new HashSet<Task>();
//	private boolean isBugScan;// bug查看权限
//	private boolean isProjectManagement;// 项目管理权限
//	private boolean isUserManagement;// 用户信息管理权限
//	private boolean isAssign;// 分配bug权限
//	private boolean isRepair;// 修复bug权限
//	private boolean isSubmit;// 提交bug权限
//	private boolean isUserPowerAssignment;// 权限分配权限

	private Set<Project> projects = new HashSet<Project>();

	public Date getBirthday()
	{
		return birthday;
	}

	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}

	public String getPost()
	{
		return post;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager")
	public Set<Project> getProjects()
	{
		return projects;
	}

	@Column(columnDefinition="varchar(255) default '888888'",name="pwd")
	public String getPwd()
	{
		return pwd;
	}

	public String getRemark()
	{
		return remark;
	}

	public String getSex()
	{
		return sex;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dealDeveloper")
	public Set<Task> getTasks()
	{
		return tasks;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public String getUserId()
	{
		return userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getUserState()
	{
		return userState;
	}

//	@Column(columnDefinition = "bit default 0", name = "isAssign")
//	public boolean isAssign()
//	{
//		return isAssign;
//	}
//
//	@Column(columnDefinition = "bit default 0", name = "isBugScan")
//	public boolean isBugScan()
//	{
//		return isBugScan;
//	}
//
//	@Column(columnDefinition = "bit default 0", name = "isProjectManagement")
//	public boolean isProjectManagement()
//	{
//		return isProjectManagement;
//	}
//
//	@Column(columnDefinition = "bit default 0", name = "isRepair")
//	public boolean isRepair()
//	{
//		return isRepair;
//	}
//
//	@Column(columnDefinition = "bit default 0", name = "isSubmit")
//	public boolean isSubmit()
//	{
//		return isSubmit;
//	}
//
//	@Column(columnDefinition = "bit default 0", name = "isUserManagement")
//	public boolean isUserManagement()
//	{
//		return isUserManagement;
//	}
//
//	@Column(columnDefinition = "bit default 0", name = "isUserPowerAssignment")
//	public boolean isUserPowerAssignment()
//	{
//		return isUserPowerAssignment;
//	}

//	public void setAssign(boolean isAssign)
//	{
//		this.isAssign = isAssign;
//	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

//	public void setBugScan(boolean isBugScan)
//	{
//		this.isBugScan = isBugScan;
//	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setPost(String post)
	{
		this.post = post;
	}

	// @Column(columnDefinition = "bit default 0", name = "isExist")
	// public boolean isExist()
	// {
	// return isExist;
	// }
	//
	// public void setExist(boolean isExist)
	// {
	// this.isExist = isExist;
	// }

//	public void setProjectManagement(boolean isProjectManagement)
//	{
//		this.isProjectManagement = isProjectManagement;
//	}

	public void setProjects(Set<Project> projects)
	{
		this.projects = projects;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

//	public void setRepair(boolean isRepair)
//	{
//		this.isRepair = isRepair;
//	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

//	public void setSubmit(boolean isSubmit)
//	{
//		this.isSubmit = isSubmit;
//	}

	public void setTasks(Set<Task> tasks)
	{
		this.tasks = tasks;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

//	public void setUserManagement(boolean isUserManagement)
//	{
//		this.isUserManagement = isUserManagement;
//	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

//	public void setUserPowerAssignment(boolean isUserPowerAssignment)
//	{
//		this.isUserPowerAssignment = isUserPowerAssignment;
//	}

	public void setUserState(String userState)
	{
		this.userState = userState;
	}

}