package com.us.bug.qd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/***********************************************************************
 * Module: ModularInfo.java Author: Administrator Purpose: Defines the Class
 * ModularInfo
 ***********************************************************************/

@Entity
@Table(name = "t_modular")
public class Modular
{
	private int id;
	private java.lang.String modularId;
	private java.lang.String modularName;
	private Project project;// 模块所属项目
	private User developer;// 模块负责人

	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public java.lang.String getModularId()
	{
		return modularId;
	}

	public void setModularId(java.lang.String modularId)
	{
		this.modularId = modularId;
	}

	public java.lang.String getModularName()
	{
		return modularName;
	}

	public void setModularName(java.lang.String modularName)
	{
		this.modularName = modularName;
	}

	@ManyToOne
	@JoinColumn(name = "projectId")
	public Project getProject()
	{
		return project;
	}

	public void setProject(Project project)
	{
		this.project = project;
	}

	@OneToOne
	@JoinColumn(name = "developerId")
	public User getDeveloper()
	{
		return developer;
	}

	public void setDeveloper(User developer)
	{
		this.developer = developer;
	}

}