package com.learning.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import com.learning.model.C;
import com.learning.model.Person;
import com.pj.media.util.MysqlHibernateUtil;

public class TestNewServiceImpl {

	public List<Person> searchAnd(String name, String address, Integer sex) {
		Session session = null;
		Transaction tx = null;
		List<Person> lst = new ArrayList<>();

		try {

			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
			Root<Person> root = criteriaQuery.from(Person.class);

			Predicate p1 = builder.like(root.get("name"),  name + "%");
			Predicate p2 = builder.equal(root.get("address"), address);
			Predicate p3 = builder.equal(root.get("sex"), sex);

			Predicate pAnd = builder.and(p1, p2, p3);

			criteriaQuery.select(root).where(pAnd);

			lst = session.createQuery(criteriaQuery).getResultList();

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
		return lst;
	}

	public List<Person> searchOr(Integer age1, Integer age2) {
		Session session = null;
		Transaction tx = null;
		List<Person> lst = new ArrayList<>();

		try {

			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
			Root<Person> root = criteriaQuery.from(Person.class);

			Predicate p1 = builder.equal(root.get("age"), age1);
			Predicate p2 = builder.equal(root.get("age"), age2);

			Predicate pOr = builder.or(p1, p2);

			criteriaQuery.select(root).where(pOr);

			lst = session.createQuery(criteriaQuery).getResultList();

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
		return lst;
	}

	public List<Person> searchIn(List<Long> ids) {
		Session session = null;
		Transaction tx = null;
		List<Person> lst = new ArrayList<>();

		try {

			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
			Root<Person> root = criteriaQuery.from(Person.class);

			In<Long> inClause = builder.in(root.get("id"));
			for (Long id : ids) {
				inClause.value(id);
			}
			 
			Predicate p1 = builder.like(root.get("name"), "%A%");
			Predicate p2 = builder.equal(root.get("age"), 25);
			Predicate pAnd = builder.and(p1, p2, inClause);

			criteriaQuery.select(root).where(pAnd);

			lst = session.createQuery(criteriaQuery).getResultList();

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
		return lst;
	}

	public Long count() {
		Session session = null;
		Transaction tx = null;
		Long count = 0L;
		try {

			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
			Root<Person> root = criteriaQuery.from(Person.class);

			criteriaQuery.select(builder.count(root));

			count = session.createQuery(criteriaQuery).getSingleResult();

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
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Person> searchSqlQuery(String name, Integer age) {
		Session session = null;
		Transaction tx = null;
		List<Person> lst = new ArrayList<>();

		try {

			String sql = "SELECT id id3 , name name3 , age age3 , address address3 "
					+ "FROM person where name like :name and age = :age";

			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query<Person> q = session.createNativeQuery(sql, "personMap03");
			q.setParameter("name", "%" + name + "%", new StringType());
			q.setParameter("age", age, new IntegerType());

			lst = q.getResultList();

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
		return lst;
	}

	public Person getById(Long id) {
		/*
		 * return dataMap.get(id);
		 */
		Session session = null;
		Transaction tx = null;
		Person o = null;
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//hibernate 5
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			//select
			CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
			
			Root<Person> root = criteriaQuery.from(Person.class);
			Predicate p1 = builder.equal(root.get("id"), id);
			criteriaQuery.select(root).where(p1);
			o = session.createQuery(criteriaQuery).uniqueResult();
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
		return o;
	}

	public static void main(String[] args) {

		TestNewServiceImpl testNewServiceImpl = new TestNewServiceImpl();
		try {
			// System.err.println(testNewServiceImpl.searchAnd("f", 31));
			// System.err.println(testNewServiceImpl.searchOr(12, 31));

			List<Long> ids = new ArrayList<Long>();
			ids.add(1L);
			ids.add(2L);
			ids.add(3L);
			ids.add(4L);

			List<Person> lst = testNewServiceImpl.searchIn(ids);
			for(Person p : lst)
			{
				System.out.println(p.toJson());
			}
			// System.err.println(testNewServiceImpl.count());
			// System.err.println(testNewServiceImpl.searchSqlQuery("f", 31));

		} catch (Exception e) {
			e.printStackTrace();
		}
		//testNewServiceImpl.getById(1L);
//		//testNewServiceImpl.searchSqlQuery("a", 30);
//		List<Person> lst = testNewServiceImpl.searchSqlQuery("dai", 25);
//		for(Person p : lst)
//		{
//			System.out.println(p.toJson());
//		}
		//testNewServiceImpl.searchAnd("A", "HN", 1);
		
	}

}
