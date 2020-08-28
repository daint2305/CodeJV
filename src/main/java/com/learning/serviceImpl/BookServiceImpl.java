package com.learning.serviceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.SUM;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.learning.model.Author;
import com.learning.model.Book;
import com.learning.model.C;
import com.learning.model.Classroom;
import com.learning.model.Person;
import com.learning.model.Publish;
import com.learning.model.StaffDetail;
import com.pj.media.util.MysqlHibernateUtil;

public class BookServiceImpl {
	public Author getAuthor(Long id)
	{
		Session session = null;
		Transaction tx = null;
		Author c = null;
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//hibernate 5
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);
			Root<Author> root = criteriaQuery.from(Author.class);
			Predicate p1 = builder.equal(root.get("id"), id);
			criteriaQuery.select(root).where(p1);
			//criteriaQuery.select(root);
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
	
	public Book getBook(Long id)
	{
		Session session = null;
		Transaction tx = null;
		Book c = null;
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//hibernate 5
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
			Root<Book> root = criteriaQuery.from(Book.class);
			Predicate p1 = builder.equal(root.get("id"), id);
			criteriaQuery.select(root).where(p1);
			//criteriaQuery.select(root);
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
	
	public List<Author> getAllAuthor() {
		Session session = null;
        Transaction tx = null;
        List<Author> lst = new ArrayList<Author>();

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);
			Root<Author> root = criteriaQuery.from(Author.class);
			
			
			criteriaQuery.select(root);
            lst =  session.createQuery(criteriaQuery).getResultList();
            
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
	public List<Publish> getBookInfo() {
		Session session = null;
		Transaction tx = null;
		List<Publish> lst = null;
		try {
			String sql ="SELECT b.CATEGORY as bookcategory, a.NAME as authorname, p.NUMBEROFPUBLISH as numberpublish, p.BOOK_COST as bookcost " 
					+	"FROM  PUBLISH as p "  
					+	"INNER JOIN BOOK as b ON p.BOOK_ID = b.BOOK_ID "  
					+	"INNER JOIN AUTHOR as a ON b.AUTHOR_ID = a.ID";
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			
			Query<Publish> q = session.createNativeQuery(sql, "publishMap1");
			
			
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
	
	
	public Long getAuthorRoyalty(Long authorid, Integer year) {
		Session session = null;
		Transaction tx = null;
		Long Sum = 0L;
		try {
			String sql ="SELECT SUM(p.AUTHOR_ROYALTY) as author_value  "
					+ 	"FROM  publish as p "
					+	"WHERE p.BOOK_ID IN "
					+ 	"(SELECT BOOK_ID FROM t3h.book WHERE AUTHOR_ID = :author_id) "
					+ 	"AND YEAR(DATE_PUBLISHED) = :year";
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Query<BigDecimal> q = session.createNativeQuery(sql);
			q.setParameter("author_id", authorid);
			q.setParameter("year", year);
			
			Sum = q.uniqueResult().longValue();
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
		return Sum;
	}
	
	public Long getPublisherProfits(Integer year) {
		Session session = null;
		Transaction tx = null;
		Long profits = 0L;
		try {
			String sql ="SELECT SUM((BOOK_COST*NUMBEROFPUBLISH) - PRODUCTION_COST - AUTHOR_ROYALTY) "
					+ "FROM  PUBLISH as p " + 
					"WHERE YEAR(DATE_PUBLISHED) = :year";
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Query<BigDecimal> q = session.createNativeQuery(sql);
			q.setParameter("year", year);
			
			profits = q.uniqueResult().longValue();
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
		return profits;
	}
	
	public List<Long> getBookIdOfAuthor(Long authorID)
	{
		Session session = null;
		Transaction tx = null;
		List<Long> bookid = null;
		
		try {
			
			String sql = "SELECT BOOK_ID "
					+ "FROM BOOK as b "
					+ "WHERE b.AUTHOR_ID = :author";
					
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query<Long> q = session.createNativeQuery(sql);
			q.setParameter("author", authorID);
			bookid = q.getResultList();
			
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
		
		return bookid;
	}
	
	public static void main(String[] args) {
		
		//cau 1
		BookServiceImpl bookservice = new BookServiceImpl();
//		Author  au =  bookservice.getAuthor(1L);
//		//Book bk = bookservice.getBook(1L);
//		
//		Set<Book> set = au.getBookset();
//		//Set<Publish> set1 = au.();
//		if(set!= null) {
//			for(Book b : set)
//			{
//				System.out.println( b.getId() + " - " + b.getAuthor().getAuthor_name() + " - " + b.getCategory() 
//				+ " - " + b.getCreate_time());
//			}
//		}
		
		List<Publish> lst = bookservice.getBookInfo();
		for(Publish o : lst)
		{	
			System.out.println(o.toJson());
		}
		
		//cau 2
		
//		Long Sum = bookservice.getAuthorRoyalty(1L, 2020);
//		System.out.println(Sum);
		
		//cau 3
		
//		Long profits = bookservice.getPublisherProfits(2020);
//		System.out.println(profits);
	}
}
