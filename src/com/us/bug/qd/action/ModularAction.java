package com.us.bug.qd.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.us.bug.qd.model.Modular;
import com.us.bug.qd.service.ModularService;
import com.us.bug.qd.service.ProjectService;
import com.us.bug.qd.service.UserService;
import com.us.bug.qd.tools.Encode;

@SuppressWarnings("serial")
@Component("modularAction")
@Scope("prototype")
public class ModularAction extends ActionSupport implements
		ServletResponseAware, ModelDriven<Modular>
{

	private ModularService modularService;
	private Modular modular = new Modular();
	private String query;
	private HttpServletResponse response;
	private UserService userService;
	private ProjectService projectService;
	private int projectid;
	private int developerId;
	private int modularid;
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
	public int getModularid()
	{
		return modularid;
	}

	public void setModularid(int modularid)
	{
		this.modularid = modularid;
	}

	public int getProjectid()
	{
		return projectid;
	}

	public void setProjectid(int projectid)
	{
		this.projectid = projectid;
	}

	public int getDeveloperId()
	{
		return developerId;
	}

	public void setDeveloperId(int developerId)
	{
		this.developerId = developerId;
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

	public ProjectService getProjectService()
	{
		return projectService;
	}

	@Resource(name = "projectServiceImpl")
	public void setProjectService(ProjectService projectService)
	{
		this.projectService = projectService;
	}

	@Override
	public Modular getModel()
	{
		return modular;
	}

	public void getModularById() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		if (!"".equals(query))
		{
			List<Modular> modularList = modularService
					.getModularsByProjectId(query);

			StringBuffer modularJson = new StringBuffer("[");
			for (int i = 0; i < modularList.size(); i++)
			{
				Modular m = modularList.get(i);
				modularJson.append("{id:").append(m.getId())
						.append(",modularId:'").append(m.getModularId())
						.append("',modularName:'").append(m.getModularName())
						.append("'}");
				if (i < modularList.size() - 1)
					modularJson.append(",");
			}
			modularJson.append("]");
			response.getWriter().write(modularJson.toString());
		}
	}

	public void getModularsById() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		if (!"".equals(query))
		{
			List<Modular> modularList = modularService
					.getModularsByProjectId(query);

			StringBuffer modularJson = new StringBuffer("[");
			for (int i = 0; i < modularList.size(); i++)
			{
				Modular m = modularList.get(i);
				modularJson.append("{modularid:").append(m.getId())
						.append(",modularId:'").append(m.getModularId())
						.append("',modularName:'").append(m.getModularName())
						.append("',developerid:")
						.append(m.getDeveloper().getId())
						.append(",developerId:'")
						.append(m.getDeveloper().getUserId())
						.append("',developerName:'")
						.append(m.getDeveloper().getUserName()).append("'}");
				if (i < modularList.size() - 1)
					modularJson.append(",");
			}
			modularJson.append("]");
			response.getWriter().write(modularJson.toString());
		}
	}

	public ModularService getModularService()
	{
		return modularService;
	}

	public String getQuery()
	{
		return query;
	}

	public void setModular(Modular modular)
	{
		this.modular = modular;
	}

	@Resource(name = "modularServiceImpl")
	public void setModularService(ModularService modularService)
	{
		this.modularService = modularService;
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

	public void add() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		modular.setDeveloper(userService.loadById(developerId));
		modular.setProject(projectService.loadById(projectid));
		modular.setModularId(encode.getModularIdEncode(projectid));
		this.modularService.add(modular);
		response.getWriter().write("{success:true}");
	}

	public void update() throws IOException
	{
		response.setContentType("text/json; charset=utf-8");
		Modular m = modularService.loadById(modularid);
		m.setDeveloper(userService.loadById(developerId));
		m.setModularName(modular.getModularName());
		this.modularService.update(m);
		response.getWriter().write("{success:true}");
	}

}
