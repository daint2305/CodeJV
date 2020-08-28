package com.learning.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learning.model.Classroom;
import com.learning.model.Person;
import com.learning.model.PersonDetail;
import com.pj.media.util.MysqlHibernateUtil;

public class PersonDetailServiceImpl {
	public PersonDetail getPersonSalary(Long id)
	{
		Session session = null;
		Transaction tx = null;
		PersonDetail c = null;
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//hibernate 5
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<PersonDetail> criteriaQuery = builder.createQuery(PersonDetail.class);
			Root<PersonDetail> root = criteriaQuery.from(PersonDetail.class);
			Predicate p1 = builder.equal(root.get("detail_ID"), id);
			criteriaQuery.select(root).where(p1);
			c = session.createQuery(criteriaQuery).uniqueResult();
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return c;
	}
	
	
	public Person getPerson(Long id)
	{
		Session session = null;
		Transaction tx = null;
		Person c = null;
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//hibernate 5
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
			Root<Person> root = criteriaQuery.from(Person.class);
			Predicate p1 = builder.equal(root.get("id"), id);
			criteriaQuery.select(root).where(p1);
			c = session.createQuery(criteriaQuery).uniqueResult();
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return c;
	}

	public void insertPersonDetail()
	{
		Session session = null;
		Transaction tx = null;
		
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Person p = new Person();
			p.setName("Nam");
			
			PersonDetail detail = new PersonDetail();
			detail.setSalary("2000");
			detail.setPerson(p);
			
			session.save(detail);
			
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
public void deletePerson(Person p) {
		
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.delete(p);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}
	
	public static void main(String[] args) {
		PersonDetailServiceImpl personDetail = new PersonDetailServiceImpl();
		
		//get personDetail
		
//		PersonDetail room = personDetail.getPersonSalary(1L);
//		Person p = room.getPerson();
//		System.out.println(p.getId() + " - " + p.getName() + " - " + p.getPerson_D().getSalary());

		//insert personDetail
		
//		personDetail.insertPersonDetail();
		
		//delete person Detail
		Person p = personDetail.getPerson(13L);
		
		personDetail.deletePerson(p);
		
	}
}
