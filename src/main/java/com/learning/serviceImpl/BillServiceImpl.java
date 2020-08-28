package com.learning.serviceImpl;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.learning.model.Bill;
import com.learning.model.Goods;
import com.learning.model.Person;
import com.pj.media.util.MysqlHibernateUtil;

public class BillServiceImpl {
	public Bill getBillById(Long id)
	{
		Session session = null;
		Transaction tx = null;
		Bill c = null;
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//hibernate 5
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Bill> criteriaQuery = builder.createQuery(Bill.class);
			Root<Bill> root = criteriaQuery.from(Bill.class);
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
	public static void main(String[] args) {
		BillServiceImpl billService = new BillServiceImpl();
		Bill bill = billService.getBillById(1L);
		if(bill!= null)
		{
			Set<Goods> set = bill.getGoodsLst();
			for(Goods g : set)
			{
				System.out.println(g.getGoodsName() + " - " + g.getPrice());
			}
		}
	}
}
