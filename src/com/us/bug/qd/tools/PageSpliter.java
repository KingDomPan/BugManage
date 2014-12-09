package com.us.bug.qd.tools;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * 通用的分页类
 * @author 杨霖
 */
@SuppressWarnings("unchecked")
@Component("pageSpliter")
public class PageSpliter
{
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 分页读取数据(包括所有字段)
	 * 
	 * @param <E>					映射数据库的类
	 * @param model					映射数据库的Model的Class
	 * @param pageNo				页码
	 * @param pageSize				每一页的记录条数
	 * @param orderby				排序的字段(语法：obj.xxx)
	 * @param sort					升序还是降序(语法：Global.DESC)
	 * @return 						分页数据容器
	 */
	public <E> Container<E> getListByPage(Class<E> model, int pageNo, int pageSize, String orderby, String sort)
	{
		Container<E> container = new Container<E>();
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		StringBuffer hql = new StringBuffer();
		hql.append("from ").append(model.getName()).append(" obj").append(" order by ").append(orderby).append(" ").append(sort);
		container.setDataContainer(session.createQuery(hql.toString()).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list());
		hql = new StringBuffer("select count(*) from ").append(model.getName()).append(" obj");
		int records = Integer.parseInt(session.createQuery(hql.toString()).uniqueResult().toString());
		container.setTotalPages(records % pageSize == 0 ? records / pageSize : records / pageSize + 1);
		return container;
	}

	/**
	 * 分页读取数据(仅仅读取指定字段,在Model的类中必须有这几个字段的构造方法)
	 * 
	 * @param <E>			映射数据库的类
	 * @param model			映射数据库的Model的Class
	 * @param pageNo		页码
	 * @param pageSize		每一页的记录条数
	 * @param fields		要读取的字段(语法：new String[]{xxx,xxx,xxx})
	 * @param orderby		排序的字段(语法：obj.xxx)
	 * @param sort			升序还是降序(语法：Global.DESC)
	 * @return 				分页数据容器
	 */
	public <E> Container<E> getListByPage(Class<E> model, int pageNo, int pageSize, String[] fields, String orderby, String sort)
	{
		Container<E> container = new Container<E>();
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		StringBuffer hql = new StringBuffer();
		hql.append("select new ").append(model.getName()).append("(");
		int length = fields.length;
		for (int i = 0; i < length; i++)
		{
			hql.append("obj.").append(fields[i]).append(",");
		}
		hql.deleteCharAt(hql.length() - 1).append(") from ").append(model.getName()).append(" obj").append(" order by ").append(orderby).append(" ").append(sort);
		container.setDataContainer(session.createQuery(hql.toString()).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list());
		hql = new StringBuffer("select count(*) from ").append(model.getName()).append(" obj");
		int records = Integer.parseInt(session.createQuery(hql.toString()).uniqueResult().toString());
		container.setTotalPages(records % pageSize == 0 ? records / pageSize : records / pageSize + 1);
		return container;
	}

	/**
	 * 分页读取数据(包括所有字段)
	 * 
	 * @param <E>			映射数据库的类
	 * @param model			映射数据库的Model的Class
	 * @param pageNo		页码
	 * @param pageSize		每一页的记录条数
	 * @param where			筛选条件(语法：where obj.xxx > 1) 
	 * @param orderby		排序的字段(语法：obj.xxx)
	 * @param sort			升序还是降序(语法：Global.DESC)
	 * @return 				分页数据容器
	 */
	public <E> Container<E> getListByPage(Class<E> model, int pageNo, int pageSize, String where, String orderby, String sort)
	{
		Container<E> container = new Container<E>();
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		StringBuffer hql = new StringBuffer("select count(*) from ").append(model.getName()).append(" obj ").append(where);
		int records = Integer.parseInt(session.createQuery(hql.toString()).uniqueResult().toString());
		hql = new StringBuffer();
		hql.append("from ").append(model.getName()).append(" obj ").append(where).append(" order by ").append(orderby).append(" ").append(sort);;
		container.setDataContainer(session.createQuery(hql.toString()).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list());
		container.setTotalPages(records % pageSize == 0 ? records / pageSize : records / pageSize + 1);
		return container;
	}
	
	/**
	 * 分页读取数据(包括所有字段)
	 * 
	 * @param <E>			映射数据库的类
	 * @param model			映射数据库的Model的Class
	 * @param pageNo		页码
	 * @param pageSize		每一页的记录条数
	 * @param fetch			要连接的对象(语法：left jion fetch obj.xxx xxx)
	 * @param where			筛选条件(语法：where obj.xxx > 1) 
	 * @param orderby		排序的字段(语法：obj.xxx)
	 * @param sort			升序还是降序(语法：Global.DESC)
	 * @return 				分页数据容器
	 */
	public <E> Container<E> getListByPage(Class<E> model, int pageNo, int pageSize,String fetch, String where, String orderby, String sort)
	{
		Container<E> container = new Container<E>();
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		StringBuffer hql = new StringBuffer("select count(*) from ").append(model.getName()).append(" obj ").append(where);
		int records = Integer.parseInt(session.createQuery(hql.toString()).uniqueResult().toString());
		hql = new StringBuffer();
		hql.append("from ").append(model.getName()).append(" obj ").append(fetch).append(" ").append(where).append(" order by ").append(orderby).append(" ").append(sort);
		System.out.println(hql.toString());
		container.setDataContainer(session.createQuery(hql.toString()).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list());
		container.setTotalPages(records % pageSize == 0 ? records / pageSize : records / pageSize + 1);
		return container;
	}

	/**
	 * 分页读取数据(仅仅读取指定字段,在Model的类中必须有这几个字段的构造方法)
	 * 
	 * @param <E>			映射数据库的类
	 * @param model			映射数据库的Model的Class
	 * @param pageNo		页码
	 * @param pageSize		每一页的记录条数
	 * @param fields		要读取的字段(语法：new String[]{xxx,xxx,xxx})
	 * @param where			筛选条件(语法：obj.xxx > 1) 
	 * @param orderby		排序的字段(语法：obj.xxx)
	 * @param sort			升序还是降序(语法：Global.DESC)
	 * @return 				分页数据容器
	 */
	public <E> Container<E> getListByPage(Class<E> model, int pageNo, int pageSize, String[] fields, String where, String orderby, String sort)
	{
		Container<E> container = new Container<E>();
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		StringBuffer hql = new StringBuffer("select count(*) from ").append(model.getName()).append(" obj ").append(where);
		int records = Integer.parseInt(session.createQuery(hql.toString()).uniqueResult().toString());
		hql = new StringBuffer();
		hql.append("select new ").append(model.getName()).append("(");
		int length = fields.length;
		for (int i = 0; i < length; i++)
		{
			hql.append("obj.").append(fields[i]).append(",");
		}
		hql.deleteCharAt(hql.length() - 1).append(") from ").append(model.getName()).append(" obj ").append(where).append(" order by ").append(orderby).append(" ").append(sort);;
		container.setDataContainer(session.createQuery(hql.toString()).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list());
		container.setTotalPages(records % pageSize == 0 ? records / pageSize : records / pageSize + 1);
		return container;
	}
	
	/**
	 * 分页读取数据(仅仅读取指定字段,在Model的类中必须有这几个字段的构造方法)
	 * 
	 * @param <E>			映射数据库的类
	 * @param model			映射数据库的Model的Class
	 * @param pageNo		页码
	 * @param pageSize		每一页的记录条数
	 * @param fields		要读取的字段(语法：new String[]{xxx,xxx,xxx})
	 * @param fetch			要连接的对象(语法：left join fetch obj.xxx xx)
	 * @param where			筛选条件(语法：obj.xxx > 1) 
	 * @param orderby		排序的字段(语法：obj.xxx)
	 * @param sort			升序还是降序(语法：Global.DESC)
	 * @return 				分页数据容器
	 */
	public <E> Container<E> getListByPage(Class<E> model, int pageNo, int pageSize, String[] fields,String fetch, String where, String orderby, String sort)
	{
		Container<E> container = new Container<E>();
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		StringBuffer hql = new StringBuffer("select count(*) from ").append(model.getName()).append(" obj ").append(where);
		int records = Integer.parseInt(session.createQuery(hql.toString()).uniqueResult().toString());
		hql = new StringBuffer();
		hql.append("select new ").append(model.getName()).append("(");
		int length = fields.length;
		for (int i = 0; i < length; i++)
		{
			hql.append("obj.").append(fields[i]).append(",");
		}
		hql.deleteCharAt(hql.length() - 1).append(") from ").append(model.getName()).append(" obj ").append(fetch).append(" ").append(where).append(" order by ").append(orderby).append(" ").append(sort);;
		container.setDataContainer(session.createQuery(hql.toString()).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list());
		container.setTotalPages(records % pageSize == 0 ? records / pageSize : records / pageSize + 1);
		return container;
	}

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}
}
