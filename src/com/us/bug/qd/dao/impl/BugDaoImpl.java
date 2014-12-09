package com.us.bug.qd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import com.us.bug.qd.dao.BugDao;
import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.model.Bug;
import com.us.bug.qd.model.BugReason;
import com.us.bug.qd.model.Task;
import com.us.bug.qd.tools.Container;

@Component("bugDaoImpl")
public class BugDaoImpl extends Dao implements BugDao
{
	@Override
	public Container<Task> getAssignedBugByPage(Integer managerID, int page,
			int pageSize)
	{
		Container<Task> container = null;
		container = this.getPageSpliter().getListByPage(Task.class, page,
				pageSize, "where obj.bug.belongs.manager.id=" + managerID,
				"obj.id", "asc");
		return container;
	}

	@Override
	public void save(Bug bug)
	{
		this.getCrudHandler().save(bug);
	}

	/**
	 * 
	 * @param testerID
	 *            测试人员ID
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            页最大记录数
	 * @return 测试人员提交的bug集合对象
	 */

	public Container<Bug> getSumbittedBugByUserId(Integer testerID, int page,
			int pageSize)
	{
		Container<Bug> container = null;
		container = this.getPageSpliter().getListByPage(Bug.class, page,
				pageSize, "left join fetch obj.tester",
				"where obj.tester.id=" + testerID, "obj.id", "desc");
		return container;
	}

	@Override
	public Container<Bug> querySubmitedBug(Integer testerID, int page,
			int pageSize, String bugId, String bugTitle, int projectId,
			int modularId)
	{
		StringBuffer where = new StringBuffer();
		where.append("where obj.tester.id='").append(testerID.toString())
				.append("'");
		if (!("".equals(bugId)))
		{
			where.append(" and obj.bugId like '%").append(bugId).append("%'");
		}

		if (!("".equals(bugTitle)))
		{
			where.append(" and obj.bugTitle like '%").append(bugTitle)
					.append("%'");
		}

		if (projectId != 0)
		{
			where.append(" and obj.belongs.id = ").append(projectId);
		}

		if (modularId != 0)
		{
			where.append(" and obj.modular.id = ").append(modularId);
		}

		Container<Bug> container = this.getPageSpliter().getListByPage(
				Bug.class, page, pageSize/* , fetch.toString() */,
				where.toString(), "obj.id", "desc");
		return container;
	}

	/**
	 * 待完善Bug信息
	 * 
	 * @param testerID
	 *            测试员Id
	 * @param page
	 *            页码
	 * @param pageSize
	 * @param stateName
	 *            bug状态=‘待审核’
	 * @param bugReasonType
	 *            bugReasonType='退回'
	 * @return id,bugId,bugTitle,bugSverity,bugEnvironment,bugExpected,
	 *         bugResult
	 *         ,bugDetail,submitedDay(bug表),projectId,projectName,modularId
	 *         ,modularName,
	 *         versionId,versionName,reasonDetail,submitedDay(t_bugReason表
	 *         ),deal
	 */

	public Container<BugReason> getToBeCompletedBugByPage(Integer testerID,
			int page, int pageSize, String stateName, String bugReasonType)
	{
		Container<BugReason> container = null;
		container = this.getPageSpliter().getListByPage(
				BugReason.class,
				page,
				pageSize,
				"left join fetch obj.dealledBug",
				"where obj.bugReasonType='" + bugReasonType
						+ "' and obj.dealledBug.stateName='" + stateName
						+ "' and obj.dealledBug.tester.id=" + testerID,
				"obj.bugDealReasonId", "asc");
		return container;
	}

	/**
	 * 待分配Bug
	 * 
	 * @param managerID
	 *            项目经理Id
	 * @param page
	 * @param pageSize
	 * @param stateName
	 *            bug状态='待分配'
	 * @return id,bugId,bugTitle,bugSverity,bugEnvironment,bugExpected,
	 *         bugResult
	 *         ,bugDetail,submitedDay(bug表),projectId,projectName,modularId
	 *         ,modularName, versionId,versionName,testerId,userName
	 *         说明:把该mangerID所负责的project中的模块中产生的的bug且状态=‘待分配’的bug信息
	 */

	@Override
	public Container<Bug> getToBeAssignBugByUserId(Integer managerID, int page,
			int pageSize, String stateName)
	{
		Container<Bug> container = this.getPageSpliter().getListByPage(
				Bug.class,
				page,
				pageSize,
				"where obj.stateName='" + stateName
						+ "' and obj.belongs.manager.id=" + managerID,
				"obj.id", "asc");
		return container;
	}

	/**
	 * 修改Bug信息
	 * 
	 * @param bug
	 *            说明：根据id(主键)修改bug除bugId,testerId外的其它所有属性
	 */
	public void update(Bug bug)
	{
		this.getCrudHandler().update(bug);
	}

	@Override
	public Bug loadById(int id)
	{
		Bug bug = this.getCrudHandler().loadById(Bug.class, id);
		return bug;
	}

	@Override
	public Container<Task> getToBeRepairedByPage(Integer developerId, int page,
			int pageSize)
	{
		Container<Task> container = null;
		container = this.getPageSpliter().getListByPage(
				Task.class,
				page,
				pageSize,
				"where obj.dealDeveloper.id=" + developerId
						+ " and obj.bug.stateName='"
						+ "待修复' and obj.active=true", "obj.taskId", "asc");
		return container;
	}

	@Override
	public Container<Bug> getAllBug(int page, int pageSize)
	{
		return this.getPageSpliter().getListByPage(Bug.class, page, pageSize,
				"obj.submitedDay", "desc");
	}

	@Override
	public void updateBugByTask(Task task)
	{
		Bug bug = this.loadById(task.getBug().getId());
		if ("待修复".equals(bug.getStateName()))
		{
			bug.setStateName("重分配");
			this.update(bug);
		}
	}

	@Override
	public List<Bug> getBugsByProjectId(int id)
	{
		return this.getCrudHandler().getList(Bug.class,
				"where obj.belongs.id=" + id + " and obj.stateName='" + "延期'");
	}

	@Override
	public void changeBugState(Integer bugid, String stateName)
	{
		Bug bug = this.loadById(bugid);
		bug.setStateName("已修复");
		this.update(bug);
	}

	@Override
	public void closeBug(int bugid)
	{
		this.changeBugState(bugid, null);
	}

	@Override
	public Container<Bug> getAllBugs(int page, int pageSize, String bugId,
			String bugTitle, int projectId, int modularId)
	{
		StringBuffer where = new StringBuffer();
		where.append("where 1=1");
		if (!("".equals(bugId)))
		{
			where.append(" and obj.bugId like '%").append(bugId).append("%'");
		}

		if (!("".equals(bugTitle)))
		{
			where.append(" and obj.bugTitle like '%").append(bugTitle)
					.append("%'");
		}

		if (projectId != 0)
		{
			where.append(" and obj.belongs.id = ").append(projectId);
		}

		if (modularId != 0)
		{
			where.append(" and obj.modular.id = ").append(modularId);
		}

		Container<Bug> container = this.getPageSpliter().getListByPage(
				Bug.class, page, pageSize, where.toString(), "obj.submitedDay",
				"desc");
		return container;
	}

}
