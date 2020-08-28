/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.serviceImpl;

import com.learning.service.PersonService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.learning.model.Person;
import com.pj.media.util.MysqlHibernateUtil;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Admin
 */
//@Repository
//@Service
@Component
public class PersonServiceImpl implements PersonService {

    /*
    Integer ids = 0;
    HashMap<Integer, Person> dataMap = new HashMap<>();
     */
    public PersonServiceImpl() {
        /*
        Person p = new Person(ids++, "Tráº§n vÄƒn A", 1, 10, "Cáº§u Giáº¥y", "Ä�N");
        dataMap.put(p.getId(), p);

        p = new Person(ids++, "Nguyá»…n vÄƒn B" , 0, 11, "Ba Ä�Ã¬nh", "HCM");
        dataMap.put(p.getId(), p);

        p = new Person(ids++, "LÃª ThÃ nh Long",  1, 15, "TÃ¢y Há»“", "HN");
        dataMap.put(p.getId(), p);
         */
    }

    @Override
    public List<Person> getData(String searchName, Integer searchSex, String searchCountry, Date startTime, Date endTime, int pageIndex, int pageSize) {
        /*
        List<Person> list = new ArrayList<>();
        list.addAll(dataMap.values());
        return list;
         */
        Session session = null;
        Transaction tx = null;
        List<Person> lst = new ArrayList<>();

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            if (searchName != null && !searchName.trim().isEmpty()) {
                criteria.add(Restrictions.ilike("name", searchName.trim(), MatchMode.ANYWHERE));
            }

            if (searchSex != null) {
                criteria.add(Restrictions.eq("sex", searchSex));
            }

            if (searchCountry != null && !searchCountry.trim().isEmpty()) {
                criteria.add(Restrictions.eq("country", searchCountry));
            }

            if (startTime != null) {
                criteria.add(Restrictions.ge("createTime", startTime));
            }

            if (endTime != null) {
                criteria.add(Restrictions.le("createTime", endTime));
            }

            criteria.setFirstResult((pageIndex - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            lst = criteria.list();

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

    @Override
    public void addData(Person p) {
        /*
        if (p.getId() == null) {
            p.setId(ids++);
        }

        dataMap.put(p.getId(), p);
         */

        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            if(p.getSex()==null) {
            	p.setSex(1);
            }
            session.saveOrUpdate(p);
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
    
    
    public void addListData(List<Person> lst) {
    	
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            for(int i =0 ; i<lst.size(); i++) {
            	session.save(lst.get(i));
            }
            
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
    
    public void updateListData(List<Person> lst) {
    	
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            for(int i =0 ; i<lst.size(); i++) {
            	session.update(lst.get(i));
            }
            
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
    
    @Override
    public void delete(Person p) {

        /*
        if (dataMap.containsKey(id)) {
            dataMap.remove(id);
        }
         */
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

    @Override
    public Person getById(Long id) {

        /*
        return  dataMap.get(id);
         */
        Session session = null;
        Transaction tx = null;
        Person o = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            //hibernate version 3,4
            Criteria criteria = session.createCriteria(Person.class);
            criteria.add(Restrictions.eq("id", id));
            o = (Person) criteria.uniqueResult();

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

    @Override
    public Integer count(String searchName, Integer searchSex, String searchCountry, Date startTime, Date endTime) {
        Session session = null;
        Transaction tx = null;
        int count = 0;
        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            if (searchName != null && !searchName.trim().isEmpty()) {
                criteria.add(Restrictions.ilike("name", searchName.trim(), MatchMode.ANYWHERE));
            }

            if (searchSex != null) {
                criteria.add(Restrictions.eq("sex", searchSex));
            }

            if (searchCountry != null && !searchCountry.trim().isEmpty()) {
                criteria.add(Restrictions.eq("country", searchCountry));
            }

            if (startTime != null) {
                criteria.add(Restrictions.ge("createTime", startTime));
            }

            if (endTime != null) {
                criteria.add(Restrictions.le("createTime", endTime));
            }

            criteria.setProjection(Projections.rowCount());
            Object c = criteria.uniqueResult();
            count = c == null ? 0 : ((Long) c).intValue();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

	@Override
	public List<Person> getAllData() {
		 Session session = null;
	        Transaction tx = null;
	        List<Person> lst = new ArrayList<>();

	        try {

	            session = MysqlHibernateUtil.getSessionFactory().openSession();
	            tx = session.beginTransaction();
	            Criteria criteria = session.createCriteria(Person.class);
	            lst = criteria.list();
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

    @Override
    public List<Person> searchByDate(Date startTime, Date endTime, int pageIndex, int pageSize) {
         Session session = null;
        Transaction tx = null;
        List<Person> lst = new ArrayList<>();

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            if (startTime != null) {
                criteria.add(Restrictions.ge("createTime", startTime));
            }

            if (endTime != null) {
                criteria.add(Restrictions.le("createTime", endTime));
            }

            criteria.setFirstResult((pageIndex - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            
            criteria.addOrder(Order.desc("id"));

            lst = criteria.list();

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

    @Override
    public Integer countByDate(Date startTime, Date endTime) {
        Session session = null;
        Transaction tx = null;
        int count = 0;
        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            if (startTime != null) {
                criteria.add(Restrictions.ge("createTime", startTime));
            }

            if (endTime != null) {
                criteria.add(Restrictions.le("createTime", endTime));
            }

            criteria.setProjection(Projections.rowCount());
            Object c = criteria.uniqueResult();
            count = c == null ? 0 : ((Long) c).intValue();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }
    
    
    public void updateData(Person p) {
    	Session session = null;
        Transaction tx = null;
        try {
        	session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(p);;
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
		Person p = new Person();
		p.setName("dai");
		p.setAge(25);
		PersonServiceImpl ps1 = new PersonServiceImpl();
		Person p0 = new Person(3L, "Dai", 40);
		//ps1.addData(p0);
		
		//update data
		Person p1 = new Person((long) 1, "Nguyen", 30);
		p1.setAddress("HN");
		p1.setCountry("VN");
		p1.setSex(1);
		//ps1.updateData(p1);
		
		//delete data
		Person p2 = new Person();
		p2.setId((long) 3);
		//ps1.delete(p2);
		
		//add list data
		
		Person x1 = new Person(4L, "Nguyen", 30);
		Person x2 = new Person(5L, "Tuan", 26);
		Person x3 = new Person(6L, "Dai", 40);
		List<Person> lst = new ArrayList<Person>();
		//lst.add(x1);
		//lst.add(x2);
		//lst.add(x3);
		//ps1.addListData(lst);
		
	}
}
