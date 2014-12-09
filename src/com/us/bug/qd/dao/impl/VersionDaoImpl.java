package com.us.bug.qd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.dao.VersionDao;
import com.us.bug.qd.model.Version;

@Component("versionDaoImpl")
public class VersionDaoImpl extends Dao implements VersionDao
{
	@Override
	public void add(Version version)
	{
		this.getCrudHandler().save(version);
	}

	@Override
	public Version getProjectCurrentVersionByProjectId(int id)
	{
		List<Version> versions = this.getCrudHandler().getList(Version.class,
				"where obj.project.id=" + id, "obj.versionId", "desc");
		if (versions.size() > 0)
			return versions.get(0);
		else
		{
			return null;
		}

	}

	@Override
	public void update(Version version)
	{
		this.getCrudHandler().update(version);
	}

	@Override
	public Version loadById(int id)
	{
		return this.getCrudHandler().loadById(Version.class, id);
	}

	/**
	 * query¼´projectId
	 * 
	 * @param query
	 * @return
	 */
	@Override
	public List<Version> getVersionByProjectId(String query)
	{
		return this.getCrudHandler().getList(Version.class,
				"where obj.project.id='" + query + "'");
	}

}
