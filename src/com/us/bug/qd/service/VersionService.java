package com.us.bug.qd.service;

import java.util.List;

import com.us.bug.qd.model.Version;

public interface VersionService {
	/**
	 * query¼´ProjectI
	 * @param query
	 * @return 
	 */
	public List<Version> getVersionByProjectId(String query);
	public Version loadById(int id);
	public void add(Version version);
	public Version getProjectCurrentVersionByProjectId(int id);
	public void update(Version version);
}
