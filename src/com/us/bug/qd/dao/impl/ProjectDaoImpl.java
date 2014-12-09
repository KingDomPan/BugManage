package com.us.bug.qd.dao.impl;

import org.springframework.stereotype.Component;
import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.dao.ProjectDao;
import com.us.bug.qd.model.Project;
import com.us.bug.qd.tools.Container;

@Component("projectDaoImpl")
public class ProjectDaoImpl extends Dao implements ProjectDao
{
	@Override
	public Project loadById(int id)
	{
		return this.getCrudHandler().loadById(Project.class, id);
	}

	/**
	 * 
	 * @param page
	 *            分页的第几页
	 * @return 第几页的所有Project
	 */
	@Override
	public Container<Project> getProjectByPage(int page)
	{
		return this.getPageSpliter().getListByPage(Project.class, page, 15,
				"obj.projectName", "DESC");
	}

	@Override
	public void update(Project project)
	{
		this.getCrudHandler().update(project);
	}

	@Override
	public void add(Project project)
	{
		this.getCrudHandler().save(project);
	}

	@Override
	public Container<Project> getAllProjectAndModularByPage(int page,
			int pageSize)
	{
		Container<Project> container = this.getPageSpliter().getListByPage(
				Project.class, page, pageSize,
				"obj.id", "asc");
		return container;
	}

	@Override
	public Container<Project> getAllProjectAndVersionByPage(int page,
			int pageSize)
	{
		Container<Project> container = this.getPageSpliter().getListByPage(
				Project.class, page, pageSize, "left join fetch obj.versions",
				"obj.id", "asc");
		return container;
	}

	@Override
	public Project getCurrentProject(int id)
	{
		return this.loadById(id);
	}

}
