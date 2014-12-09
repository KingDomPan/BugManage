package com.us.bug.qd.tools;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.us.bug.qd.model.Modular;
import com.us.bug.qd.model.Project;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.ModularService;
import com.us.bug.qd.service.ProjectService;
import com.us.bug.qd.service.UserService;
import com.us.bug.qd.service.VersionService;

@Component("encode")
public class Encode
{
	private ModularService modularService;
	private BugService bugService;
	private ProjectService projectService;
	private UserService userService;
	private VersionService versionService;
	private long id;
	private Random random=new Random();

	public long getId()
	{
		id = random.nextInt(10);
		if (id < 0)
			id = id * (-1);
		return id;
	}

	public BugService getBugService()
	{
		return bugService;
	}

	public ModularService getModularService()
	{
		return modularService;
	}

	public ProjectService getProjectService()
	{
		return projectService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public VersionService getVersionService()
	{
		return versionService;
	}

	@Resource(name = "bugServiceImpl")
	public void setBugService(BugService bugService)
	{
		this.bugService = bugService;
	}

	@Resource(name = "modularServiceImpl")
	public void setModularService(ModularService modularService)
	{
		this.modularService = modularService;
	}

	@Resource(name = "projectServiceImpl")
	public void setProjectService(ProjectService projectService)
	{
		this.projectService = projectService;
	}

	@Resource(name = "userServiceImpl")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	@Resource(name = "versionServiceImpl")
	public void setVersionService(VersionService versionService)
	{
		this.versionService = versionService;
	}

	public String getBugIdEncode(int projectid, int modularid)
	{
		long id = getId();
		Modular m = modularService.loadById(modularid);
		StringBuffer sb = new StringBuffer();
		sb.append("B_").append(SerialNumberUtil.toSerialNumber(id)).append("_")
				.append(m.getModularId());
		return sb.toString();
	}

	public String getProjectIdEncode()
	{
		long id = getId();
		StringBuffer sb = new StringBuffer();
		sb.append("P_");
		sb.append(SerialNumberUtil.toSerialNumber(id));
		return sb.toString();
	}

	public String getModularIdEncode(int projectid)
	{
		long id = getId();
		StringBuffer sb = new StringBuffer();
		Project p = projectService.loadById(projectid);
		p.getProjectId();
		sb.append("M_").append(SerialNumberUtil.toSerialNumber(id)).append("_")
				.append(p.getProjectId());
		return sb.toString();
	}

	public String getUserIdEncode()
	{
		long id = getId();
		StringBuffer sb = new StringBuffer();
		sb.append("U_");
		sb.append(SerialNumberUtil.toSerialNumber(id));
		return sb.toString();
	}
	
	
	public String getVersionNameEncode(int projectid)
	{
		long id = getId();
		StringBuffer sb = new StringBuffer();
		Project p = projectService.loadById(projectid);
		p.getProjectId();
		sb.append("V_").append(SerialNumberUtil.toSerialNumber(id)).append("_")
				.append(p.getProjectId());
		return sb.toString();
	}
}
