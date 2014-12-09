package com.us.bug.qd.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.us.bug.qd.model.Bug;
import com.us.bug.qd.model.Version;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.ProjectService;
import com.us.bug.qd.service.VersionService;
import com.us.bug.qd.tools.Encode;

@SuppressWarnings("serial")
@Component("versionAction")
@Scope("prototype")
public class VersionAction extends ActionSupport implements
		ServletResponseAware, ModelDriven<Version>
{
	private Version version = new Version();
	private VersionService versionService;
	private HttpServletResponse response;
	private ProjectService projectService;
	private BugService bugService;
	private Encode encode;
	public Encode getEncode()
	{
		return encode;
	}

	@Resource(name="encode")
	public void setEncode(Encode encode)
	{
		this.encode = encode;
	}
	public BugService getBugService()
	{
		return bugService;
	}

	@Resource(name = "bugServiceImpl")
	public void setBugService(BugService bugService)
	{
		this.bugService = bugService;
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

	private String query;
	private int projectid;

	public int getProjectid()
	{
		return projectid;
	}

	public void setProjectid(int projectid)
	{
		this.projectid = projectid;
	}

	public void add() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Version cVersion = this.versionService
				.getProjectCurrentVersionByProjectId(projectid);
		if (cVersion != null)
		{
			cVersion.setEndTime(new Date());
			List<Bug> bugs = bugService.getBugsByProjectId(projectid);
			if (bugs != null)
			{
				for (int i = 0; i < bugs.size(); i++)
				{
					Bug b = bugs.get(i);
					b.setStateName("´ý·ÖÅä");
					this.bugService.update(b);
				}
			}
			this.versionService.update(cVersion);
		}

		version.setProject(projectService.loadById(projectid));
		version.setVersionName(encode.getVersionNameEncode(projectid));
		version.setBeginTime(new Date());
		this.versionService.add(version);
		response.getWriter().write("{success:true}");
	}

	@Override
	public Version getModel()
	{
		return version;
	}

	public String getQuery()
	{
		return query;
	}

	public Version getVersion()
	{
		return version;
	}

	public void getVersionByProjectId() throws IOException
	{
		response.setCharacterEncoding("utf-8");
		if (!"".equals(query))
		{
			List<Version> versionList = versionService
					.getVersionByProjectId(query);
			StringBuffer versionJson = new StringBuffer("[");
			for (int i = 0; i < versionList.size(); i++)
			{
				Version v = versionList.get(i);
				versionJson.append("{versionId:").append(v.getVersionId())
						.append(",versionName:'").append(v.getVersionName())
						.append("'}");
				if (i < versionList.size() - 1)
					versionJson.append(",");
			}
			versionJson.append("]");
			response.getWriter().write(versionJson.toString());
		}
	}

	public void getVersionsByProjectId() throws IOException
	{
		response.setCharacterEncoding("utf-8");
		if (!"".equals(query))
		{
			List<Version> versionList = versionService
					.getVersionByProjectId(query);
			StringBuffer versionJson = new StringBuffer("[");
			for (int i = 0; i < versionList.size(); i++)
			{
				Version v = versionList.get(i);
				versionJson.append("{versionId:'").append(v.getVersionId())
						.append("',versionName:'").append(v.getVersionName())
						.append("',beginTime:'").append(v.getBeginTime())
						.append("',endTime:'").append(v.getEndTime())
						.append("',updatedContend:'")
						.append(v.getUpdatedContend()).append("'}");
				if (i < versionList.size() - 1)
					versionJson.append(",");
			}
			versionJson.append("]");
			response.getWriter().write(versionJson.toString());
		}
	}

	public VersionService getVersionService()
	{
		return versionService;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	@Override
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	public void setVersion(Version version)
	{
		this.version = version;
	}

	@Resource(name = "versionServiceImpl")
	public void setVersionService(VersionService versionService)
	{
		this.versionService = versionService;
	}

}
