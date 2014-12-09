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
import com.us.bug.qd.model.RepairReason;
import com.us.bug.qd.model.User;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.RepairReasonService;
import com.us.bug.qd.service.RepairService;
import com.us.bug.qd.tools.Container;

@SuppressWarnings("serial")
@Component("repairReasonAction")
@Scope("prototype")
public class RepairReasonAction extends ActionSupport implements
		ServletResponseAware, ServletRequestAware, ModelDriven<RepairReason>
{
	private HttpServletResponse response;
	private HttpServletRequest request;
	private RepairReasonService repairReasonService;
	private BugService bugService;
	private RepairService repairService;
	private int rid;
	private int bugid;
	private int page;

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getBugid()
	{
		return bugid;
	}

	public void setBugid(int bugid)
	{
		this.bugid = bugid;
	}

	public int getRid()
	{
		return rid;
	}

	public void setRid(int rid)
	{
		this.rid = rid;
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

	public BugService getBugService()
	{
		return bugService;
	}

	@Resource(name = "bugServiceImpl")
	public void setBugService(BugService bugService)
	{
		this.bugService = bugService;
	}

	private RepairReason repairReason = new RepairReason();

	public void add() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		RepairReason r = new RepairReason();
		Repair repair = repairService.loadById(rid);
		r.setReasonDetail(repairReason.getReasonDetail());
		r.setRepair(repair);
		r.setRepairReason("不通过");
		r.setSubmitedDay(new Date());
		this.repairReasonService.add(r);

		Bug b = this.bugService.loadById(bugid);
		b.setStateName("重修复");
		this.bugService.update(b);
		response.getWriter().write("{success:true}");
	}

	public void getReRepairBugByUserId() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<RepairReason> repairReasons =  this.repairReasonService.getReRepairBugByUserId(
				((User) (request.getSession().getAttribute("user"))).getId(),
				"重修复", page, 15);
		int totalCount = repairReasons.getTotalPages() * 15;
		List<RepairReason> conLists = repairReasons.getDataContainer();
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conLists.size(); i++)
		{
			RepairReason r = conLists.get(i);
			conJson.append("{bugid:").append(r.getRepair().getRepairBug().getId()).append(",bugId:'")
					.append(r.getRepair().getRepairBug().getBugId()).append("',bugTitle:'")
					.append(r.getRepair().getRepairBug().getBugTitle()).append("',bugEnvironment:'")
					.append(r.getRepair().getRepairBug().getBugEnvironment())
					.append("',bugExpected:'").append(r.getRepair().getRepairBug().getBugExpected())
					.append("',bugDetail:'").append(r.getRepair().getRepairBug().getBugDetail())
					.append("',bugSeverity:'").append(r.getRepair().getRepairBug().getBugSeverity())
					.append("',bugResult:'").append(r.getRepair().getRepairBug().getBugResult())
					.append("',submitedDay:'").append(r.getRepair().getRepairBug().getSubmitedDay())
					.append("',projectId:'")
					.append(r.getRepair().getRepairBug().getBelongs().getProjectId())
					.append("',projectName:'")
					.append(r.getRepair().getRepairBug().getBelongs().getProjectName())
					.append("',modularId:'")
					.append(r.getRepair().getRepairBug().getModular().getModularId())
					.append("',modularName:'")
					.append(r.getRepair().getRepairBug().getModular().getModularName())
					.append("',versionId:'")
					.append(r.getRepair().getRepairBug().getVersion().getVersionId())
					.append("',testerName:'")
					.append(r.getRepair().getRepairBug().getTester().getUserName())
					.append("',versionName:'")
					.append(r.getRepair().getRepairBug().getVersion().getVersionName())
					.append("',submitedDay5:'").append(r.getRepair().getSubmitedDay())
					.append("',repairScheme:'").append(r.getRepair().getRepairScheme())
					.append("',submitedDay6:'").append(r.getSubmitedDay())	
					.append("',repairReason:'").append(r.getRepairReason())
					.append("',repairDetail:'").append(r.getReasonDetail())
					.append("',rid:'").append(r.getRrid())
					.append("'}");
			if (i < conLists.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}

	@Override
	public RepairReason getModel()
	{
		return repairReason;
	}

	public RepairReasonService getRepairReasonService()
	{
		return repairReasonService;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	@Resource(name = "repairReasonServiceImpl")
	public void setRepairReasonService(RepairReasonService repairReasonService)
	{
		this.repairReasonService = repairReasonService;
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

}
