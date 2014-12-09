package com.us.bug.qd.dao;

import java.util.List;

import com.us.bug.qd.model.Bug;
import com.us.bug.qd.model.BugReason;
import com.us.bug.qd.model.Task;
import com.us.bug.qd.tools.Container;

public interface BugDao
{
	public void save(Bug bug);
	public Container<Bug> getAllBug(int page,int pageSize);
	public Container<Bug> getSumbittedBugByUserId(Integer testerID,
			int page, int pageSize);
	public Container<Bug> getAllBugs(int page, int pageSize, String bugId,
			String bugTitle, int projectId, int modularId);

	/**
	 * ��������ѯ���ύ��Bug��Ϣ
	 * 
	 * @return
	 */
	public Container<Bug> querySubmitedBug(Integer testerID, int page,
			int pageSize, String bugId, String bugTitle, int projectId,
			int modularId);

	/**
	 * ������Bug��Ϣ
	 * 
	 * @param testerID
	 *            ����ԱId
	 * @param page
	 *            ҳ��
	 * @param pageSize
	 * @param stateName
	 *            bug״̬=������ˡ�
	 * @param bugReasonType
	 *            bugReasonType='�˻�'
	 * @return id,bugId,bugTtile,bugSverity,bugEnvironment,bugExpected,
	 *         bugResult
	 *         ,bugDetail,submitedDay(bug��),projectId,projectName,modularId
	 *         ,modularName,
	 *         versionId,versionName,reasonDetail,submitedDay(t_bugDetailReason��
	 *         ),dealledBugId
	 */
	public Container<BugReason> getToBeCompletedBugByPage(Integer testerID,
			int page, int pageSize, String stateName, String bugReasonType);

	/**
	 * ������Bug
	 * 
	 * @param managerID
	 *            ��Ŀ����Id
	 * @param page
	 * @param pageSize
	 * @param stateName
	 *            bug״̬='������'
	 * @return id,bugId,bugTitle,bugSverity,bugEnvironment,bugExpected,
	 *         bugResult
	 *         ,bugDetail,submitedDay(bug��),projectId,projectName,modularId
	 *         ,modularName, versionId,versionName,testerId,userName
	 *         ˵��:�Ѹ�mangerID�������project�е�ģ���в����ĵ�bug��״̬=�������䡯��bug��Ϣ
	 */
	public Container<Bug> getToBeAssignBugByUserId(Integer managerID,
			int page, int pageSize, String stateName);

	/**
	 * ��ȡ�ѷ����bug��Ϣ 
	 * @param managerID  
	 * @param page
	 * @param pageSize
	 * @return id,bugId,bugTitle,bugDetail,bugEnvironment,bugExpected,bugResult,bugServerity,stateName,submitedDay(bug��)
	 * projectId,projectName,modularId,modularName,versionId,versionName,testerId,UserName,bugPriority,remark,assignManagerId,
	 * dealDeveloperId,submitedDay(task��)
	 * 
	 */
	public Container<Task> getAssignedBugByPage(Integer managerID,int page, int pageSize);

	
	   /**
     * �޸�Bug��Ϣ
     * @param bug
     * ˵��������id(����)�޸�bug��bugId,testerId���������������
     */
	public void update(Bug bug);
	
	public Bug loadById(int id);
	
	
	public Container<Task> getToBeRepairedByPage(Integer developerId, int page,
			int pageSize);

	void updateBugByTask(Task task);
	
	public List<Bug> getBugsByProjectId(int id);
	
	public void changeBugState(Integer bugid,String stateName);
	public void closeBug(int bugid);
}
