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
import com.us.bug.qd.model.Task;
import com.us.bug.qd.model.TaskReason;
import com.us.bug.qd.model.User;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.TaskReasonService;
import com.us.bug.qd.service.TaskService;
import com.us.bug.qd.tools.Container;

@SuppressWarnings("serial")
@Component("taskReasonAction")
@Scope("prototype")
public class TaskReasonAction extends ActionSupport implements
		ModelDriven<TaskReason>, ServletRequestAware, ServletResponseAware
{
	private BugService bugService;
	private TaskReason taskReason = new TaskReason();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private TaskReasonService taskReasonService;
	private TaskService taskService;
	private int page;

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public TaskService getTaskService()
	{
		return taskService;
	}

	@Resource(name = "taskServiceImpl")
	public void setTaskService(TaskService taskService)
	{
		this.taskService = taskService;
	}

	private int bugid;
	private int taskid;

	public int getTaskid()
	{
		return taskid;
	}

	public void setTaskid(int taskid)
	{
		this.taskid = taskid;
	}

	public int getBugid()
	{
		return bugid;
	}

	public void setBugid(int bugid)
	{
		this.bugid = bugid;
	}

	public TaskReasonService getTaskReasonService()
	{
		return taskReasonService;
	}

	@Resource(name = "taskReasonServiceImpl")
	public void setTaskReasonService(TaskReasonService taskReasonService)
	{
		this.taskReasonService = taskReasonService;
	}

	public BugService getBugService()
	{
		return bugService;
	}

	@Override
	public TaskReason getModel()
	{
		return taskReason;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	@Resource(name = "bugServiceImpl")
	public void setBugService(BugService bugService)
	{
		this.bugService = bugService;
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

	public void add() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Bug bug = bugService.loadById(bugid);
		bug.setStateName("重分配");
		bugService.update(bug);

		Task task = taskService.loadById(taskid);
		task.setActive(false);
		taskService.update(task);
		taskReason.setTask(task);
		taskReason.setSubmitedDay(new Date());
		taskReason.setTaskReasonType("拒绝");
		this.taskReasonService.add(taskReason);
		response.getWriter().write("{success:true}");
	}

	public void getReAssignBugByUserId() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<TaskReason> taskReasons = this.taskReasonService.getReAssignBugByUserId(
				((User) (request.getSession().getAttribute("user"))).getId(),
				"重分配", page, 15);
		int totalCount = taskReasons.getTotalPages() * 20;
		List<TaskReason> conLists = taskReasons.getDataContainer();
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conLists.size(); i++)
		{
			TaskReason t = conLists.get(i);
			conJson.append("{bugId:").append(t.getTask().getBug().getId()).append(",bugid:'")
					.append(t.getTask().getBug().getBugId()).append("',bugTitle:'")
					.append(t.getTask().getBug().getBugTitle()).append("',bugEnvironment:'")
					.append(t.getTask().getBug().getBugEnvironment())
					.append("',bugExpected:'").append(t.getTask().getBug().getBugExpected())
					.append("',bugDetail:'").append(t.getTask().getBug().getBugDetail())
					.append("',bugSeverity:'").append(t.getTask().getBug().getBugSeverity())
					.append("',bugResult:'").append(t.getTask().getBug().getBugResult())
					.append("',submitedDay:'").append(t.getTask().getBug().getSubmitedDay())
					.append("',testerId:'")
					.append(t.getTask().getBug().getTester().getId())
					.append("',testerName:'")
					.append(t.getTask().getBug().getTester().getUserName())
					.append("',projectId:'")
					.append(t.getTask().getBug().getBelongs().getId())
					.append("',projectName:'")
					.append(t.getTask().getBug().getBelongs().getProjectName())
					.append("',modularId:'")
					.append(t.getTask().getBug().getModular().getModularId())
					.append("',modularName:'")
					.append(t.getTask().getBug().getModular().getModularName())
					.append("',versionId:'")
					.append(t.getTask().getBug().getVersion().getVersionId())
					.append("',versionName:'")
					.append(t.getTask().getBug().getVersion().getVersionName())
					.append("',bugPriority:'").append(t.getTask().getBugPriority())
					.append("',remark:'").append(t.getTask().getRemark())
					.append("',developerName:'").append(t.getTask().getDealDeveloper().getUserName())
					.append("',submitedDay4:'").append(t.getSubmitedDay())
					.append("',submitedDay2:'").append(t.getTask().getSubmitedDay())
					.append("',reasonDetail:'").append(t.getReasonDetail())
					.append("',taskReasonType:'").append(t.getTaskReasonType())
					.append("'}");
			if (i < conLists.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}
}
