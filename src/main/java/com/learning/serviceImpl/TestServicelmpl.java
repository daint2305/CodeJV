package com.learning.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.learning.model.Classroom;
import com.learning.model.Oto;
import com.learning.model.Person;
import com.mysql.cj.result.YearToDateValueFactory;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.pj.media.util.MysqlHibernateUtil;

public class TestServicelmpl {
	public List<Oto> searchAndPrice(Long idSup, Long valueF, Long valueL) {
		Session session = null;
		Transaction tx = null;
		List<Oto> lst = new ArrayList<>();
		
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Oto> criteriaQuery = builder.createQuery(Oto.class);
			Root<Oto> root = criteriaQuery.from(Oto.class);
			
			Predicate p1 = builder.equal(root.get("idSup"), idSup);
			Predicate p2 = builder.between(root.get("value"), valueF , valueL);
			
			Predicate pAnd = builder.and(p1, p2);
			
			criteriaQuery.select(root).where(pAnd);

			lst = session.createQuery(criteriaQuery).getResultList();

			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally{
			if (session != null) {
				session.close();
			}
		}
		return lst;
		
	}
	
	
	public List<Oto> searchSerial(Integer serial) {
		Session session = null;
		Transaction tx = null;
		List<Oto> lst = new ArrayList<>();
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Oto> criteriaQuery = builder.createQuery(Oto.class);
			Root<Oto> root = criteriaQuery.from(Oto.class);
			Predicate p1 = builder.equal(root.get("serial"), serial);
			
			criteriaQuery.select(root).where(p1);
			
			lst = session.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally{
			if (session != null) {
				session.close();
			}
		}
		return lst;
	}
	
	public Long count(Long id) {
		Session session = null;
		Transaction tx = null;
		Long count = 0L;
		try {

			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
			Root<Oto> root = criteriaQuery.from(Oto.class);
			
			Predicate p1 = builder.gt(root.get("id"), id);
			
			criteriaQuery.select(builder.count(root)).where(p1);

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
	public List<Oto> searchSqlQuery(String name, Integer dateTime) {
		Session session = null;
		Transaction tx = null;
		List<Oto> lst = new ArrayList<>();
		
		try {
			String sql = "SELECT o.idO id, o.serial  serial, o.dateO createTime, o.value value, s.nameS supplierName " 
					+ "FROM oto as o "
					+ "LEFT JOIN supplier as s "
					+ "ON o.idSup = s.idS "
					+ "WHERE s.nameS = :name and YEAR(o.dateO) = :dateTime ";

			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query<Oto> q = session.createNativeQuery(sql, "otoMap01");
			q.setParameter("name",  name , new StringType());
			q.setParameter("dateTime", dateTime , new IntegerType());

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
	
	@SuppressWarnings("unchecked")
	public Long countCar(String name, Long value) {
		Session session = null;
		Transaction tx = null;
		List<Oto> lst = new ArrayList<>();
		
		try {
			String sql = "SELECT o.idO id, o.serial  serial, o.dateO createTime, o.value value, s.nameS supplierName " 
					+ "FROM oto as o "
					+ "LEFT JOIN supplier as s "
					+ "ON o.idSup = s.idS "
					+ "WHERE s.nameS = :name1 and o.value < :value2 ";

			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query<Oto> q = session.createNativeQuery(sql, "otoMap01");
			q.setParameter("name1",  name , new StringType());
			q.setParameter("value2", value , new LongType());

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
		return (long)lst.size();
	}
	
	@SuppressWarnings("unchecked")
	public Integer numberCar(String name, Integer value)
	{
		Session session = null;
		Transaction tx = null;
		int count = 0;
		try {
			String sql = "SELECT count(*) " 
					+ "FROM oto as o "
					+ "LEFT JOIN supplier as s "
					+ "ON o.idSup = s.idS "
					+ "WHERE s.nameS = :name and o.value < :value ";
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query<BigInteger> q = session.createNativeQuery(sql);
			q.setParameter("name", name);
			q.setParameter("value", value);
			
			count = q.uniqueResult().intValue();
			
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
	
	public Classroom getClassRoom( Long id)
	{
		Session session = null;
		Transaction tx = null;
		Classroom c = null;
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//hibernate 5
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Classroom> criteriaQuery = builder.createQuery(Classroom.class);
			Root<Classroom> root = criteriaQuery.from(Classroom.class);
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
	
	public void insertClass()
	{
		Session session = null;
		Transaction tx = null;
		
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Classroom room  = new Classroom();
			room.setClassname("ClassTest");
			room.setCode("abc");
			
			Set<Person> set = new HashSet<Person>();
			Person p1 = new Person();
			p1.setName("Tuan Dai1");
			p1.setClassroom(room);
			set.add(p1);
			
			Person p2 = new Person();
			p1.setName("Tuan Dai2");
			p1.setClassroom(room);
			set.add(p2);
			
			room.setPersonset(set);
			
			session.save(room);
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
	
	public void deleteMapping(Classroom c) {
		
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(c);
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
	
	@SuppressWarnings("unchecked")
	public List<Oto> SearchInSqlQuery(List<Long> ids, String name, Integer year)
	{
		Session session = null;
		Transaction tx = null;
		List<Oto> lst = new ArrayList<>();
		
		try {
			String sql = "SELECT o.idO id1, o.serial  serial1, o.dateO createTime1, o.value value1, s.nameS supplierName1 " 
					+ "FROM oto as o "
					+ "LEFT JOIN supplier as s "
					+ "ON o.idSup = s.idS "
					+ "WHERE s.nameS = :name and YEAR(o.dateO) = :year and o.idO in (:idList)";
			
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query<Oto> q = session.createNativeQuery(sql, "otoMap02");
			q.setParameter("idList",  ids );
			q.setParameter("name",  name , new StringType());
			q.setParameter("year",  year , new IntegerType());
			
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
	
	public static void main(String[] args) {
		TestServicelmpl testService = new TestServicelmpl();
		Classroom  room =  testService.getClassRoom(2L);
//		testService.deleteMapping(room);
		
//		testService.searchAndPrice(1L, 100000L , 500000L);
//		testService.searchSerial(9999);
//		testService.count(10L);
		
//		List<Oto> lst = testService.searchSqlQuery("Toyota",2020);
//		for(Oto o : lst)
//		{	
//			System.out.println(o.toJson());
//		}
		System.out.println(testService.numberCar("Toyota", 1000000));
		

//		Classroom  room =  testService.getClassRoom(1L);
//		
//		Set<Person> set = room.getPersonset();
//		if(set!= null) {
//			for(Person p : set)
//			{
//				System.out.println(p.getId() + " - " + p.getName());
//			}
//		}
//		
		//testService.insertClass();
		
		
//		List<Long> ids = new ArrayList<Long>();
//		ids.add(1L);
//		ids.add(2L);
//		ids.add(3L);
//		ids.add(4L);
//		List<Oto> lst = testService.SearchInSqlQuery(ids, "Toyota", 2020);
//		for(Oto o : lst)
//		{	
//			System.out.println(o.toJson());
//		}
		
	}
	
}
