package com.jackie.hibernate;

import java.util.Date;

import junit.framework.TestCase;

import org.hibernate.Session;

import com.jackie.hibernate.Group;
import com.jackie.hibernate.HibernateUtils4Xml;
import com.jackie.hibernate.User;

public class Many2OneTest extends TestCase {

	public void testSave1() {
		Session session = null;
		try {
			session = HibernateUtils4Xml.getSession();
			session.beginTransaction();
			
			Group group = new Group();
			group.setName("第一组");
			
			User user1 = new User();
			user1.setName("张三");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("李四");
			user2.setGroup(group);
			
			session.save(user1);
			session.save(user2);
			//在清理缓存是发生错误TransientObjectException
			//因为Group为Transient状态，没有被session，在数据库中没有匹配的数据
			//而User为Persistent状态，在清理缓存时hibernate在缓存中无法找到Group对象
			//结论：Persistent状态的对象不能引用Transient状态的对象
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils4Xml.closeSession(session);
		}
	}
	
	public void testSave2() {
		Session session = null;
		try {
			session = HibernateUtils4Xml.getSession();
			session.beginTransaction();
			
			Group group = new Group();
			group.setName("第一组");
			session.save(group);
			
			User user1 = new User();
			user1.setName("张三");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("李四");
			user2.setGroup(group);
			
			session.save(user1);
			session.save(user2);
			//可以正确的保存数据
			//因为Group和User都是Persistent状态的对象
			//所以在hibernate清理缓存时在session中可以找到关联对象
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils4Xml.closeSession(session);
		}
	}	
	
	public void testSave3() {
		Session session = null;
		try {
			session = HibernateUtils4Xml.getSession();
			session.beginTransaction();
			
			Group group = new Group();
			group.setName("第一组");
			
			User user1 = new User();
			user1.setName("张三");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("李四");
			user2.setGroup(group);
			
			session.save(user1);
			session.save(user2);
			//没有抛出TransientObjectException异常
			//因为使用了级联特性
			//hibernate会首先保存User的关联对象对象Group
			//Group和User就都是Persistent状态的对象了
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils4Xml.closeSession(session);
		}
	}		
	
	public void testLoad1() {
		Session session = null;
		try {
			session = HibernateUtils4Xml.getSession();
			session.beginTransaction();
			User user = (User)session.load(User.class, 3);
			System.out.println("user.name=" + user.getName());
			System.out.println("user.group.name=" + user.getGroup().getName());
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils4Xml.closeSession(session);
		}
	}		
}
