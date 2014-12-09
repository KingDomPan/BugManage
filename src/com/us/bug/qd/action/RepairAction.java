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
import com.us.bug.qd.model.Repair;
import com.us.bug.qd.model.User;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.RepairService;
import com.us.bug.qd.tools.Container;

@SuppressWarnings("serial")
@Component("repairAction")
@Scope("prototype")
public class RepairAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, ModelDriven<Repair>
{

	private Repair repair = new Repair();
	private RepairService repairService;
	private int page;

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public RepairService getRepairService()
	{
		return repairService;
	}

	@Resource(name = "repairServiceImpl")
	public void setRepairService(RepairService repairService)
	{
		this.repairService = repairService;
	}

	private HttpServletRequest request;
	private HttpServletResponse response;
	private BugService bugService;
	private int bugid;

	public int getBugid()
	{
		return bugid;
	}

	public void setBugid(int bugid)
	{
		this.bugid = bugid;
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

	@Override
	public Repair getModel()
	{
		return repair;
	}

	@Override
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void add() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		User dev = (User) request.getSession().getAttribute("user");
		Bug bug = bugService.loadById(bugid);
		Repair r = new Repair();
		r.setRepairBug(bug);
		r.setRepairDeveloper(dev);
		r.setSubmitedDay(new Date());
		r.setRepairScheme(repair.getRepairScheme());
		bug.setStateName("待验证");
		this.repairService.add(r);
		this.bugService.update(bug);
		response.getWriter().write("{success:true}");
	}

	public void getDeveloperSubmitedBugByUserId() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<Repair> repairs = this.repairService
				.getDeveloperSubmitedBugByUserId(((User) (request.getSession()
						.getAttribute("user"))).getId(), page, 15);
		List<Repair> conRepairs = repairs.getDataContainer();
		int totalCount = repairs.getTotalPages() * 15;
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conRepairs.size(); i++)
		{
			Repair r = conRepairs.get(i);
			conJson.append("{bugid:").append(r.getRepairBug().getId())
					.append(",bugId:'").append(r.getRepairBug().getBugId())
					.append("',bugTitle:'")
					.append(r.getRepairBug().getBugTitle())
					.append("',bugEnvironment:'")
					.append(r.getRepairBug().getBugEnvironment())
					.append("',bugExpected:'")
					.append(r.getRepairBug().getBugExpected())
					.append("',bugDetail:'")
					.append(r.getRepairBug().getBugDetail())
					.append("',bugSeverity:'")
					.append(r.getRepairBug().getBugSeverity())
					.append("',bugResult:'")
					.append(r.getRepairBug().getBugResult())
					.append("',submitedDay:'")
					.append(r.getRepairBug().getSubmitedDay())
					/*.append("',projectid:'")
					.append(r.getRepairBug().getBelongs().getId())*/
					.append("',projectId:'")
					.append(r.getRepairBug().getBelongs().getProjectId())
					.append("',projectName:'")
					.append(r.getRepairBug().getBelongs().getProjectName())
					/*.append("',modularid:'")
					.append(r.getRepairBug().getModular().getId())*/
					.append("',modularId:'")
					.append(r.getRepairBug().getModular().getModularId())
					.append("',modularName:'")
					.append(r.getRepairBug().getModular().getModularName())
					.append("',versionId:'")
					.append(r.getRepairBug().getVersion().getVersionId())
					.append("',testerId:'")
					.append(r.getRepairBug().getTester().getId())
					.append("',testerName:'")
					.append(r.getRepairBug().getTester().getUserName())
					.append("',versionName:'")
					.append(r.getRepairBug().getVersion().getVersionName())
					.append("',repairScheme:'").append(r.getRepairScheme())
					.append("',stateName:'")
					.append(r.getRepairBug().getStateName())
					.append("',submitedDay4:'").append(r.getSubmitedDay())
					.append("'}");
			if (i < conRepairs.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}

	public void getToBeCheckedBugByUserId() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<Repair> repairs =this.repairService.getToBeCheckedBugByUserId(
				((User) (request.getSession().getAttribute("user"))).getId(),
				"待验证", page, 15);
		List<Repair> conRepairs = repairs.getDataContainer();
		int totalCount = repairs.getTotalPages() * 15;
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conRepairs.size(); i++)
		{
			Repair r = conRepairs.get(i);
			conJson.append("{bugid:").append(r.getRepairBug().getId())
					.append(",bugId:'").append(r.getRepairBug().getBugId())
					.append("',bugTitle:'")
					.append(r.getRepairBug().getBugTitle())
					.append("',bugEnvironment:'")
					.append(r.getRepairBug().getBugEnvironment())
					.append("',bugExpected:'")
					.append(r.getRepairBug().getBugExpected())
					.append("',bugDetail:'")
					.append(r.getRepairBug().getBugDetail())
					.append("',bugSeverity:'")
					.append(r.getRepairBug().getBugSeverity())
					.append("',bugResult:'")
					.append(r.getRepairBug().getBugResult())
					.append("',submitedDay:'")
					.append(r.getRepairBug().getSubmitedDay())
					.append("',projectid:'")
					.append(r.getRepairBug().getBelongs().getId())
					.append("',projectId:'")
					.append(r.getRepairBug().getBelongs().getProjectId())
					.append("',projectName:'")
					.append(r.getRepairBug().getBelongs().getProjectName())
					.append("',modularid:'")
					.append(r.getRepairBug().getModular().getId())
					.append("',modularId:'")
					.append(r.getRepairBug().getModular().getModularId())
					.append("',modularName:'")
					.append(r.getRepairBug().getModular().getModularName())
					.append("',versionId:'")
					.append(r.getRepairBug().getVersion().getVersionId())
					.append("',testerId:'")
					.append(r.getRepairBug().getTester().getId())
					.append("',testerName:'")
					.append(r.getRepairBug().getTester().getUserName())
					.append("',versionName:'")
					.append(r.getRepairBug().getVersion().getVersionName())
					.append("',repairScheme:'").append(r.getRepairScheme())
					.append("',developerid:'")
					.append(r.getRepairDeveloper().getId())
					.append("',rid:'").append(r.getRepairId())
					.append("',developerId:'")
					.append(r.getRepairDeveloper().getUserId())
					.append("',developerName:'")
					.append(r.getRepairDeveloper().getUserName())
					.append("',submitDay4:'").append(r.getSubmitedDay())
					.append("'}");
			if (i < conRepairs.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}
}
