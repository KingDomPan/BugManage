package com.us.bug.qd.action;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.us.bug.qd.model.Task;
import com.us.bug.qd.model.User;
import com.us.bug.qd.service.BugService;
import com.us.bug.qd.service.TaskService;
import com.us.bug.qd.service.UserService;
import com.us.bug.qd.tools.Container;
import com.us.bug.qd.tools.Encode;
import com.us.bug.qd.tools.MD5Code;

@SuppressWarnings("serial")
@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ServletRequestAware,
		ModelDriven<User>, ServletResponseAware
{
	private User user = new User();
	private UserService userService;
	private TaskService taskService;
	private BugService bugService;
	private Encode encode;
	private MD5Code md5Code;
	
	public MD5Code getMd5Code()
	{
		return md5Code;
	}

	@Resource(name="md5Code")
	public void setMd5Code(MD5Code md5Code)
	{
		this.md5Code = md5Code;
	}

	//private SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
	
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

	public TaskService getTaskService()
	{
		return taskService;
	}

	@Resource(name = "taskServiceImpl")
	public void setTaskService(TaskService taskService)
	{
		this.taskService = taskService;
	}

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String checkcode;
	private int page;
	private int userid;

	public int getUserid()
	{
		return userid;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public String getCheckcode()
	{
		return checkcode;
	}

	@Override
	public User getModel()
	{
		return user;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public HttpSession getSession()
	{
		return session;
	}

	public User getUser()
	{
		return user;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void isLogined() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		User users = (User) session.getAttribute("user");
		if (users == null)
		{
			response.getWriter().write("{success:false}");
		}
		else
		{
			response.getWriter().write(
					"{success:true,username:'" + users.getUserName()
							+ "',userstatus:'" + users.getPost() + "'}");
		}
	}

	public void login() throws IOException
	{
		response.setCharacterEncoding("utf-8");
		String rand = (String) session.getAttribute("rand");
		if (!checkcode.equals(rand))
		{
			response.getWriter().write("{success:false,errorMessage:'验证码不正确'}");
		}
		else
		{
			User _user = userService.isExist(user);
			if (_user != null)
			{
				session.setAttribute("user", _user);
				response.getWriter().write(
						"{success:true,username:'" + _user.getUserName()
								+ "',userstatus:'" + _user.getPost() + "'}");
			}
			else
			{
				response.getWriter().write(
						"{success:false,errorMessage:'用户不存在或密码错误!'}");
			}
		}

	}

	public void loginOut() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		session.removeAttribute("user");
		response.getWriter().write("{success:true}");
	}

	public void setCheckcode(String checkcode)
	{
		this.checkcode = checkcode;
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
		this.session = request.getSession();
	}

	@Override
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	public void setSession(HttpSession session)
	{
		this.session = session;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Resource(name = "userServiceImpl")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public void save() throws IOException
	{
		 response.setContentType("text/json; charset=utf-8");
		 user.setUserId(encode.getUserIdEncode());
		 user.setUserState("在职");
		 user.setPwd(md5Code.getMD5ofStr("888888"));
		 this.userService.save(user);
		 response.getWriter().write("{success:true}");
	}
	

	public void update() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		User _user = userService.loadById(userid);
		String post = _user.getPost();
		String userState = user.getUserState();
		if ("开发人员".equals(post))
		{
			if (!(userState.equals("在职")))
			{
				// here to change bugStateName
				List<Task> tasks = taskService.getTaskByDeveloperId(userid);
				if (tasks != null)
					for (int i = 0; i < tasks.size(); i++)
					{
						bugService.updateBugByTask(tasks.get(i));
					}
			}
		}
		_user.setUserName(user.getUserName());
		_user.setPost(user.getPost());
		_user.setBirthday(user.getBirthday());
		_user.setTelephone(user.getTelephone());
		_user.setSex(user.getSex());
		_user.setUserState(userState);
		_user.setRemark(user.getRemark());
		this.userService.update(_user);
		response.getWriter().write("{success:true}");
	}

	public void getUserByPage() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<User> datas = this.userService.getUserByPage(page, 15);
		int totalCount = datas.getTotalPages() * 15;
		List<User> conLists = datas.getDataContainer();
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conLists.size(); i++)
		{
			User o = conLists.get(i);
			conJson.append("{userid:").append(o.getId()).append(",userId:'")
					.append(o.getUserId()).append("',userName:'")
					.append(o.getUserName()).append("',sex:'")
					.append(o.getSex()).append("',telephone:'")
					.append(o.getTelephone()).append("',birthday:'")
					.append(o.getBirthday()).append("',post:'")
					.append(o.getPost()).append("',userState:'")
					.append(o.getUserState()).append("',remark:'")
					.append(o.getRemark()).append("'}");
			if (i < conLists.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}
	
	
	//获得所有在职项目经理
	public void getMangersInPosition() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		List<User> managers=userService.getMangersInPosition();
		StringBuffer conJson=new StringBuffer("[");
		for(int i=0;i<managers.size();i++)
		{
			User u = managers.get(i);
			conJson.append("{userid:").append(u.getId()).append(",userId:'")
					.append(u.getUserId()).append("',userName:'")
					.append(u.getUserName()).append("'}");
			if (i < managers.size() - 1)
				conJson.append(",");
		}
		conJson.append("]");
		response.getWriter().write(conJson.toString());
	}
	
	public void getDevelopersInPosition() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<User> developers=userService.getDevelopersInPosition(page, 15);
		StringBuffer conJson=new StringBuffer("[");
		List<User> conDevelopers=developers.getDataContainer();
		for(int i=0;i<conDevelopers.size();i++)
		{
			User u = conDevelopers.get(i);
			conJson.append("{userid:").append(u.getId()).append(",userId:'")
					.append(u.getUserId()).append("',userName:'")
					.append(u.getUserName()).append("'}");
			if (i < conDevelopers.size() - 1)
				conJson.append(",");
		}
		conJson.append("]");
		response.getWriter().write(conJson.toString());
	}
}
