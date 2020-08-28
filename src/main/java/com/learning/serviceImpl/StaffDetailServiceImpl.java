package com.learning.serviceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.learning.model.Staff;
import com.learning.model.StaffDetail;
import com.pj.media.util.MysqlHibernateUtil;

public class StaffDetailServiceImpl {
	public StaffDetail  getStaffInfomation(Long Staff_ID) {


		Session session = null;
		Transaction tx = null;
		StaffDetail c = null;
		try {
			session = MysqlHibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//hibernate 5
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<StaffDetail> criteriaQuery = builder.createQuery(StaffDetail.class);
			Root<StaffDetail> root = criteriaQuery.from(StaffDetail.class);
			Predicate p1 = builder.equal(root.get("staffId"), Staff_ID);
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
		StaffDetailServiceImpl sd = new StaffDetailServiceImpl();
		
		//get staff information
		sd.getStaffInfomation(1L);
		StaffDetail staff = sd.getStaffInfomation(2L);
		Staff s = staff.getStaff();
		System.out.println(s.getStaffCode() + " - " + s.getStaffName() 
				+ " - " + s.getDetail().getAge()
				+ " - " + s.getDetail().getAcademicLevel()
				+ " - " + s.getDetail().getCountry()
				+ " - " + s.getDetail().getHobby());
			
	}
}
