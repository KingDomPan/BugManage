package com.us.bug.qd.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.us.bug.qd.dao.VersionDao;
import com.us.bug.qd.model.Version;
import com.us.bug.qd.service.VersionService;
import com.us.bug.qd.service.impl.VersionServiceImpl;

@Component("versionServiceImpl")
public class VersionServiceImpl implements VersionService
{

	private VersionDao versionDao;

	@Override
	public Version getProjectCurrentVersionByProjectId(int id)
	{
		return versionDao.getProjectCurrentVersionByProjectId(id);
	}
	
	

	@Override
	public void update(Version version)
	{
		this.versionDao.update(version);
	}

	@Override
	public void add(Version version)
	{
		this.versionDao.add(version);
	}

	@Override
	public List<Version> getVersionByProjectId(String query)
	{
		return versionDao.getVersionByProjectId(query);
	}

	public VersionDao getVersionDao()
	{
		return versionDao;
	}

	@Override
	public Version loadById(int id)
	{
		return versionDao.loadById(id);
	}

	@Resource(name = "versionDaoImpl")
	public void setVersionDao(VersionDao versionDao)
	{
		this.versionDao = versionDao;
	}

}
