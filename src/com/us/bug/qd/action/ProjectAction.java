package com.us.bug.qd.action;

import java.io.IOException;
import java.util.Iterator;
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
import com.us.bug.qd.model.Modular;
import com.us.bug.qd.model.Project;
import com.us.bug.qd.model.User;
import com.us.bug.qd.service.ProjectService;
import com.us.bug.qd.service.UserService;
import com.us.bug.qd.tools.Container;
import com.us.bug.qd.tools.Encode;

@SuppressWarnings("serial")
@Component("projectAction")
@Scope("prototype")
public class ProjectAction extends ActionSupport implements
		ServletResponseAware, ServletRequestAware, ModelDriven<Project>
{
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ProjectService projectService;
	private UserService userService;
	private int page;
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

	private Project project = new Project();
	private int projectid;
	private int managerId;
	public int getManagerId()
	{
		return managerId;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void setManagerId(int managerId)
	{
		this.managerId = managerId;
	}

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
		User manager = userService.loadById(managerId);
		this.project.setManager(manager);
		this.project.setProjectId(encode.getProjectIdEncode());
		this.projectService.add(project);
		response.getWriter().write("{success:true}");
	}

	public void getAllProjectAndModularByPage() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<Project> datas = this.projectService
				.getAllProjectAndModularByPage(page, 15);
		int totalCount = datas.getTotalPages() * 15;
		List<Project> conLists = datas.getDataContainer();
		StringBuffer conJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < conLists.size(); i++)
		{
			Project o = conLists.get(i);
			conJson.append("{projectId:").append(o.getId())
					.append(",projectid:'").append(o.getProjectId())
					.append("',projectName:'").append(o.getProjectName())
					.append("',managerId:").append(o.getManager().getId())
					.append("',[");
			Iterator<Modular> modularIterator = o.getModulars().iterator();
			while (modularIterator.hasNext())
			{
				Modular m = modularIterator.next();
				conJson.append("{modularId:'").append(m.getId())
						.append("',modularid:'").append(m.getModularId())
						.append("',modularName:'").append(m.getModularName())
						.append("'}");
				if (modularIterator.hasNext())
				{
					conJson.append(",");
				}
			}
			conJson.append("]}");
			if (i < conLists.size() - 1)
				conJson.append(",");
		}
		conJson.append("]}");
		response.getWriter().write(conJson.toString());
	}

	@Override
	public Project getModel()
	{
		return project;
	}

	public int getPage()
	{
		return page;
	}

	public void getProjectAndModularAndDeveloper() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Project p = this.projectService.getCurrentProject(projectid);
		Iterator<Modular> modulars = p.getModulars().iterator();
		StringBuffer treeJson = new StringBuffer("{children:[{text:'当前项目',leaf:false,children:[");
		while (modulars.hasNext())
		{
			Modular m = modulars.next();
			treeJson.append("{text:'").append(m.getModularName())
					.append("',id:").append(m.getId()).append(",leaf:false,")
					.append("children:[").append("{text:'")
					.append(m.getDeveloper().getUserName()).append("',userid:")
					.append(m.getDeveloper().getId()).append(",leaf:true}")
					.append("]}");
			if (modulars.hasNext())
			{
				treeJson.append(",");
			}
		}
		treeJson.append("]},{text:'其他项目',leaf:false,children:[");
		List<User> developerInPosition=userService.getDevelopersInPosition();
		Iterator<User> dt=developerInPosition.iterator(); 
		while(dt.hasNext()){
			User u=dt.next();
			treeJson.append("{text:'").append(u.getUserName()).append("',leaf:true,userid:")
			.append(u.getId()).append("}");
			if (dt.hasNext())
			{
				treeJson.append(",");
			}
		}
		treeJson.append("]}]}");
        response.getWriter().write(treeJson.toString());
	}

	public void getProjectByPage() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<Project> projectContainer = projectService
				.getProjectByPage(page);
		int totalCount = projectContainer.getTotalPages() * 15;
		List<Project> projectList = projectContainer.getDataContainer();
		StringBuffer projectJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < projectList.size(); i++)
		{
			Project p = projectList.get(i);
			projectJson.append("{id:").append(p.getId()).append(",projectId:'")
					.append(p.getProjectId()).append("',projectName:'")
					.append(p.getProjectName()).append("'}");
			if (i < projectList.size() - 1)
				projectJson.append(",");
		}
		projectJson.append("]}");
		response.getWriter().write(projectJson.toString());
	}

	public void getProjectsByPage() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Container<Project> projectContainer = projectService
				.getProjectByPage(page);
		int totalCount = projectContainer.getTotalPages() * 15;
		List<Project> projectList = projectContainer.getDataContainer();
		StringBuffer projectJson = new StringBuffer("{totalCount:" + totalCount
				+ ",topics:[");
		for (int i = 0; i < projectList.size(); i++)
		{
			Project p = projectList.get(i);
			projectJson.append("{projectid:").append(p.getId())
					.append(",projectId:'").append(p.getProjectId())
					.append("',projectName:'").append(p.getProjectName())
					.append("',managerid:").append(p.getManager().getId())
					.append(",managerId:'").append(p.getManager().getUserId())
					.append("',managerName:'")
					.append(p.getManager().getUserName()).append("'}");
			if (i < projectList.size() - 1)
				projectJson.append(",");
		}
		projectJson.append("]}");
		response.getWriter().write(projectJson.toString());
	}

	public ProjectService getProjectService()
	{
		return projectService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	@Resource(name = "projectServiceImpl")
	public void setProjectService(ProjectService projectService)
	{
		this.projectService = projectService;
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

	@Resource(name = "userServiceImpl")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public void update() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		System.out.println(managerId+"DDDDDDDDDDDDDDDDDDD");
		User manager=userService.loadById(managerId);
		Project p=projectService.loadById(projectid);
		p.setProjectName(project.getProjectName());
		p.setManager(manager);
		this.projectService.update(p);
		response.getWriter().write("{success:true}");
	}
}
