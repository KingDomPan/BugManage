package com.us.bug.qd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.ProjectDao;
import com.us.bug.qd.model.Project;
import com.us.bug.qd.service.ProjectService;
import com.us.bug.qd.tools.Container;

@Component("projectServiceImpl")
public class ProjectServiceImpl implements ProjectService
{
	@Override
	public Project loadById(int id)
	{
		return projectDao.loadById(id);
	}

	private ProjectDao projectDao;
	
	public ProjectDao getProjectDao()
	{
		return projectDao;
	}
	
	@Resource(name="projectDaoImpl")
	public void setProjectDao(ProjectDao projectDao)
	{
		this.projectDao = projectDao;
	}

	@Override
	public void update(Project project)
	{
		projectDao.update(project);
	}

	@Override
	public void add(Project project)
	{
		projectDao.add(project);
	}

	@Override
	public Container<Project> getAllProjectAndModularByPage(int page,
			int pageSize)
	{
		return projectDao.getAllProjectAndModularByPage(page, pageSize);
	}

	@Override
	public Container<Project> getAllProjectAndVersionByPage(int page,
			int pageSize)
	{
		return projectDao.getAllProjectAndVersionByPage(page, pageSize);
	}

	@Override
	public Container<Project> getProjectByPage(int page)
	{
		return projectDao.getProjectByPage(page);
	}

	@Override
	public Project getCurrentProject(int id)
	{
		return projectDao.getCurrentProject(id);
	}
	
}
