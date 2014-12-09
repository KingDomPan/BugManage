package com.us.bug.qd.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.us.bug.qd.model.Bug;
import com.us.bug.qd.model.BugReason;
import com.us.bug.qd.model.Modular;
import com.us.bug.qd.model.Project;
import com.us.bug.qd.model.Task;
import com.us.bug.qd.model.User;
import com.us.bug.qd.model.Version;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.ModularService;
import com.us.bug.qd.service.ProjectService;
import com.us.bug.qd.service.VersionService;
import com.us.bug.qd.tools.Container;
import com.us.bug.qd.tools.Encode;

@SuppressWarnings("serial")
@Component("bugAction")
@Scope("prototype")
public class BugAction extends ActionSupport implements ModelDriven<Bug>,
		ServletRequestAware, ServletResponseAware
{
	private BugService bugService;
	private ProjectService projectService;
	private ModularService modularService;
	private VersionService versionService;
	private HttpServletRequest request;
	private Encode encode;
	
	private HttpServletResponse response;

	private int projectId;

	private int modularId;

	private int versionId;

	private int versionid;

	private int _bugId;
	private int bugid;

	private Bug bug = new Bug();

	private int page;

	public void closeBug() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		this.bugService.closeBug(bugid);
		response.getWriter().write("{success:true}");
	}
	public int get_bugId()
	{
		return _bugId;
	}

	public void getAllBugs() throws IOException
	{

		response.setContentType("text/json; charset=utf-8");
		if (("".equals(bug.getBugId()) || bug.getBugId() == null)
				&& ("".equals(bug.getBugTitle()) || bug.getBugTitle() == null)
				&& modularId == 0 && projectId == 0)
		{
			Container<Bug> datas = this.bugService.getAllBug(page, 15);
			int totalCount = datas.getTotalPages() * 15;
			List<Bug> conLists = datas.getDataContainer();
			StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
					+ ",topics:[");
			for (int i = 0; i < conLists.size(); i++)
			{
				Bug o = conLists.get(i);
				conJson.append("{bugid:").append(o.getId()).append(",bugId:'")
						.append(o.getBugId()).append("',bugTitle:'")
						.append(o.getBugTitle()).append("',bugEnvironment:'")
						.append(o.getBugEnvironment())
						.append("',bugExpected:'").append(o.getBugExpected())
						.append("',bugDetail:'").append(o.getBugDetail())
						.append("',bugSeverity:'").append(o.getBugSeverity())
						.append("',stateName:'").append(o.getStateName())
						.append("',bugResult:'").append(o.getBugResult())
						.append("',submitedDay:'").append(o.getSubmitedDay())
						/*
						 * .append("',projectid:'")
						 * .append(o.getBug().getBelongs().getId())
						 */
						.append("',projectId:'")
						.append(o.getBelongs().getProjectId())
						.append("',projectName:'")
						.append(o.getBelongs().getProjectName())
						/*
						 * .append("',modularid:'")
						 * .append(o.getBug().getModular().getId())
						 */
						.append("',modularId:'")
						.append(o.getModular().getModularId())
						.append("',modularName:'")
						.append(o.getModular().getModularName())
						.append("',versionId:'")
						.append(o.getVersion().getVersionId())
						.append("',testerId:'").append(o.getTester().getId())
						.append("',testerName:'")
						.append(o.getTester().getUserName())
						.append("',versionName:'")
						.append(o.getVersion().getVersionName()).append("'}");
				if (i < conLists.size() - 1)
					conJson.append(",");
			}
			conJson.append("]}");
			response.getWriter().write(conJson.toString());
		}
		else
		{
			String bugid = new String(bug.getBugId().getBytes("iso-8859-1"),
					"utf-8");
			String bugTitle = new String(bug.getBugTitle().getBytes(
					"iso-8859-1"), "utf-8");
			Container<Bug> datas = this.bugService.getAllBugs(page, 15,
					bugid, bugTitle, projectId, modularId);
			int totalCount = datas.getTotalPages() * 15;
			List<Bug> conLists = datas.getDataContainer();
			StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
					+ ",topics:[");
			for (int i = 0; i < conLists.size(); i++)
			{
				Bug o = conLists.get(i);
				conJson.append("{bugid:").append(o.getId()).append(",bugId:'")
						.append(o.getBugId()).append("',bugTitle:'")
						.append(o.getBugTitle()).append("',bugEnvironment:'")
						.append(o.getBugEnvironment())
						.append("',bugExpected:'").append(o.getBugExpected())
						.append("',bugDetail:'").append(o.getBugDetail())
						.append("',bugSeverity:'").append(o.getBugSeverity())
						.append("',bugResult:'").append(o.getBugResult())
						.append("',submitedDay:'").append(o.getSubmitedDay())
						.append("',stateName:'").append(o.getStateName())
						.append("',projectId:'")
						.append(o.getBelongs().getProjectId())
						.append("',projectName:'")
						.append(o.getBelongs().getProjectName())
						.append("',modularId:'")
						.append(o.getModular().getModularId())
						.append("',modularName:'")
						.append(o.getModular().getModularName())
						.append("',testerId:'").append(o.getTester().getId())
						.append("',testerName:'")
						.append(o.getTester().getUserName())
						.append("',versionId:'")
						.append(o.getVersion().getVersionId())
						.append("',versionName:'")
						.append(o.getVersion().getVersionName()).append("'}");
				if (i < conLists.size() - 1)
					conJson.append(",");
			}
			conJson.append("]}");
			response.getWriter().write(conJson.toString());
		}
	}

	public void getAssignedBugByPage() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<Task> datas = this.bugService.getAssignedBugByPage(
				((User) (request.getSession().getAttribute("user"))).getId(),
				page, 15);
		int totalCount = datas.getTotalPages() * 15;
		List<Task> conLists = datas.getDataContainer();
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conLists.size(); i++)
		{
			Task o = conLists.get(i);
			conJson.append("{bugId:").append(o.getBug().getId())
					.append(",bugid:'").append(o.getBug().getBugId())
					.append("',bugTitle:'").append(o.getBug().getBugTitle())
					.append("',bugEnvironment:'")
					.append(o.getBug().getBugEnvironment())
					.append("',bugExpected:'")
					.append(o.getBug().getBugExpected())
					.append("',bugDetail:'").append(o.getBug().getBugDetail())
					.append("',bugSeverity:'")
					.append(o.getBug().getBugSeverity())
					.append("',bugResult:'").append(o.getBug().getBugResult())
					.append("',submitedDay:'")
					.append(o.getBug().getSubmitedDay())
					.append("',projectId:'")
					.append(o.getBug().getBelongs().getId())
					.append("',projectName:'")
					.append(o.getBug().getBelongs().getProjectName())
					.append("',modularId:'")
					.append(o.getBug().getModular().getId())
					.append("',modularName:'")
					.append(o.getBug().getModular().getModularName())
					.append("',versionId:'")
					.append(o.getBug().getVersion().getVersionId())
					.append("',testerId:'")
					.append(o.getBug().getTester().getId())
					.append("',testerName:'")
					.append(o.getBug().getTester().getUserName())
					.append("',versionName:'")
					.append(o.getBug().getVersion().getVersionName())
					.append("',bugPriority:'").append(o.getBugPriority())
					.append("',remark:'").append(o.getRemark())
					.append("',developerName:'")
					.append(o.getDealDeveloper().getUserName())
					.append("',stateName:'").append(o.getBug().getStateName())
					.append("',submitedDay2:'").append(o.getSubmitedDay())
					.append("'}");
			if (i < conLists.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}

	public Bug getBug()
	{
		return bug;
	}
	public int getBugid()
	{
		return bugid;
	}

	public BugService getBugService()
	{
		return bugService;
	}

	public Encode getEncode()
	{
		return encode;
	}

	@Override
	public Bug getModel()
	{
		return bug;
	}

	public int getModularId()
	{
		return modularId;
	}

	public ModularService getModularService()
	{
		return modularService;
	}

	public int getPage()
	{
		return page;
	}

	public int getProjectId()
	{
		return projectId;
	}

	public ProjectService getProjectService()
	{
		return projectService;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public void getSumbittedBugByUserId() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		if (("".equals(bug.getBugId()) || bug.getBugId() == null)
				&& ("".equals(bug.getBugTitle()) || bug.getBugTitle() == null)
				&& modularId == 0 && projectId == 0)
		{
			Container<Bug> datas = this.bugService.getSumbittedBugByUserId(
					((User) (request.getSession().getAttribute("user")))
							.getId(), page, 20);
			int totalCount = datas.getTotalPages() * 20;
			List<Bug> conLists = datas.getDataContainer();
			StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
					+ ",topics:[");
			for (int i = 0; i < conLists.size(); i++)
			{
				Bug o = conLists.get(i);
				conJson.append("{id:").append(o.getId()).append(",bugId:'")
						.append(o.getBugId()).append("',bugTitle:'")
						.append(o.getBugTitle()).append("',bugEnvironment:'")
						.append(o.getBugEnvironment())
						.append("',bugExpected:'").append(o.getBugExpected())
						.append("',bugDetail:'").append(o.getBugDetail())
						.append("',bugSeverity:'").append(o.getBugSeverity())
						.append("',bugResult:'").append(o.getBugResult())
						.append("',submitedDay:'").append(o.getSubmitedDay())
						.append("',stateName:'").append(o.getStateName())
						.append("',projectid:'").append(o.getBelongs().getId())
						.append("',projectId:'")
						.append(o.getBelongs().getProjectId())
						.append("',projectName:'")
						.append(o.getBelongs().getProjectName())
						.append("',modularId:'")
						.append(o.getModular().getModularId())
						.append("',modularid:'").append(o.getModular().getId())
						.append("',modularName:'")
						.append(o.getModular().getModularName())
						.append("',versionId:'")
						.append(o.getVersion().getVersionId())
						.append("',versionName:'")
						.append(o.getVersion().getVersionName()).append("'}");
				if (i < conLists.size() - 1)
					conJson.append(",");
			}
			conJson.append("]}");
			response.getWriter().write(conJson.toString());
		}
		else
		{
			String bugid = new String(bug.getBugId().getBytes("iso-8859-1"),
					"utf-8");
			String bugTitle = new String(bug.getBugTitle().getBytes(
					"iso-8859-1"), "utf-8");
			Container<Bug> datas = this.bugService.querySubmitedBug(
					((User) (request.getSession().getAttribute("user")))
							.getId(), page, 20, bugid, bugTitle, projectId,
					modularId);
			int totalCount = datas.getTotalPages() * 20;
			List<Bug> conLists = datas.getDataContainer();
			StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
					+ ",topics:[");
			for (int i = 0; i < conLists.size(); i++)
			{
				Bug o = conLists.get(i);
				conJson.append("{id:").append(o.getId()).append(",bugId:'")
						.append(o.getBugId()).append("',bugTitle:'")
						.append(o.getBugTitle()).append("',bugEnvironment:'")
						.append(o.getBugEnvironment())
						.append("',bugExpected:'").append(o.getBugExpected())
						.append("',bugDetail:'").append(o.getBugDetail())
						.append("',bugSeverity:'").append(o.getBugSeverity())
						.append("',bugResult:'").append(o.getBugResult())
						.append("',submitedDay:'").append(o.getSubmitedDay())
						.append("',stateName:'").append(o.getStateName())
						.append("',projectId:'").append(o.getBelongs().getId())
						.append("',projectName:'")
						.append(o.getBelongs().getProjectName())
						.append("',modularId:'").append(o.getModular().getId())
						.append("',modularName:'")
						.append(o.getModular().getModularName())
						.append("',versionId:'")
						.append(o.getVersion().getVersionId())
						.append("',versionName:'")
						.append(o.getVersion().getVersionName()).append("'}");
				if (i < conLists.size() - 1)
					conJson.append(",");
			}
			conJson.append("]}");
			response.getWriter().write(conJson.toString());
		}

	}

	// /成功
	public void getToBeAssignBugByUserId() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<Bug> datas = this.bugService.getToBeAssignBugByUserId(
				((User) (request.getSession().getAttribute("user"))).getId(),
				page, 15, "待分配");
		int totalCount = datas.getTotalPages() * 15;
		List<Bug> conLists = datas.getDataContainer();
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conLists.size(); i++)
		{
			Bug o = conLists.get(i);
			conJson.append("{bugId:")
					.append(o.getId())
					.append(",bugid:'")
					.append(o.getBugId())
					.append("',bugTitle:'")
					.append(o.getBugTitle())
					.append("',bugEnvironment:'")
					.append(o.getBugEnvironment())
					.append("',bugExpected:'")
					.append(o.getBugExpected())
					.append("',bugDetail:'")
					.append(o.getBugDetail())
					.append("',bugSeverity:'")
					.append(o.getBugSeverity())
					.append("',bugResult:'")
					.append(o.getBugResult())
					.append("',submitedDay:'")
					.append(o.getSubmitedDay())
					.append("',projectId:'")
					.append(o.getBelongs().getId())
					.append("',projectid:'")
					.append(o.getBelongs().getProjectId())
					.append("',projectName:'")
					.append(o.getBelongs().getProjectName())
					// .append("',modularId:'").append(o.getModular().getId())
					.append("',modularid:'")
					.append(o.getModular().getModularId())
					.append("',modularName:'")
					.append(o.getModular().getModularName())
					.append("',versionId:'")
					.append(o.getVersion().getVersionId())
					// .append("',testerId:'").append(o.getTester().getId())
					.append("',testerid:'").append(o.getTester().getUserId())
					.append("',testerName:'")
					.append(o.getTester().getUserName())
					.append("',versionName:'")
					.append(o.getVersion().getVersionName()).append("'}");
			if (i < conLists.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}

	public void getToBeCompletedBugByPage() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<BugReason> datas = this.bugService.getToBeCompletedBugByPage(
				((User) (request.getSession().getAttribute("user"))).getId(),
				page, 15, "待完善", "回退");
		int totalCount = datas.getTotalPages() * 15;
		List<BugReason> conLists = datas.getDataContainer();
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conLists.size(); i++)
		{
			BugReason o = conLists.get(i);
			conJson.append("{id:").append(o.getDealledBug().getId())
					.append(",bugId:'").append(o.getDealledBug().getBugId())
					.append("',bugTitle:'")
					.append(o.getDealledBug().getBugTitle())
					.append("',bugEnvironment:'")
					.append(o.getDealledBug().getBugEnvironment())
					.append("',bugExpected:'")
					.append(o.getDealledBug().getBugExpected())
					.append("',bugDetail:'")
					.append(o.getDealledBug().getBugDetail())
					.append("',bugSeverity:'")
					.append(o.getDealledBug().getBugSeverity())
					.append("',bugResult:'")
					.append(o.getDealledBug().getBugResult())
					.append("',submitedDay:'")
					.append(o.getDealledBug().getSubmitedDay())
					.append("',projectid:'")
					.append(o.getDealledBug().getBelongs().getId())
					.append("',projectId:'")
					.append(o.getDealledBug().getBugId())
					.append("',projectName:'")
					.append(o.getDealledBug().getBelongs().getProjectName())
					.append("',modularId:'")
					.append(o.getDealledBug().getModular().getModularId())
					.append("',modularid:'")
					.append(o.getDealledBug().getModular().getId())
					.append("',modularName:'")
					.append(o.getDealledBug().getModular().getModularName())
					.append("',versionId:'")
					.append(o.getDealledBug().getVersion().getVersionId())
					.append("',reasonDetail:'").append(o.getReasonDetail())
					.append("',managerName:'")
					.append(o.getManager().getUserName())
					.append("',submitedDay3:'").append(o.getSubmitedDay())
					.append("',versionName:'")
					.append(o.getDealledBug().getVersion().getVersionName())
					.append("'}");
			if (i < conLists.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());

	}

	public void getToBeRepairedByPage() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<Task> datas = this.bugService.getToBeRepairedByPage(
				((User) (request.getSession().getAttribute("user"))).getId(),
				page, 15);
		int totalCount = datas.getTotalPages() * 15;
		List<Task> conLists = datas.getDataContainer();
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conLists.size(); i++)
		{
			Task o = conLists.get(i);
			conJson.append("{bugid:").append(o.getBug().getId())
					.append(",bugId:'").append(o.getBug().getBugId())
					.append("',bugTitle:'").append(o.getBug().getBugTitle())
					.append("',bugEnvironment:'")
					.append(o.getBug().getBugEnvironment())
					.append("',bugExpected:'")
					.append(o.getBug().getBugExpected())
					.append("',bugDetail:'").append(o.getBug().getBugDetail())
					.append("',bugSeverity:'")
					.append(o.getBug().getBugSeverity())
					.append("',bugResult:'").append(o.getBug().getBugResult())
					.append("',submitedDay:'")
					.append(o.getBug().getSubmitedDay())
					/*
					 * .append("',projectid:'")
					 * .append(o.getBug().getBelongs().getId())
					 */
					.append("',projectId:'")
					.append(o.getBug().getBelongs().getProjectId())
					.append("',projectName:'")
					.append(o.getBug().getBelongs().getProjectName())
					/*
					 * .append("',modularid:'")
					 * .append(o.getBug().getModular().getId())
					 */
					.append("',modularId:'")
					.append(o.getBug().getModular().getModularId())
					.append("',modularName:'")
					.append(o.getBug().getModular().getModularName())
					.append("',versionId:'")
					.append(o.getBug().getVersion().getVersionId())
					.append("',testerId:'")
					.append(o.getBug().getTester().getId())
					.append("',testerName:'")
					.append(o.getBug().getTester().getUserName())
					.append("',versionName:'")
					.append(o.getBug().getVersion().getVersionName())
					.append("',taskid:'").append(o.getTaskId())
					.append("',bugPriority:'").append(o.getBugPriority())
					.append("',remark:'").append(o.getRemark())
					.append("',managerId:'")
					.append(o.getAssignManager().getId())
					.append("',managerName:'")
					.append(o.getAssignManager().getUserName())
					.append("',developerId:'")
					.append(o.getDealDeveloper().getId())
					.append("',developerName:'")
					.append(o.getDealDeveloper().getUserName())
					.append("',submitedDay2:'").append(o.getSubmitedDay())
					.append("'}");
			if (i < conLists.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}

	public int getVersionid()
	{
		return versionid;
	}

	public int getVersionId()
	{
		return versionId;
	}

	public VersionService getVersionService()
	{
		return versionService;
	}

	public void save() throws IOException
	{
		bug.setSubmitedDay(new Date());
		bug.setStateName("待分配");
		bug.setTester((User) request.getSession().getAttribute("user"));
		bug.setBugId(encode.getBugIdEncode(projectId, modularId));
		Project project = new Project();
		project.setId(projectId);
		bug.setBelongs(project);

		Modular modular = new Modular();
		modular.setId(modularId);
		bug.setModular(modular);

		Version version = new Version();
		version.setVersionId(versionId);
		bug.setVersion(version);

		bugService.save(bug);
		response.getWriter().write("{success:true}");
	}

	public void set_bugId(int _bugId)
	{
		this._bugId = _bugId;
	}

	public void setBug(Bug bug)
	{
		this.bug = bug;
	}

	public void setBugid(int bugid)
	{
		this.bugid = bugid;
	}

	@Resource(name = "bugServiceImpl")
	public void setBugService(BugService bugService)
	{
		this.bugService = bugService;
	}

	@Resource(name="encode")
	public void setEncode(Encode encode)
	{
		this.encode = encode;
	}

	public void setModularId(int modularId)
	{
		this.modularId = modularId;
	}

	@Resource(name = "modularServiceImpl")
	public void setModularService(ModularService modularService)
	{
		this.modularService = modularService;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public void setProjectId(int projectId)
	{
		this.projectId = projectId;
	}

	@Resource(name = "projectServiceImpl")
	public void setProjectService(ProjectService projectService)
	{
		this.projectService = projectService;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	public void setVersionid(int versionid)
	{
		this.versionid = versionid;
	}

	public void setVersionId(int versionId)
	{
		this.versionId = versionId;
	}

	@Resource(name = "versionServiceImpl")
	public void setVersionService(VersionService versionService)
	{
		this.versionService = versionService;
	}

	public void updateSubmited() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Bug _bug = bugService.loadById(_bugId);
		_bug.setBugTitle(bug.getBugTitle());
		_bug.setBugSeverity(bug.getBugSeverity());
		_bug.setBugEnvironment(bug.getBugEnvironment());
		_bug.setBugDetail(bug.getBugDetail());
		_bug.setBugExpected(bug.getBugExpected());
		_bug.setBugResult(bug.getBugResult());
		_bug.setBugTitle(bug.getBugTitle());
		_bug.setStateName("待分配");
		Project project = projectService.loadById(projectId);
		Modular modular = modularService.loadById(modularId);
		Version version = versionService.loadById(versionid);
		_bug.setBelongs(project);
		_bug.setModular(modular);
		_bug.setVersion(version);
		bugService.update(_bug);
		response.getWriter().write("{success:true}");
	}
}
