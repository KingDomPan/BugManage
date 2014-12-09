package com.us.bug.qd.action;

import java.io.IOException;
import java.util.Date;

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
import com.us.bug.qd.model.User;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.TaskService;

@SuppressWarnings("serial")
@Component("taskAction")
@Scope("prototype")
public class TaskAction extends ActionSupport implements ModelDriven<Task>,
		ServletRequestAware, ServletResponseAware
{
	private BugService bugService;

	private TaskService taskService;

	private HttpServletRequest request;

	private HttpServletResponse response;
	private Task task = new Task();
	private int bugId;
	private int dealDeveloperId;
	public void assignBug() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Bug bug = bugService.loadById(bugId);
		bug.setStateName("´ýÐÞ¸´");
		bugService.update(bug);
		task.setBug(bug);
		User dealDeveloper = new User();
		dealDeveloper.setId(dealDeveloperId);
		task.setDealDeveloper(dealDeveloper);

		User manager = new User();
		manager.setId(((User) request.getSession().getAttribute("user"))
				.getId());

		task.setAssignManager(manager);
		task.setActive(true);
		task.setSubmitedDay(new Date());
		taskService.assingBug(task);

		response.getWriter().write("{success:true}");
	}


	public int getBugId()
	{
		return bugId;
	}

	public BugService getBugService()
	{
		return bugService;
	}

	public int getDealDeveloperId()
	{
		return dealDeveloperId;
	}

	@Override
	public Task getModel()
	{
		return task;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public TaskService getTaskService()
	{
		return taskService;
	}

	public void setBugId(int bugId)
	{
		this.bugId = bugId;
	}

	@Resource(name = "bugServiceImpl")
	public void setBugService(BugService bugService)
	{
		this.bugService = bugService;
	}

	public void setDealDeveloperId(int dealDeveloperId)
	{
		this.dealDeveloperId = dealDeveloperId;
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

	@Resource(name = "taskServiceImpl")
	public void setTaskService(TaskService taskService)
	{
		this.taskService = taskService;
	}

}
