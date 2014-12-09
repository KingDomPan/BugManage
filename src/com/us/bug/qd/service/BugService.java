package com.us.bug.qd.service;

import java.util.List;

import com.us.bug.qd.model.Bug;
import com.us.bug.qd.model.BugReason;
import com.us.bug.qd.model.Task;
import com.us.bug.qd.tools.Container;

public interface BugService
{
	/*
	 * 提交bug
	 */
	public void save(Bug bug);

	/*
	 * 分页读取某测试人员已提交而处于待分配的Bug信息 查询
	 * 
	 * @param testerId
	 * 
	 * @param page
	 * 
	 * @return
	 */
	public Container<Bug> getSumbittedBugByUserId(Integer testerID, int page,
			int pageSize);

	/**
	 * 多条件查询已提交的Bug信息
	 * 
	 * @return
	 */
	public Container<Bug> querySubmitedBug(Integer testerID, int page,
			int pageSize, String bugId, String bugTitle, int projectId,
			int modularId);

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
	public Container<BugReason> getToBeCompletedBugByPage(Integer testerID,
			int page, int pageSize, String stateName, String bugReasonType);

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
	public Container<Bug> getToBeAssignBugByUserId(Integer managerID, int page,
			int pageSize, String stateName);

	/**
	 * 修改Bug信息
	 * 
	 * @param bug
	 *            说明：根据id(主键)修改bug除bugId,testerId外的其它所有属性
	 */
	public void update(Bug bug);

	/**
	 * 获取已分配的bug信息
	 * 
	 * @param managerID
	 * @param page
	 * @param pageSize
	 * @return id,bugId,bugTitle,bugDetail,bugEnvironment,bugExpected,bugResult,
	 *         bugServerity,stateName,submitedDay(bug表)
	 *         projectId,projectName,modularId
	 *         ,modularName,versionId,versionName,
	 *         testerId,UserName,bugPriority,remark,assignManagerId,
	 *         dealDeveloperId,submitedDay(task表)
	 * 
	 */
	public Container<Task> getAssignedBugByPage(Integer managerID, int page,
			int pageSize);

	public Container<Task> getToBeRepairedByPage(Integer developerId, int page,
			int pageSize);

	public Bug loadById(int id);

	void updateBugByTask(Task task);

	public List<Bug> getBugsByProjectId(int id);

	public void changeBugState(Integer bugid, String stateName);

	public void closeBug(int bugid);

	public Container<Bug> getAllBugs(int page, int pageSize, String bugId,
			String bugTitle, int projectId, int modularId);

	public Container<Bug> getAllBug(int page, int pageSize);
}
