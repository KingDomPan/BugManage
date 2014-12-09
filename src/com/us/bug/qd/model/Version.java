/***********************************************************************
 * Module:  Version.java
 * Author:  Administrator
 * Purpose: Defines the Class ProjectVersion
 ***********************************************************************/
package com.us.bug.qd.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_version")
public class Version
{
	private int versionId;
	private Project project;// 版本的项目信息
	private String versionName;// 版本名称
	private Date beginTime;// 开始时间
	private Date endTime;// 结束时间
	private String updatedContend;// 更新内容

	@Id
	@GeneratedValue
	public int getVersionId()
	{
		return versionId;
	}

	public void setVersionId(int versionId)
	{
		this.versionId = versionId;
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

	public String getVersionName()
	{
		return versionName;
	}

	public void setVersionName(String versionName)
	{
		this.versionName = versionName;
	}

	public Date getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(Date beginTime)
	{
		this.beginTime = beginTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public String getUpdatedContend()
	{
		return updatedContend;
	}

	public void setUpdatedContend(String updatedContend)
	{
		this.updatedContend = updatedContend;
	}

}
