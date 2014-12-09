package com.us.bug.qd.dao;

import com.us.bug.qd.model.Project;
import com.us.bug.qd.tools.Container;

public interface ProjectDao
{
	/**
	 * 
	 * @param page
	 *            分页的第几页
	 * @return 第几页的所有Project
	 */
	public Container<Project> getProjectByPage(int page);

	/**
	 * 
	 * @param project
	 *            修改项目名称和负责人
	 */
	public void update(Project project);

	/**
	 * 
	 * @param project
	 *            ：projectName and manager 添加项目
	 */
	public void add(Project project);
	
	
	/**
	 * 获取所有项目及其模块信息
	 * page:第几页
	 * pageSize:页最大记录数
	 */
	public Container<Project> getAllProjectAndModularByPage(int page,int pageSize);
	
	
	
	/**
	 * 获取所有项目及其版本信息
	 * page:第几页
	 * pageSize:页最大记录数
	 */
	public Container<Project> getAllProjectAndVersionByPage(int page,int pageSize);
	
	
	public Project loadById(int id);
	
	public Project getCurrentProject(int id);
}
