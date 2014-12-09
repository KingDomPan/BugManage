package com.us.bug.qd.tools;
import java.util.List;
/**
 * 分页数据容器 			
 * totalPages总共的页数  
 * dataContainer数据容器
 * @author 张伟旭
 * 
 */
public class Container<E>
{
	private int totalPages;
	private List<E> dataContainer;

	public int getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	public List<E> getDataContainer()
	{
		return dataContainer;
	}

	public void setDataContainer(List<E> dataContainer)
	{
		this.dataContainer = dataContainer;
	}

}
