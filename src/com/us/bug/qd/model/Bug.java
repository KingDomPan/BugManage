/***********************************************************************
 * Module:  Bug.java
 * Author:  Administrator
 * Purpose: Defines the Class BugInfo
 ***********************************************************************/
package com.us.bug.qd.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_bug")
public class Bug
{
	private int id;
	private java.lang.String bugId;// ���
	private java.lang.String bugTitle;// ����
	private String bugSeverity;// ���س̶�
	private Project belongs;// bug����������Ŀ
    private Version version;//bug����ʱ��Ŀ�汾��Ϣ
	private Modular modular;// bug����ģ��
	private java.lang.String bugEnvironment;// ���л���
	private User tester;// �ύ��Ա
	private Date submitedDay;// �ύ����
	private String stateName;
	private java.lang.String bugExpected;// ����Ԥ��
	private java.lang.String bugResult;// ���Խ��
	private java.lang.String bugDetail;// ��ϸ����
	private Set<Task> tasks = new HashSet<Task>();
	private Set<BugReason> bugReasons = new HashSet<BugReason>();
	@OneToOne
	@JoinColumn(name = "projectId")
	public Project getBelongs()
	{
		return belongs;
	}
	public java.lang.String getBugDetail()
	{
		return bugDetail;
	}

	public java.lang.String getBugEnvironment()
	{
		return bugEnvironment;
	}

	public java.lang.String getBugExpected()
	{
		return bugExpected;
	}


	public java.lang.String getBugId()
	{
		return bugId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dealledBug")
	public Set<BugReason> getBugReasons()
	{
		return bugReasons;
	}

	public java.lang.String getBugResult()
	{
		return bugResult;
	}

	public String getBugSeverity()
	{
		return bugSeverity;
	}

	public java.lang.String getBugTitle()
	{
		return bugTitle;
	}

	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}

	@OneToOne
	@JoinColumn(name = "modularId")
	public Modular getModular()
	{
		return modular;
	}

	@Column(updatable=true)
	public String getStateName()
	{
		return stateName;
	}

	public Date getSubmitedDay()
	{
		return submitedDay;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bug")
	public Set<Task> getTasks()
	{
		return tasks;
	}

	@OneToOne
	@JoinColumn(name = "testerId")
	public User getTester()
	{
		return tester;
	}

	@OneToOne
	 @JoinColumn(name = "versionId")
	 public Version getVersion()
	 {
	 return version;
	 }

	public void setBelongs(Project belongs)
	{
		this.belongs = belongs;
	}

	 public void setBugDetail(java.lang.String bugDetail)
	{
		this.bugDetail = bugDetail;
	}
	 public void setBugEnvironment(java.lang.String bugEnvironment)
	{
		this.bugEnvironment = bugEnvironment;
	}

	public void setBugExpected(java.lang.String bugExpected)
	{
		this.bugExpected = bugExpected;
	}

	public void setBugId(java.lang.String bugId)
	{
		this.bugId = bugId;
	}

	public void setBugReasons(Set<BugReason> bugReasons)
	{
		this.bugReasons = bugReasons;
	}

	public void setBugResult(java.lang.String bugResult)
	{
		this.bugResult = bugResult;
	}

	public void setBugSeverity(String bugSeverity)
	{
		this.bugSeverity = bugSeverity;
	}

	public void setBugTitle(java.lang.String bugTitle)
	{
		this.bugTitle = bugTitle;
	}

	public void setId(int id)
	{
		this.id = id;
	}

//	@OneToOne
//	@JoinColumn(name = "stateId")
//	public State getState()
//	{
//		return state;
//	}
//
//	public void setState(State state)
//	{
//		this.state = state;
//	}

	public void setModular(Modular modular)
	{
		this.modular = modular;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public void setSubmitedDay(Date submitedDay)
	{
		this.submitedDay = submitedDay;
	}

	public void setTasks(Set<Task> tasks)
	{
		this.tasks = tasks;
	}

	public void setTester(User tester)
	{
		this.tester = tester;
	}

	public void setVersion(Version version)
	 {
	 this.version = version;
	 }

}