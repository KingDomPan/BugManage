package com.us.bug.qd.service;

import com.us.bug.qd.model.Project;
import com.us.bug.qd.tools.Container;

public interface ProjectService
{
	/**
	 * 
	 * @param page
	 *            ��ҳ�ĵڼ�ҳ
	 * @return �ڼ�ҳ������Project
	 */
	public Container<Project> getProjectByPage(int page);

	/**
	 * 
	 * @param project
	 *            �޸���Ŀ���ƺ͸�����
	 */
	public void update(Project project);

	/**
	 * 
	 * @param project
	 *            ��projectName and manager �����Ŀ
	 */
	public void add(Project project);

	/**
	 * ��ȡ���е���Ŀ��ģ����Ϣ
	 * 
	 * @param page
	 * @param pageSize
	 * @return 
	 *         projectId,projectName,modularId,modularName,managerId,userName(��Ŀ����
	 *         ),developerId,userName(������Ա)
	 */
	public Container<Project> getAllProjectAndModularByPage(int page,
			int pageSize);

	/**
	 * ��ȡ������Ŀ����汾��Ϣ page:�ڼ�ҳ pageSize:ҳ����¼��
	 */
	public Container<Project> getAllProjectAndVersionByPage(int page,
			int pageSize);
	
	public Project loadById(int id);
	
	public Project getCurrentProject(int id);
}
