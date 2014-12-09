package com.us.bug.qd.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.us.bug.qd.dao.Dao;
import com.us.bug.qd.model.Bug;
import com.us.bug.qd.model.BugReason;
import com.us.bug.qd.model.Modular;
import com.us.bug.qd.model.Project;
import com.us.bug.qd.model.User;
import com.us.bug.qd.model.Version;
import com.us.bug.qd.service.ModularService;
import com.us.bug.qd.service.ProjectService;
import com.us.bug.qd.service.UserService;
import com.us.bug.qd.service.VersionService;
import com.us.bug.qd.tools.Encode;
import com.us.bug.qd.tools.MD5Code;

@Component("addDaoImpl")
public class AddDaoImpl extends Dao
{
	private Encode encode;
	private MD5Code md5Code;
	private ProjectService projectService;
	private UserService userService;
	private ModularService modularService;
	private VersionService versionService;

	public Encode getEncode()
	{
		return encode;
	}

	@Resource(name = "encode")
	public void setEncode(Encode encode)
	{
		this.encode = encode;
	}

	public MD5Code getMd5Code()
	{
		return md5Code;
	}

	@Resource(name = "md5Code")
	public void setMd5Code(MD5Code md5Code)
	{
		this.md5Code = md5Code;
	}

	public ProjectService getProjectService()
	{
		return projectService;
	}

	@Resource(name = "projectServiceImpl")
	public void setProjectService(ProjectService projectService)
	{
		this.projectService = projectService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Resource(name = "userServiceImpl")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public ModularService getModularService()
	{
		return modularService;
	}

	@Resource(name = "modularServiceImpl")
	public void setModularService(ModularService modularService)
	{
		this.modularService = modularService;
	}

	public VersionService getVersionService()
	{
		return versionService;
	}

	@Resource(name = "versionServiceImpl")
	public void setVersionService(VersionService versionService)
	{
		this.versionService = versionService;
	}

	public void addUsers()
	{
		List<User> users = new ArrayList<User>();
		for (int i = 1; i <= 200; i++)
		{
			User user = new User();
			user.setBirthday(new Date());
			user.setUserState("在职");
			user.setUserId(encode.getUserIdEncode());
			user.setPwd(md5Code.getMD5ofStr("888888"));
			user.setRemark("暂无备注信息");
			user.setSex("男");
			user.setTelephone("13400517145");
			int k = i % 3;
			switch (k)
			{
				case 0:
					user.setPost("测试员");
					user.setUserName("tester" + i);
					break;
				case 1:
					user.setPost("项目经理");
					user.setUserName("admin" + i);
					break;
				case 2:
					user.setPost("开发人员");
					user.setUserName("developer" + i);
					break;
				default:
					break;
			}
			users.add(user);
		}
		this.getCrudHandler().save(users);

	}

	public void addProjects()
	{
		List<Project> projects = new ArrayList<Project>();
		for (int i = 1; i <= 200; i++)
		{
			Project project = new Project();
			project.setProjectId(encode.getProjectIdEncode());
			project.setProjectName("项目--" + i);
			project.setManager(userService.loadById(1));
			projects.add(project);
		}
		this.getCrudHandler().save(projects);
	}

	@SuppressWarnings("deprecation")
	public void addVersions()
	{
		Project project = new Project();
		project.setId(1);

		List<Version> versions = new ArrayList<Version>();
		for (int i = 1; i <= 200; i++)
		{
			Version version = new Version();
			version.setProject(project);
			version.setVersionName(encode.getVersionNameEncode(1));
			version.setBeginTime(new Date(2012,2,i));
			version.setEndTime(new Date(2012,2,i+2));
			version.setUpdatedContend("从上一版本更新而来");
			versions.add(version);
		}
		this.getCrudHandler().save(versions);
	}

	public void addModulars()
	{
		Project project = new Project();
		project.setId(1);
		String name=projectService.loadById(1).getProjectName();
		User de=userService.loadById(2);
		List<Modular> modulars = new ArrayList<Modular>();
		for (int i = 1; i <=200; i++)
		{
			Modular modular = new Modular();
			modular.setProject(project);
			modular.setModularId(encode.getModularIdEncode(1));
			modular.setModularName("模块" + i+"--"+name);
			modular.setDeveloper(de);
			modulars.add(modular);
		}
		this.getCrudHandler().save(modulars);
	}

	public void addBugs()
	{
		Project project=projectService.loadById(1);
		Modular modular=modularService.loadById(1);
		User tester=userService.loadById(3);
		Version version=versionService.loadById(1);
		List<Bug> bugs = new ArrayList<Bug>();
		for (int i = 0; i < 200; i++)
		{
			Bug bug = new Bug();
			bug.setBugId(encode.getBugIdEncode(1, 1));
			bug.setSubmitedDay(new Date());
			bug.setStateName("待分配");
			bug.setBugDetail("当运行到XXX的时候出现系统无响应状态");
			bug.setBugExpected("运行到XXX时无该状态");
			bug.setBugResult("运行到XXX时无该状态");
			bug.setBugTitle("BBBBBB~");
			bug.setBugSeverity("Major");
			bug.setBugEnvironment("WIN8");
			bug.setTester(tester);
			bug.setBelongs(project);
			bug.setModular(modular);
			bug.setVersion(version);
			bugs.add(bug);
		}
		this.getCrudHandler().save(bugs);
	}

	public void addBugReasons()
	{
		List<BugReason> bugReasons = new ArrayList<BugReason>();
		for (int i = 0; i < 32; i++)
		{
			BugReason bugReason = new BugReason();

			bugReason.setSubmitedDay(new Date());

			Bug bug = new Bug();
			bug.setId(i);

			bugReason.setDealledBug(bug);
			bugReason.setReasonDetail("补办u不不不uubububu 不不");
			bugReason.setBugReasonType("回退");
			User user = new User();
			user.setId(2);
			bugReason.setManager(user);

			bugReasons.add(bugReason);
		}
		this.getCrudHandler().save(bugReasons);
	}

}
