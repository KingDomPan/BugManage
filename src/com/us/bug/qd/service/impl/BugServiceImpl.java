package com.us.bug.qd.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.BugDao;
import com.us.bug.qd.model.Bug;
import com.us.bug.qd.model.BugReason;
import com.us.bug.qd.model.Task;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.tools.Container;

@Component("bugServiceImpl")
public class BugServiceImpl implements BugService
{
	@Override
	public Container<Bug> getAllBug(int page, int pageSize)
	{
		return this.bugDao.getAllBug(page, pageSize);
	}

	@Override
	public Container<Bug> getAllBugs(int page, int pageSize, String bugId,
			String bugTitle, int projectId, int modularId)
	{
		return this.bugDao.getAllBugs(page, pageSize, bugId, bugTitle,
				projectId, modularId);
	}

	@Override
	public void changeBugState(Integer bugid, String stateName)
	{
		this.bugDao.changeBugState(bugid, stateName);
	}

	@Override
	public void closeBug(int bugid)
	{
		this.bugDao.closeBug(bugid);
	}

	@Override
	public List<Bug> getBugsByProjectId(int id)
	{
		return bugDao.getBugsByProjectId(id);
	}

	@Override
	public void updateBugByTask(Task task)
	{
		bugDao.updateBugByTask(task);
	}

	@Override
	public Container<Task> getToBeRepairedByPage(Integer developerId, int page,
			int pageSize)
	{
		return bugDao.getToBeRepairedByPage(developerId, page, pageSize);
	}

	@Override
	public Bug loadById(int id)
	{
		return bugDao.loadById(id);
	}

	private BugDao bugDao;

	public BugDao getBugDao()
	{
		return bugDao;
	}

	@Override
	public Container<Task> getAssignedBugByPage(Integer managerID, int page,
			int pageSize)
	{
		return bugDao.getAssignedBugByPage(managerID, page, pageSize);
	}

	/**
	 * 修改Bug信息
	 * 
	 * @param bug
	 *            说明：根据id(主键)修改bug除bugId,testerId外的其它所有属性
	 */
	@Override
	public void update(Bug bug)
	{
		this.bugDao.update(bug);
	}

	@Resource(name = "bugDaoImpl")
	public void setBugDao(BugDao bugDao)
	{
		this.bugDao = bugDao;
	}

	@Override
	public void save(Bug bug)
	{
		bugDao.save(bug);
	}

	@Override
	public Container<Bug> getSumbittedBugByUserId(Integer testerID, int page,
			int pageSize)
	{
		return bugDao.getSumbittedBugByUserId(testerID, page, pageSize);
	}

	@Override
	public Container<Bug> querySubmitedBug(Integer testerID, int page,
			int pageSize, String bugId, String bugTitle, int projectId,
			int modularId)
	{

		return bugDao.querySubmitedBug(testerID, page, pageSize, bugId,
				bugTitle, projectId, modularId);
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
	 * @return id,bugId,bugTtile,bugSverity,bugEnvironment,bugExpected,
	 *         bugResult
	 *         ,bugDetail,submitedDay(bug表),projectId,projectName,modularId
	 *         ,modularName,
	 *         versionId,versionName,reasonDetail,submitedDay(t_bugDetailReason表
	 *         ),dealledBugId
	 */
	@Override
	public Container<BugReason> getToBeCompletedBugByPage(Integer testerID,
			int page, int pageSize, String stateName, String bugReasonType)
	{
		return bugDao.getToBeCompletedBugByPage(testerID, page, pageSize,
				stateName, bugReasonType);
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
		return bugDao.getToBeAssignBugByUserId(managerID, page, pageSize,
				stateName);
	}

}
