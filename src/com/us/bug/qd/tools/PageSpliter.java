package com.us.bug.qd.tools;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * ͨ�õķ�ҳ��
 * @author ����
 */
@SuppressWarnings("unchecked")
@Component("pageSpliter")
public class PageSpliter
{
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * ��ҳ��ȡ����(���������ֶ�)
	 * 
	 * @param <E>					ӳ�����ݿ����
	 * @param model					ӳ�����ݿ��Model��Class
	 * @param pageNo				ҳ��
	 * @param pageSize				ÿһҳ�ļ�¼����
	 * @param orderby				������ֶ�(�﷨��obj.xxx)
	 * @param sort					�����ǽ���(�﷨��Global.DESC)
	 * @return 						��ҳ��������
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
	 * ��ҳ��ȡ����(������ȡָ���ֶ�,��Model�����б������⼸���ֶεĹ��췽��)
	 * 
	 * @param <E>			ӳ�����ݿ����
	 * @param model			ӳ�����ݿ��Model��Class
	 * @param pageNo		ҳ��
	 * @param pageSize		ÿһҳ�ļ�¼����
	 * @param fields		Ҫ��ȡ���ֶ�(�﷨��new String[]{xxx,xxx,xxx})
	 * @param orderby		������ֶ�(�﷨��obj.xxx)
	 * @param sort			�����ǽ���(�﷨��Global.DESC)
	 * @return 				��ҳ��������
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
	 * ��ҳ��ȡ����(���������ֶ�)
	 * 
	 * @param <E>			ӳ�����ݿ����
	 * @param model			ӳ�����ݿ��Model��Class
	 * @param pageNo		ҳ��
	 * @param pageSize		ÿһҳ�ļ�¼����
	 * @param where			ɸѡ����(�﷨��where obj.xxx > 1) 
	 * @param orderby		������ֶ�(�﷨��obj.xxx)
	 * @param sort			�����ǽ���(�﷨��Global.DESC)
	 * @return 				��ҳ��������
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
	 * ��ҳ��ȡ����(���������ֶ�)
	 * 
	 * @param <E>			ӳ�����ݿ����
	 * @param model			ӳ�����ݿ��Model��Class
	 * @param pageNo		ҳ��
	 * @param pageSize		ÿһҳ�ļ�¼����
	 * @param fetch			Ҫ���ӵĶ���(�﷨��left jion fetch obj.xxx xxx)
	 * @param where			ɸѡ����(�﷨��where obj.xxx > 1) 
	 * @param orderby		������ֶ�(�﷨��obj.xxx)
	 * @param sort			�����ǽ���(�﷨��Global.DESC)
	 * @return 				��ҳ��������
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
	 * ��ҳ��ȡ����(������ȡָ���ֶ�,��Model�����б������⼸���ֶεĹ��췽��)
	 * 
	 * @param <E>			ӳ�����ݿ����
	 * @param model			ӳ�����ݿ��Model��Class
	 * @param pageNo		ҳ��
	 * @param pageSize		ÿһҳ�ļ�¼����
	 * @param fields		Ҫ��ȡ���ֶ�(�﷨��new String[]{xxx,xxx,xxx})
	 * @param where			ɸѡ����(�﷨��obj.xxx > 1) 
	 * @param orderby		������ֶ�(�﷨��obj.xxx)
	 * @param sort			�����ǽ���(�﷨��Global.DESC)
	 * @return 				��ҳ��������
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
	 * ��ҳ��ȡ����(������ȡָ���ֶ�,��Model�����б������⼸���ֶεĹ��췽��)
	 * 
	 * @param <E>			ӳ�����ݿ����
	 * @param model			ӳ�����ݿ��Model��Class
	 * @param pageNo		ҳ��
	 * @param pageSize		ÿһҳ�ļ�¼����
	 * @param fields		Ҫ��ȡ���ֶ�(�﷨��new String[]{xxx,xxx,xxx})
	 * @param fetch			Ҫ���ӵĶ���(�﷨��left join fetch obj.xxx xx)
	 * @param where			ɸѡ����(�﷨��obj.xxx > 1) 
	 * @param orderby		������ֶ�(�﷨��obj.xxx)
	 * @param sort			�����ǽ���(�﷨��Global.DESC)
	 * @return 				��ҳ��������
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
