package com.us.bug.qd.dao;

import java.util.List;

import com.us.bug.qd.model.Version;

public interface VersionDao
{
	/**
	 * query¼´ProjectI
	 * @param query
	 * @return 
	 */
	public List<Version> getVersionByProjectId(String query);
	public void add(Version version);
	public Version loadById(int id);
	public Version getProjectCurrentVersionByProjectId(int id);
	public void update(Version version);
}
