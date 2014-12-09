package com.us.bug.qd.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/***********************************************************************
 * Module: ProjectInfo.java Author: Administrator Purpose: Defines the Class
 * ProjectInfo
 ***********************************************************************/
@Entity
@Table(name = "t_project")
public class Project
{
	private int id;
	private java.lang.String projectId;
	private java.lang.String projectName;
//	private java.lang.String currentVersionName;// 项目当前版本名称
	private User manager;// 项目经理
	private Set<Modular> modulars = new HashSet<Modular>();
	private Set<Version> versions = new HashSet<Version>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Version> getVersions()
	{
		return versions;
	}

	public void setVersions(Set<Version> versions)
	{
		this.versions = versions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Modular> getModulars()
	{
		return modulars;
	}

	public void setModulars(Set<Modular> modulars)
	{
		this.modulars = modulars;
	}

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

	public java.lang.String getProjectId()
	{
		return projectId;
	}

	public void setProjectId(java.lang.String projectId)
	{
		this.projectId = projectId;
	}

	public java.lang.String getProjectName()
	{
		return projectName;
	}

	public void setProjectName(java.lang.String projectName)
	{
		this.projectName = projectName;
	}

//	// @Transient
//	public java.lang.String getCurrentVersionName()
//	{
//		return currentVersionName;
//	}
//
//	public void setCurrentVersionName(java.lang.String currentVersionName)
//	{
//		this.currentVersionName = currentVersionName;
//	}

	@ManyToOne(fetch=FetchType.EAGER)/////////////
	@JoinColumn(name = "managerId")
	public User getManager()
	{
		return manager;
	}

	public void setManager(User manager)
	{
		this.manager = manager;
	}

}