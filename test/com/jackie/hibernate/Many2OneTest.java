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
			group.setName("��һ��");
			
			User user1 = new User();
			user1.setName("����");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("����");
			user2.setGroup(group);
			
			session.save(user1);
			session.save(user2);
			//���������Ƿ�������TransientObjectException
			//��ΪGroupΪTransient״̬��û�б�session�������ݿ���û��ƥ�������
			//��UserΪPersistent״̬����������ʱhibernate�ڻ������޷��ҵ�Group����
			//���ۣ�Persistent״̬�Ķ���������Transient״̬�Ķ���
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
			group.setName("��һ��");
			session.save(group);
			
			User user1 = new User();
			user1.setName("����");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("����");
			user2.setGroup(group);
			
			session.save(user1);
			session.save(user2);
			//������ȷ�ı�������
			//��ΪGroup��User����Persistent״̬�Ķ���
			//������hibernate������ʱ��session�п����ҵ���������
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
			group.setName("��һ��");
			
			User user1 = new User();
			user1.setName("����");
			user1.setGroup(group);
			
			User user2 = new User();
			user2.setName("����");
			user2.setGroup(group);
			
			session.save(user1);
			session.save(user2);
			//û���׳�TransientObjectException�쳣
			//��Ϊʹ���˼�������
			//hibernate�����ȱ���User�Ĺ����������Group
			//Group��User�Ͷ���Persistent״̬�Ķ�����
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
