package com.us.bug.qd.tools;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * 增删改查通用组件
 * 
 * @author 张伟旭
 */
@SuppressWarnings({ "unchecked", "deprecation" })
@Component("crudHandler")
public class CrudHandler
{
	private HibernateTemplate hibernateTemplate;

	/**
	 * 执行存储过程
	 * 
	 * @param procedure
	 */
	public void execProcedure(String procedureName) throws Exception
	{
		this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.connection().prepareCall("exec " + procedureName).execute();
	}

	/**
	 * 返回一个对象
	 * 
	 * @param hql
	 *            hql语句
	 * @return
	 */
	public Object sclar(String hql)
	{
		return this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql).uniqueResult();
	}

	/**
	 * 添加记录
	 * 
	 * @param sql
	 *            SQL语句
	 */
	public void save(String sql)
	{
		this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 添加记录
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体Model
	 */
	public <E> void save(E model)
	{
		this.hibernateTemplate.save(model);
	}

	/**
	 * 批量添加
	 * 
	 * @param <E>
	 *            泛型类
	 * @param list
	 *            实体集合
	 */
	public <E> void save(List<E> list)
	{
		int length = list.size();
		for (int i = 0; i < length; i++)
		{
			this.hibernateTemplate.save(list.get(i));
		}
	}

	/**
	 * 删除记录(前提：表的主键列名为id)
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体Model的class
	 * @param id
	 *            实体Model的Id
	 */
	public <E> void delete(Class<E> model, long id)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("delete ").append(model.getName())
				.append(" obj where obj.id=").append(id);
		this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(sb.toString()).executeUpdate();
	}

	/**
	 * 删除记录(前提：表的主键列名为id)
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体Model的class
	 * @param id
	 *            实体Model的Id
	 */
	public <E> void delete(Class<E> model, int id)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("delete ").append(model.getName())
				.append(" obj where obj.id=").append(id);
		this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(sb.toString()).executeUpdate();
	}

	/**
	 * 删除记录
	 * 
	 * @param <E>
	 *            泛型类
	 * @param obj
	 *            实体Model
	 */
	public <E> void delete(E obj)
	{
		this.hibernateTemplate.delete(obj);
	}

	/**
	 * 批量删除
	 * 
	 * @param <E>
	 *            泛型类
	 * @param list
	 *            实体集合
	 */
	public <E> void deleteByBatch(List<E> list)
	{
		int length = list.size();
		for (int i = 0; i < length; i++)
		{
			this.hibernateTemplate.delete(list.get(i));
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param where
	 *            条件(语法：where obj.xxx > 1)
	 */
	public <E> void deleteByBatch(Class<E> model, String where)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("delete ").append(model.getName()).append(" obj ")
				.append(where);
		this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(sb.toString()).executeUpdate();
	}

	/**
	 * 更新记录
	 * 
	 * @param <E>
	 *            泛型类
	 * @param obj
	 *            实体 Model
	 */
	public <E> void update(E obj)
	{
		this.hibernateTemplate.update(obj);
	}

	/**
	 * 批量更新
	 * 
	 * @param <E>
	 *            泛型类
	 * @param list
	 *            实体集合
	 */
	public <E> void updateByBatch(List<E> list)
	{
		int length = list.size();
		for (int i = 0; i < length; i++)
		{
			this.hibernateTemplate.update(list.get(i));
		}
	}

	/**
	 * 根据条件更新指定字段
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param set
	 *            HQL语句(语法：set obj.xxx=xxx)
	 * @param where
	 *            条件(语法：where obj.xxx>1)
	 */
	public <E> void updateByBatch(Class<E> model, String set, String where)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("update ").append(model.getName()).append(" obj ")
				.append(set).append(" ").append(where);
		this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(sb.toString()).executeUpdate();
	}

	/**
	 * 读取顶部的N条数据
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param top
	 *            N条
	 * @param where
	 *            HQL语句(语法：where obj.xxx=xxx)
	 * @return 实体集合
	 */
	public <E> List<E> loadTopByCondition(Class<E> model, int top, String where)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(model.getName()).append(" obj ")
				.append(where);
		Query q = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(sb.toString());
		q.setMaxResults(top);
		return q.list();
	}

	/**
	 * 读取记录
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param id
	 *            主键Id
	 * @return 实体
	 */
	public <E> E loadById(Class<E> model, int id)
	{
		return (E) this.hibernateTemplate.get(model, id);
	}

	/**
	 * 读取指定字段的记录(前提：该对象必须要有参数fields的构造方法)
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体clss
	 * @param id
	 *            主键Id
	 * @param fields
	 *            要读取的字段(语法：new String[]{field1,field2})
	 * @return 实体
	 */
	public <E> E loadById(Class<E> model, int id, String[] fields)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select new ").append(model.getName()).append("(");
		for (int i = 0; i < fields.length; i++)
		{
			sb.append("obj.").append(fields[i]).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(") from ")
				.append(model.getName()).append(" obj where obj.id=")
				.append(id);
		return (E) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(sb.toString()).uniqueResult();
	}

	/**
	 * 根据条件读取记录
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param where
	 *            HQL语句(语法：where obj.xxx=xxx)
	 * @return 实体
	 */
	public <E> E loadById(Class<E> model, String where)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(model.getName()).append(" obj ")
				.append(where);
		return (E) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(sb.toString()).uniqueResult();
	}

	/**
	 * 根据条件读取指定字段的记录(前提：该对象必须要有参数fields的构造方法)
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param where
	 *            HQL语句(语法：where obj.xxx=xxx)
	 * @param fields
	 *            要读取的字段(语法：new String[]{field1,field2})
	 * @return 实体
	 */
	public <E> E loadById(Class<E> model, String where, String[] fields)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select new ").append(model.getName()).append("(");
		for (int i = 0; i < fields.length; i++)
		{
			sb.append("obj.").append(fields[i]).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(") from ")
				.append(model.getName()).append(" obj ").append(where);
		return (E) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(sb.toString()).uniqueResult();
	}

	/**
	 * 读取列表
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @return 实体集合
	 */
	public <E> List<E> getList(Class<E> model)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(model.getName());
		return (List<E>) this.hibernateTemplate.find(sb.toString());
	}

	/**
	 * 读取指定字段的列表(前提：该对象必须要有fields的构造方法)
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param fields
	 *            要读取的字段(语法：new String[]{field1,field2})
	 * @return 实体集合
	 */
	public <E> List<E> getList(Class<E> model, String[] fields)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select new ").append(model.getName()).append("(");
		for (int i = 0; i < fields.length; i++)
		{
			sb.append("obj.").append(fields[i]).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(") from ")
				.append(model.getName()).append(" obj");
		return (List<E>) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(sb.toString()).list();
	}

	/**
	 * 读取指定字段的列表(前提：该对象必须要有fields的构造方法)
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param fields
	 *            要读取的字段(语法：new String[]{field1,field2})
	 * @param orderby
	 *            排序字段 (语法：obj.xxx)
	 * @param desc
	 *            升序或降序(语法：Global.ASC)
	 * @return 实体集合
	 */
	public <E> List<E> getList(Class<E> model, String[] fields, String orderby,
			String desc)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select new ").append(model.getName()).append("(");
		for (int i = 0; i < fields.length; i++)
		{
			sb.append("obj.").append(fields[i]).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(") from ")
				.append(model.getName()).append(" obj order by ")
				.append(orderby).append(desc);
		return (List<E>) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(sb.toString()).list();
	}

	/**
	 * 根据条件读取列表
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param where
	 *            HQL语句(语法：where obj.xxx=xxx)
	 * @return 实体集合
	 */
	public <E> List<E> getList(Class<E> model, String where)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(model.getName()).append(" obj ")
				.append(where);
		return (List<E>) this.hibernateTemplate.find(sb.toString());
	}

	/**
	 * 根据条件读取列表
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param where
	 *            HQL语句(语法：where obj.xxx=xxx)
	 * @param orderby
	 *            排序字段 (语法：obj.xxx)
	 * @param desc
	 *            升序或降序(语法：Global.ASC)
	 * @return 实体集合
	 */
	public <E> List<E> getList(Class<E> model, String where, String orderby,
			String desc)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(model.getName()).append(" obj ")
				.append(where).append(" order by ").append(orderby).append(" ")
				.append(desc);
		return (List<E>) this.hibernateTemplate.find(sb.toString());
	}

	/**
	 * 根据条件读取指定字段的列表(前提：该对象必须要有fields的构造方法)
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param where
	 *            HQL语句(语法：where obj.xxx=xxx)
	 * @param fields
	 *            要读取的字段(语法：new String[]{field1,field2})
	 * @return 实体集合
	 */
	public <E> List<E> getList(Class<E> model, String where, String[] fields)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select new ").append(model.getName()).append("(");
		for (int i = 0; i < fields.length; i++)
		{
			sb.append("obj.").append(fields[i]).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(") from ")
				.append(model.getName()).append(" obj ").append(where);
		return (List<E>) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(sb.toString()).list();
	}

	/**
	 * 根据条件读取指定字段的列表(前提：该对象必须要有fields的构造方法)
	 * 
	 * @param <E>
	 *            泛型类
	 * @param model
	 *            实体class
	 * @param where
	 *            HQL语句(语法：where obj.xxx=xxx)
	 * @param fields
	 *            要读取的字段(语法：new String[]{field1,field2})
	 * @param orderby
	 *            排序字段 (语法：obj.xxx)
	 * @param desc
	 *            升序或降序(语法：Global.ASC)
	 * @return 实体集合
	 */
	public <E> List<E> getList(Class<E> model, String where, String[] fields,
			String orderby, String desc)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select new ").append(model.getName()).append("(");
		for (int i = 0; i < fields.length; i++)
		{
			sb.append("obj.").append(fields[i]).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(") from ")
				.append(model.getName()).append(" obj ").append(where)
				.append(" order by ").append(orderby).append(desc);
		return (List<E>) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(sb.toString()).list();
	}

	public <E> List<E> getList(Class<E> model, String fetch, String where,
			String orderby, String sort)
	{
		StringBuffer hql = new StringBuffer();
		hql.append("from ").append(model.getName()).append(" obj ")
				.append(fetch).append(" ").append(where).append(" order by ")
				.append(orderby).append(" ").append(sort);
		return (List<E>) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(hql.toString()).list();
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
