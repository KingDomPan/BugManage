package com.us.bug.qd.dao;

import com.us.bug.qd.model.Project;
import com.us.bug.qd.tools.Container;

public interface ProjectDao
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
	 * ��ȡ������Ŀ����ģ����Ϣ
	 * page:�ڼ�ҳ
	 * pageSize:ҳ����¼��
	 */
	public Container<Project> getAllProjectAndModularByPage(int page,int pageSize);
	
	
	
	/**
	 * ��ȡ������Ŀ����汾��Ϣ
	 * page:�ڼ�ҳ
	 * pageSize:ҳ����¼��
	 */
	public Container<Project> getAllProjectAndVersionByPage(int page,int pageSize);
	
	
	public Project loadById(int id);
	
	public Project getCurrentProject(int id);
}
