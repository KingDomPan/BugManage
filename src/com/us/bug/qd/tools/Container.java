package com.us.bug.qd.tools;
import java.util.List;
/**
 * ��ҳ�������� 			
 * totalPages�ܹ���ҳ��  
 * dataContainer��������
 * @author ��ΰ��
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
