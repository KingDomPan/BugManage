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
import com.us.bug.qd.model.BugReason;
import com.us.bug.qd.model.User;
import com.us.bug.qd.service.BugReasonService;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.UserService;

@SuppressWarnings("serial")
@Component("bugReasonAction")
@Scope("prototype")
public class BugReasonAction extends ActionSupport implements
		ModelDriven<BugReason>, ServletRequestAware, ServletResponseAware
{
	private UserService userService;
	private BugService bugService;
	private int bugId;
	public int getBugId()
	{
		return bugId;
	}

	public void setBugId(int bugId)
	{
		this.bugId = bugId;
	}

	public BugService getBugService()
	{
		return bugService;
	}

	@Resource(name="bugServiceImpl")
	public void setBugService(BugService bugService)
	{
		this.bugService = bugService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Resource(name="userServiceImpl")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	private BugReason bugReason = new BugReason();
	private BugReasonService bugReasonService;
	private HttpServletRequest request;
	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	private HttpServletResponse response;

	public void bugOperate() throws IOException
	{
		bugReason.setManager((User)(request.getSession().getAttribute("user")));
		Bug bug=bugService.loadById(bugId);
		if(bugReason.getBugReasonType().equals("回退"))
		{
			bug.setStateName("待完善");
			bugService.update(bug);
		}
		if(bugReason.getBugReasonType().equals("延期"))
		{
			bug.setStateName("延期");
			bugService.update(bug);
		}
		if(bugReason.getBugReasonType().equals("关闭"))
		{
			bug.setStateName("关闭");
			bugService.update(bug);
		}
		bugReason.setDealledBug(bug);
		bugReason.setSubmitedDay(new Date());
		bugReasonService.bugOperate(bugReason);
		
		response.getWriter().write("{success:true}");
	}

	public BugReasonService getBugReasonService()
	{
		return bugReasonService;
	}

	@Override
	public BugReason getModel()
	{
		return bugReason;
	}

	@Resource(name = "bugReasonServiceImpl")
	public void setBugReasonService(BugReasonService bugReasonService)
	{
		this.bugReasonService = bugReasonService;
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
